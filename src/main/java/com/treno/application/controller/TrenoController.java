package com.treno.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.service.TrenoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/treni")
public class TrenoController {

	@Autowired
	@Qualifier("TrenoService")
	TrenoService trenoService;

	// Controllo accesso pagina solo in sessione di lgoin
	@GetMapping
	public String treni(HttpSession session, Model model) {
		User utente = (User) session.getAttribute("utente");

		if (utente == null) {
			return "redirect:/login";
		}

		model.addAttribute("utente", utente);
		return "treni4"; // dashboard
	}

// Metodo per mostrare la pagina per creare un nuovo treno
	@GetMapping("/crea")
	public String mostraFormCreazioneTreno(HttpSession session, Model model) {
		User utente = (User) session.getAttribute("utente");

		if (utente == null) {
			return "redirect:/login";
		}

		model.addAttribute("treno", Treno.build()); // Aggiungi un treno vuoto per il form
		return "crea2";
	}

	// Metodo per gestire la creazione del treno
	@PostMapping("/crea")
	public String creaTreno(@RequestParam("input") String input, @RequestParam("marca") String marca,
			HttpSession session, Model model) {
		User utente = (User) session.getAttribute("utente");

		if (utente == null) {
			return "redirect:/login";
		}

		// Chiama il servizio per creare il treno
		trenoService.creaTreno(input, utente, marca);

		return "redirect:/treni"; // Reindirizza alla lista dei treni o alla dashboard
	}

//	    @GetMapping("/visualizza")
//	    public String visualizzaTreniUtente(HttpSession session, Model model) {
//	        // Recupera l'utente dalla sessione
//	        User utente = (User) session.getAttribute("utente");
//
//	        if (utente == null) {
//	            return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
//	        }
//
//	        // Recupera i treni dell'utente dal servizio usando il suo ID
//	        List<Treno> treni = trenoService.findAllTreniByUser(utente.getUserId());
//	        model.addAttribute("treni", treni); // Passa i treni alla vista
//	        model.addAttribute("utente", utente); // Passa anche l'utente alla vista per eventuali altre informazioni
//	        return "visualizzaTreni"; // Restituisce la vista per la lista dei treni
//	    }

//	
//	@GetMapping("/visualizza")
//	public String visualizzaTreniUtente(HttpSession session, Model model) {
//	    // Recupera l'utente dalla sessione
//	    User utente = (User) session.getAttribute("utente");
//
//	    if (utente == null) {
//	        return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
//	    }
//
//	    // Recupera i treni dell'utente dal servizio usando il suo ID
//	    List<Treno> treni = trenoService.findAllTreniByUser(utente.getUserId());
//
//	    // Mappa ogni Treno su TrenoDTO
//	    List<TrenoDTO> treniDto = treni.stream()
//	    	    .map(treno -> new TrenoDTO(
//	    	        treno.getIdTreno(),             // Supponendo che il metodo per ottenere l'ID del treno sia getId()
//	    	        treno.getInVendita(),           // Nome del treno
//	    	        treno.getImmagine()     // URL dell'immagine del treno
//	    	    ))
//	    	    .collect(Collectors.toList());
//
//	    // Converte la lista in formato JSON
//	    String treniJson = new Gson().toJson(treniDto); // Assicurati di avere la libreria Gson
//
//	    // Passa la lista dei treni in formato JSON alla vista
//	    model.addAttribute("treniJson", treniJson);
//	    return "visualizzaTreni"; // Restituisce la vista per la lista dei treni
//	}

	@GetMapping("/visualizza")
	public String visualizzaTreniUtente(HttpSession session, Model model) {
		// Recupera l'utente dalla sessione
		User utente = (User) session.getAttribute("utente");

		if (utente == null) {
			return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
		}

		// Recupera i treni dell'utente dal servizio usando il suo ID
		List<Treno> treni = trenoService.findAllTreniByUser(utente.getUserId());

		// Mappa ogni Treno su TrenoDTO
		List<TrenoDTO> treniDto = treni.stream().map(treno -> new TrenoDTO(treno.getIdTreno(), // Supponendo che il
																								// metodo per ottenere
																								// l'ID del treno sia
																								// getId()
				treno.getInVendita(), // Nome del treno
				treno.getImmagine() // URL dell'immagine del treno
		)).collect(Collectors.toList());

		// Passa la lista dei DTO alla vista
		model.addAttribute("treniDto", treniDto);
		model.addAttribute("utente", utente); // Passa anche l'utente alla vista per eventuali altre informazioni
		return "visualizzaTreni"; // Restituisce la vista per la lista dei treni
	}

	@GetMapping("/dettagli/{id}")
	public String dettagliTreno(@PathVariable Long id, Model model) {
		Treno treno = trenoService.findById(id); // Recupera il treno dal database
		model.addAttribute("treno", treno); // Passa il treno alla vista
		return "dettagliTreno"; // Restituisce la vista dettagliTreno.jsp
	}

	///////////////////////////////////////////////////////

	@PostMapping("/listaFiltrata")
	public String getListaFiltrata(@ModelAttribute("trenoFilter") TrenoFilter trenoFilter, HttpSession session,
			Model model) {
		User utente = (User) session.getAttribute("utente");
		if (utente == null) {
			return "redirect:/login";
		}

		// Recupera i treni filtrati usando il filtro e l'ID dell'utente
		List<Treno> treni = trenoService.filtraTreni(trenoFilter, utente.getUserId());

		model.addAttribute("treni", treni);
		return "visualizzaTreni";
	}

}

package com.treno.application.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.exception.ValutazioneException;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.service.TrenoService;
import com.treno.application.service.UserService;
import com.treno.application.service.ValutazioneService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {
	
	@Autowired
	@Qualifier("ValutazioneService")
	private ValutazioneService valutazione;
	
	@Autowired
	@Qualifier("Sessione")
	private SessioneUtility sessione;
	
	
	@Autowired
	@Qualifier("TrenoService")
	private TrenoService trenoService;
	
	@Autowired
	@Qualifier("UserService")
	private UserService userService;
	
	
	
	
	
	    @GetMapping
	    public String getAllTreni(Model model, HttpSession session) {
	        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
	        
	        if(!sessione.isUtenteLoggato(session)) {
	        	return sessione.redirectTologin();
	        }
	        List<TrenoDTO> treniDto = trenoService.getAllTreni();
	        model.addAttribute("treni", treniDto);
	        model.addAttribute("utenteLoggato",utenteLoggato);
	        return "catalogo";
	    }
	    
	    
	    
	    
//	    // Applica il filtro sui treni
//	    @GetMapping("/filtro")
//	    public String filtraTreni(@ModelAttribute("filtro") TrenoFilter filtro, Model model, HttpSession session) {
//	        sessione.getUtenteLoggato(session);
//	        List<TrenoDTO> treniDto = trenoService.filtraTreni(filtro);
//	        model.addAttribute("treniDto", treniDto);
//	        model.addAttribute("filtro", filtro);
//	        return "visualizzaTreni";
//	    }
//	    
	    
	    

//	    @PostMapping("/filtra")
//	    public String filtraTreniPost(@ModelAttribute("trenoFilter") TrenoFilter trenoFilter, Model model, HttpSession session) {
//	        // Verifica che l'utente sia loggato
//	        if (!sessione.isUtenteLoggato(session)) {
//	            return sessione.redirectTologin();
//	        }
//
//	        // Recupera l'utente loggato
//	        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
//
//	        // Filtra i treni utilizzando il TrenoFilter
//	        List<TrenoDTO> treniFiltrati = trenoService.filtraTreni(trenoFilter);
//
//	        // Aggiungi la lista dei treni filtrati al modello
//	        model.addAttribute("treni", treniFiltrati);
//	        model.addAttribute("utenteLoggato", utenteLoggato);
//	        model.addAttribute("trenoFilter", trenoFilter);
//
//	        // Restituisci la vista del catalogo
//	        return "catalogo";
//	    }
//
//	    
	    
	    
	    @GetMapping("/filtro")
	    public String filtraTreni(@ModelAttribute("trenoFilter") TrenoFilter trenoFilter, Model model, HttpSession session) {
	        // Verifica che l'utente sia loggato
//	        if (!sessione.isUtenteLoggato(session)) {
//	            return sessione.redirectTologin();
//	        }

	        // Recupera l'utente loggato
	        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);

	        // Filtra i treni utilizzando il TrenoFilter
	        Set<TrenoDTO> treniFiltratiSet = trenoService.filtraTreni(trenoFilter);
	        List<TrenoDTO> treniFiltrati = treniFiltratiSet.stream().collect(Collectors.toList());
	        
	        
	        // Aggiungi la lista dei treni filtrati al modello
	        model.addAttribute("treni", treniFiltrati);
	        model.addAttribute("utenteLoggato", utenteLoggato);
	        model.addAttribute("trenoFilter", trenoFilter);

	        // Restituisci la vista del catalogo
	        return "catalogo";
	    }
	    
	    
	    
	    
//	    
//	    @PostMapping("/valutaTreno")
//	    public String valutaTreno(HttpSession session,
//	                              @ModelAttribute("treno") TrenoDTO trenoSelezionato,
//	                              @RequestParam("voto") int voto,
//	                              Model model) {
//
//	        // Verifica se l'utente è loggato
//	        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
//	        if (utenteLoggato == null) {
//	            return "redirect:/login"; // Reindirizza alla pagina di login se non è loggato
//	        }
//	        
//	        trenoSelezionato = trenoService.findById(trenoSelezionato.getIdTreno());
//	        
//
//	        // Controlla se il treno è stato selezionato correttamente
//	        if (trenoSelezionato == null || trenoSelezionato.getIdTreno()==0) {
//	            throw new ValutazioneException("Il treno da te selezionato non è al momento presente.");
//	        }
//
//	        try {
//	            // Chiama il metodo per la valutazione
//	            valutazione.UserValutaTreno(utenteLoggato.getUserId(), trenoSelezionato.getIdTreno(), voto);
//
//	            // Aggiorna i dati del treno e dell'utente dopo la valutazione
//	            TrenoDTO trenoAggiornato = trenoService.findById(trenoSelezionato.getIdTreno());
//	            UserDTO utenteAggiornato = userService.findById(utenteLoggato.getUserId());
//
//	            // Aggiungi i dati aggiornati al modello
//	            model.addAttribute("treno", trenoAggiornato);
//	            model.addAttribute("utente", utenteAggiornato);
//	            model.addAttribute("succes", "Il treno è stato valutato con successo");
//
//	            // Aggiorna i dati dell'utente nella sessione
//	            sessione.setUtenteLoggato(session, utenteAggiornato);
//
//	        } catch (ValutazioneException e) {
//	            model.addAttribute("errorMessage", e.getMessage());
//	            return "redirect:/treni/visualizza/treno";
//	        }
//
//	        return "redirect:/treni/visualizza/treno"; // Reindirizza alla vista del treno
//	    }
//
//	    
	    
	    @PostMapping("/valutaTreno")
	    public String valutaTreno(HttpSession session,
	                              @RequestParam("trenoId") Long trenoId,
	                              @RequestParam("voto") int voto,
	                              Model model) {

	        // Verifica se l'utente è loggato
	        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
	        if (utenteLoggato == null) {
	            return "redirect:/login"; // Reindirizza alla pagina di login se non è loggato
	        }

	        // Cerca il treno tramite l'ID
	        TrenoDTO trenoSelezionato = trenoService.findById(trenoId);
	        if (trenoSelezionato == null || trenoSelezionato.getIdTreno() == 0) {
	            throw new ValutazioneException("Il treno da te selezionato non è al momento presente.");
	        }

	        try {
	            // Chiama il metodo per la valutazione
	            valutazione.UserValutaTreno(utenteLoggato.getUserId(), trenoSelezionato.getIdTreno(), voto);

	            // Aggiorna i dati del treno e dell'utente dopo la valutazione
	            TrenoDTO trenoAggiornato = trenoService.findById(trenoSelezionato.getIdTreno());
	            UserDTO utenteAggiornato = userService.findById(utenteLoggato.getUserId());

	            // Aggiungi i dati aggiornati al modello
	            model.addAttribute("treno", trenoAggiornato);
	            model.addAttribute("utente", utenteAggiornato);
	            model.addAttribute("success", "Il treno è stato valutato con successo");

	            // Aggiorna i dati dell'utente nella sessione
	            sessione.setUtenteLoggato(session, utenteAggiornato);

	        } catch (ValutazioneException e) {
	            model.addAttribute("errorMessage", e.getMessage());
	            return "redirect:/treni/visualizza/treno";
	        }

	        return "redirect:/treni/visualizza/treno"; // Reindirizza alla vista del treno
	    }

	    
	    
	    
	    
}
	    
	    



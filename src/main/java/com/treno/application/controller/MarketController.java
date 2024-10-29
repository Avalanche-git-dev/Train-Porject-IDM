package com.treno.application.controller;

import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.treno.application.dto.AdminDTO;
import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.exception.FondiInsufficientiException;
import com.treno.application.exception.VenditoreAcquirenteNonTrovatoException;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.service.TransazioneService;
import com.treno.application.service.TrenoService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/market")
public class MarketController {

	@Autowired
	@Qualifier("TrenoService")
	private TrenoService trenoService;

	@Autowired
	@Qualifier("TransazioneService")
	private TransazioneService transazioneService;

	@Autowired
	@Qualifier("Sessione")
	private SessioneUtility sessione;

	@GetMapping
	public String mostraTreniInVendita(Model model, HttpSession session) {
		sessione.getUtenteLoggato(session);
		
		
		List<TrenoDTO> treniInVendita = trenoService.findTreniInVendita();
		model.addAttribute("treniInVendita", treniInVendita);
		return "market";
	}

	@PostMapping("/acquista")
	public String acquistaTreno(@RequestParam("idTreno") Long idTreno, RedirectAttributes redirectAttributes, HttpSession session) {
	if(sessione.isAdminLoggato(session)) {
		AdminDTO admin = sessione.getAdminLoggato(session);
		sessione.setUtenteLoggato(session, admin);
	}
			 
		try {
			transazioneService.compraTreno(sessione.getUtenteLoggato(session).getUserId(), idTreno);
		} catch (VenditoreAcquirenteNonTrovatoException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
		} catch (FondiInsufficientiException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		redirectAttributes.addFlashAttribute("succesMessage", "Acquisto completato");
		return "redirect:/market";
	}
	
	
	

	@GetMapping("/filtro")
	public String filtraTreni(@ModelAttribute("trenoFilter") TrenoFilter trenoFilter, Model model,
			HttpSession session) {
		// Verifica che l'utente sia loggato
		if (!sessione.isUtenteLoggato(session)) {
			return sessione.redirectTologin();
		}
		// Recupera l'utente loggato
		UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
		// Filtra i treni utilizzando il TrenoFilter
		List<TrenoDTO> treniFiltratiSet = trenoService.findTreniByFilter(trenoFilter);
		List<TrenoDTO> treniFiltrati = treniFiltratiSet.stream().collect(Collectors.toList());
		// Aggiungi la lista dei treni filtrati al modello
		model.addAttribute("treni", treniFiltrati);
		model.addAttribute("utenteLoggato", utenteLoggato);
		model.addAttribute("trenoFilter", trenoFilter);
		// Restituisci la vista del catalogo
		return "market";
	}

}

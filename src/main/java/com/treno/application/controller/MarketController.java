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
    private SessioneUtility sessioneUtility;
	
	@GetMapping
    public String mostraTreniInVendita(Model model, HttpSession session) {
		sessioneUtility.getUtenteLoggato(session);
        List<TrenoDTO> treniInVendita = trenoService.findTreniInVendita();
        model.addAttribute("treniInVendita", treniInVendita);
        return "market";
    }
	
	@PostMapping("/acquista")
	public String acquistaTreno(@RequestParam("idTreno") Long idTreno, Model model, HttpSession session) 
	        throws VenditoreAcquirenteNonTrovatoException, FondiInsufficientiException {
	    transazioneService.compraTreno(sessioneUtility.getUtenteLoggato(session).getUserId(), idTreno);
	    model.addAttribute("message", "Acquisto in corso");
	    return "transazione";
	}

	@PostMapping("/mettiInVendita")
    public String mettiTrenoInVendita(@RequestParam("idTreno") Long idTreno, 
                                      @RequestParam("prezzoVendita") Double prezzoVendita, 
                                      Model model, HttpSession session) {
        long idUtente = sessioneUtility.getUtenteLoggato(session).getUserId();
        String risultatoVendita = transazioneService.mettiInVendita(idUtente, idTreno, prezzoVendita);
        model.addAttribute("message", risultatoVendita);
        return "redirect:/market";
    }
	
	@GetMapping("/filtro")
    public String filtraTreni(@ModelAttribute("trenoFilter") TrenoFilter trenoFilter, Model model, HttpSession session) {
        // Verifica che l'utente sia loggato
        if (!sessioneUtility.isUtenteLoggato(session)) {
            return sessioneUtility.redirectTologin();
        }
        // Recupera l'utente loggato
        UserDTO utenteLoggato = sessioneUtility.getUtenteLoggato(session);
        // Filtra i treni utilizzando il TrenoFilter
        Set<TrenoDTO> treniFiltratiSet = trenoService.filtraTreni(trenoFilter);
        List<TrenoDTO> treniFiltrati = treniFiltratiSet.stream().collect(Collectors.toList());
        // Aggiungi la lista dei treni filtrati al modello
        model.addAttribute("treni", treniFiltrati);
        model.addAttribute("utenteLoggato", utenteLoggato);
        model.addAttribute("trenoFilter", trenoFilter);
        // Restituisci la vista del catalogo
        return "market";
    }
	
}

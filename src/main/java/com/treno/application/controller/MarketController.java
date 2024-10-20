package com.treno.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.exception.FondiInsufficientiException;
import com.treno.application.exception.VenditoreAcquirenteNonTrovatoException;
import com.treno.application.service.TransazioneService;
import com.treno.application.service.TrenoService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
public class MarketController {
	
	@Autowired
	@Qualifier("TrenoService")
	private TrenoService trenoService;
	
	@Autowired
	@Qualifier("TransazioneService")
	private TransazioneService transazioneService;
	
	@Autowired
    private SessioneUtility sessioneUtility;
	
	@GetMapping("/market")
    public String mostraTreniInVendita(Model model, HttpSession session) {
		sessioneUtility.getUtenteLoggato(session);
        List<TrenoDTO> treniInVendita = trenoService.findTreniInVendita();
        model.addAttribute("treniInVendita", treniInVendita);
        return "market";
    }
	
	@PostMapping("/transazione")
	public String acquistaTreno(@RequestParam("idTreno") Long idTreno, Model model, HttpSession session) 
	        throws VenditoreAcquirenteNonTrovatoException, FondiInsufficientiException {
	    transazioneService.compraTreno(sessioneUtility.getUtenteLoggato(session).getUserId(), idTreno);
	    model.addAttribute("message", "Acquisto in corso");
	    return "transazione";
	}

	
}

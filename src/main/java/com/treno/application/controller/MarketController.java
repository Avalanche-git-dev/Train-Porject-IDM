package com.treno.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.service.TrenoService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
public class MarketController {
	
	@Autowired
	@Qualifier("TrenoService")
	private TrenoService trenoService;
	
	//@Autowired
    //private SessioneUtility sessioneUtility;
	
	@GetMapping("/market")
    public String mostraTreniInVendita(Model model, HttpSession session) {
		//sessioneUtility.getUtenteLoggato(session);
        // Recupera la lista di treni in vendita
        List<TrenoDTO> treniInVendita = trenoService.findTreniInVendita();
        // Aggiungi la lista al modello per renderla disponibile nella vista
        model.addAttribute("treniInVendita", treniInVendita);
        // Ritorna il nome della vista "market.jsp"
        return "market";
    }

}

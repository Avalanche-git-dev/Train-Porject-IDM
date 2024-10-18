package com.treno.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.treno.application.dto.UserDTO;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private SessioneUtility sessioneUtility;

    @GetMapping
    public String dashboard(HttpSession session, Model model) {
    	
    	// Nuova tecnica 
        UserDTO utente = sessioneUtility.getUtenteLoggato(session);
        
        // L'interceptor assicura già che l'utente sia loggato, quindi l'utente non sarà mai null
        model.addAttribute("utente", utente);
        return "dashboard"; // Mostra la vista della dashboard
    }

    @GetMapping("/profilo")
    public String profilo(HttpSession session, Model model) {
        // Usa SessioneUtility per ottenere l'utente loggato
        UserDTO utente = sessioneUtility.getUtenteLoggato(session);

        model.addAttribute("utente", utente);
        return "profilo"; // Mostra la vista del profilo
    }

    @GetMapping("/treni")
    public String treni(HttpSession session, Model model) {
        // Usa SessioneUtility per ottenere l'utente loggato
        UserDTO utente = sessioneUtility.getUtenteLoggato(session);

        model.addAttribute("utente", utente);
        return "treni"; // Mostra la vista dei treni
    }

    @GetMapping("/market")
    public String market(HttpSession session, Model model) {
        // Usa SessioneUtility per ottenere l'utente loggato
        UserDTO utente = sessioneUtility.getUtenteLoggato(session);

        // L'interceptor garantisce che l'utente sia loggato
        model.addAttribute("utente", utente);
        return "market"; // Mostra la vista del mercato
    }
}

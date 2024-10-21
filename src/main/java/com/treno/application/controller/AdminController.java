/* package com.treno.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.treno.application.dto.UserDTO;
import com.treno.application.service.AdminService;
import com.treno.application.service.TrenoService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    @Qualifier("TrenoService")
    TrenoService trenoService;

    @Autowired
    @Qualifier("Sessione")
    private SessioneUtility sessione;
    
    @Autowired
    @Qualifier("AdminService")
    private AdminService adminService;

    @GetMapping
    public String mostraUtenti(HttpSession session, Model model) {
        UserDTO utenteDto = sessione.getUtenteLoggato(session);
        // Verifica che l'utente sia loggato
        if (!sessione.isUtenteLoggato(session)) {
            return sessione.redirectTologin();
        }
        // Recupera la lista di tutti gli utenti
        model.addAttribute("utenti", adminService.findAllUsers());
        // Aggiunge l'utente loggato al modello
        model.addAttribute("utenteLoggato", utenteDto);

        // Ritorna la vista "utenti"
        return "utenti";
    }
	
} */

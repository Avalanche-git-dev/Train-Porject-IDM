package com.treno.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treno.application.dto.UserDTO;
// import com.treno.application.service.AdminService;
import com.treno.application.service.TrenoService;
import com.treno.application.service.UserService;
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
    @Qualifier("UserService")
    private UserService userService;

    @GetMapping
    public String mostraUtenti(HttpSession session, Model model) {
        UserDTO utenteDto = sessione.getUtenteLoggato(session);
        // Verifica che l'utente sia loggato
        if (!sessione.isUtenteLoggato(session)) {
            return sessione.redirectTologin();
        }
        // Recupera la lista di tutti gli utenti e la aggiunge al modello
        model.addAttribute("listaUtenti", userService.findAllUsers());
        // Aggiunge l'utente loggato al modello
        model.addAttribute("utenteLoggato", utenteDto);
        // Ritorna la vista "admin"
        return "admin";
    }
    
    @PostMapping("/bloccaUtente")
    @ResponseBody
    public ResponseEntity<String> bloccaUtente(@RequestParam("userId") long userId) {
        userService.bloccaUser(userId);
        return ResponseEntity.ok("Utente bloccato con successo");
    }
    
    @PostMapping("/sbloccaUtente")
    @ResponseBody
    public ResponseEntity<String> sbloccaUtente(@RequestParam("userId") long userId) {
    	userService.sbloccaUser(userId);
    	return ResponseEntity.ok("Utente sbloccato con successo!");
    }	
	
}

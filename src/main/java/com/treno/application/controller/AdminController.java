package com.treno.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treno.application.dto.AdminDTO;
import com.treno.application.dto.TransazioneDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.service.TransazioneService;
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
    
    @Autowired
    @Qualifier("TransazioneService")
    private TransazioneService transazioneService;
    
    
    

    
    
    
//
//    @GetMapping
//    public String mostraAdmin(HttpSession session, Model model) {
//        AdminDTO admin = sessione.getAdminLoggato(session);
//        if (!sessione.isAdminLoggato(session)) {
//            return sessione.redirectTologin();
//        }
//        model.addAttribute("listaUtenti", userService.findAllUsers());
//        model.addAttribute("user", admin);
//        return "admin";
//    }
    
    
    @GetMapping
    public String mostraAdmin(HttpSession session, Model model) {
        AdminDTO admin = sessione.getAdminLoggato(session);
        
        if (!sessione.isAdminLoggato(session)) {
            return sessione.redirectTologin();
        }
        
        // Recupero dei dati da proiettare nel modello
        List<UserDTO> utenti = userService.findAllUsers(); // Tutti gli utenti
        List<UserDTO> utentiBloccati = userService.getAllLockedUsers(); // Utenti bloccati
        List<TransazioneDTO> transazioni = transazioneService.getTransazioniOrdinatePerDataRecente(); // Tutte le transazioni
        
        // Aggiunta dei dati al modello
        model.addAttribute("listaUtenti", utenti);
        model.addAttribute("listaUtentiBloccati", utentiBloccati);
        model.addAttribute("listaTransazioni", transazioni);
        model.addAttribute("user", admin);
        
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
    
    @GetMapping("/mostra/utente")
    public String mostraProfiloUtente(@PathVariable("userId") long userId, HttpSession session, Model model) {
        UserDTO utenteDto = sessione.getUtenteLoggato(session);
        if (!sessione.isUtenteLoggato(session)) {
            return sessione.redirectTologin();
        }
        UserDTO user = userService.findById(userId); // Assicurati di avere questo metodo nel tuo UserService
        model.addAttribute("userInfo", user);
        // Aggiunge l'utente loggato al modello
        model.addAttribute("utenteLoggato", utenteDto);
        return "profiloUtente";
    }
	
}

package com.treno.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treno.application.dto.AdminDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.exception.InvalidCredentialsException;
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
    
    
    
    @GetMapping("/login")
    public String mostralogin(@RequestParam(value = "sessioneScaduta", required = false) String sessioneScaduta, HttpSession session, Model model) {
        if (session != null) {
            session.invalidate();
        }
         
        model.addAttribute("AdminDto", new UserDTO());
        return "login"; 
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute("AdminDto") AdminDTO adminDto, Model model, HttpSession session) {
        try {
        	
            AdminDTO adminLoggato = adminService.login(adminDto);
            session.setAttribute("loggedAdmin", adminLoggato);
            return "admin";
            
        } catch (InvalidCredentialsException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
            
        }
    }
    
    
    
    

//    @GetMapping("/dashboard")
//    public String mostraUtenti(HttpSession session, Model model) {
//        UserDTO utenteDto = sessione.getUtenteLoggato(session);
//        if (!sessione.isUtenteLoggato(session)) {
//            return sessione.redirectTologin();
//        }
//        model.addAttribute("listaUtenti", adminService.findAllUsers());
//        model.addAttribute("utenteLoggato", utenteDto);
//        return "admin";
//    }
    
    @PostMapping("/bloccaUtente")
    @ResponseBody
    public ResponseEntity<String> bloccaUtente(@RequestParam("userId") long userId) {
        adminService.bloccaUser(userId);
        return ResponseEntity.ok("Utente bloccato con successo");
    }
    
    @PostMapping("/sbloccaUtente")
    @ResponseBody
    public ResponseEntity<String> sbloccaUtente(@RequestParam("userId") long userId) {
    	adminService.sbloccaUser(userId);
    	return ResponseEntity.ok("Utente sbloccato con successo!");
    }	
    
    @GetMapping("/profiloUtente/{userId}")
    public String mostraProfiloUtente(@PathVariable("userId") long userId, HttpSession session, Model model) {
        UserDTO utenteDto = sessione.getUtenteLoggato(session);
        if (!sessione.isUtenteLoggato(session)) {
            return sessione.redirectTologin();
        }
        UserDTO user = adminService.findById(userId); // Assicurati di avere questo metodo nel tuo UserService
        model.addAttribute("userInfo", user);
        // Aggiunge l'utente loggato al modello
        model.addAttribute("utenteLoggato", utenteDto);
        return "profiloUtente";
    }
	
}

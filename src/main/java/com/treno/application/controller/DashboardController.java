package com.treno.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("Sessione")
    private SessioneUtility sessioneUtility;

    
    
    @GetMapping
    public String dashboard(HttpSession session, Model model) {
        UserDTO utenteLoggato = sessioneUtility.getUtenteLoggato(session);
        if(!sessioneUtility.isUtenteLoggato(session)) {
        	return sessioneUtility.redirectTologin();
        }
        //sessioneUtility.setUtenteLoggato(session, utenteLoggato); se non modifico lo stato rimane li 
        model.addAttribute("utenteLoggato", utenteLoggato);
        return "dashboard";
    }
    
    
    
}




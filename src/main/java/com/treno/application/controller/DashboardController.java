package com.treno.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String dashboard(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        
        if (sessioneUtility.isUtenteGuest(session)) {
            
            return "redirect:/user/login"; // 
        }

        if (sessioneUtility.isUtenteLoggato(session)) {
            UserDTO utenteLoggato = sessioneUtility.getUtenteLoggato(session);
            model.addAttribute("utenteLoggato", utenteLoggato);
            return "dashboard";
        } else if (sessioneUtility.isAdminLoggato(session)) {
            return "redirect:/admin";
        }
        
        redirectAttributes.addFlashAttribute("errorMessage", "Non sei autorizzato ad accedere a questa sezione. Sei pregato di registrarti o eseguire il login per proseguire.");
        // Fallback per utenti loggati non previsti
        return "redirect:/user/login";
    }

}


package com.treno.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.treno.application.configuration.SessioneUtilityImpl;
import com.treno.application.dto.UserDTO;
import com.treno.application.exception.UserAlreadyExistsException;
import com.treno.application.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessioneUtilityImpl sessioneUtility;

    
    

    
    
    
    
    
    
    
    
    // Mostra il form di registrazione
    @GetMapping("/registrati")
    public String mostraFormRegistrazione( Model model) {
    	
    	UserDTO userDto = new UserDTO();
    	
        // Passiamo l'oggetto anche al modello per usarlo nella JSP
        model.addAttribute("userDto", userDto);
        return "registrati";  // <--------- 
    }
    
    
    
    @PostMapping("/registrazione")
    public String registerUser(@ModelAttribute("userDto") UserDTO userDto, HttpSession session, Model model) {
        try {
        	//Registrazione al Service
            userService.registra(userDto);
            //session.setAttribute("userLoggedIn", userDto);
            sessioneUtility.setUtenteLoggato(session, userDto); 
            return "redirect:/dashboard";  // Reindirizza alla dashboard
        } 
        catch (UserAlreadyExistsException e) {
        	//Imposta il messaggio di errore al model della vista, reindirizza sulla pagina stessa.
            model.addAttribute("errorMessage", e.getMessage());
            return "registrati";  // Torna alla pagina di registrazione
        }
    }


    
    	

}



package com.treno.application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.treno.application.dto.UserDTO;
import com.treno.application.service.UserService;
import com.treno.eccezzioni.InvalidPasswordException;
import com.treno.eccezzioni.UserAlreadyExistsException;
import com.treno.eccezzioni.UserNotFoundException;

import jakarta.servlet.http.HttpSession;

@Controller 
public class UserController {

    @Autowired
    @Qualifier("UserService")
    private UserService userService;

    // Mostra il form di registrazione
    @GetMapping("/registrati")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDTO());
        return "registrazione"; // Jsmettila di mettere l'estensione
    }

    // Registra un nuovo utente
    @PostMapping("/registrazione")
    public String registerUser(@ModelAttribute UserDTO userDto, HttpSession session) {
        try {
            userService.registra(userDto);
            return "redirect:/login"; // Reindirizza al login dopo la registrazione
        } catch (UserAlreadyExistsException e) {
            session.setAttribute("errorMessage", "Errore durante la registrazione: " + e.getMessage());
            return "registrazione"; // Ritorna alla pagina di registrazione se c'è un errore
        }
    }

    // Mostra il form di login
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userDto", new UserDTO());
        return "login"; // JSP o altra vista per il login, utilizzare il View Resolver
    }

    // Effettua il login dell'utente
//    @PostMapping("/login")
//    public String loginUser(@ModelAttribute("userDto") UserDTO userDto, Model model, HttpSession session) {
//        try {
//            userService.login(userDto);
//            
//            // Se il login ha successo, salva l'utente nella sessione
//            UserDTO utente = userService.findByUsername(userDto.getUsername());
//            session.setAttribute("utente", utente);
//
//            return "redirect:/dashboard"; 
//
//        } catch (UserNotFoundException e) {
//            model.addAttribute("error", "Utente non trovato.");
//            return "login"; 
//
//        } catch (InvalidPasswordException e) {
//            model.addAttribute("error", "Password errata.");
//            return "login"; 
//        }
//    }
    
    
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("userDto") UserDTO userDto, Model model, HttpSession session) {
        try {
            // Effettua il login e riceve un UserDTO se le credenziali sono corrette
            UserDTO utente = userService.login(userDto);

            // Se il login ha successo, salva l'utente nella sessione
            session.setAttribute("utente", utente);

            // Reindirizza alla dashboard
            return "redirect:/dashboard"; 

        } catch (UserNotFoundException e) {
            // Se l'utente non viene trovato, mostra un messaggio di errore
            model.addAttribute("error", "Utente non trovato.");
            return "login"; 

        } catch (InvalidPasswordException e) {
            // Se la password non è corretta, mostra un messaggio di errore
            model.addAttribute("error", "Password errata.");
            return "login"; 
        }
    }


    // Effettua il logout dell'utente
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        userService.logout(session);
        return "redirect:/login"; // Reindirizza al login dopo il logout
    }
}

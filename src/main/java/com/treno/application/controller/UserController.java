package com.treno.application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.treno.application.dto.UserDTO;
import com.treno.application.exception.InvalidPasswordException;
import com.treno.application.exception.UserAlreadyExistsException;
import com.treno.application.exception.UserNotFoundException;
import com.treno.application.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
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
    public String login(HttpSession session, Model model) {
        // Invalida eventuale sessione esistente
        if (session != null) {
            session.invalidate();
        }

        // Aggiunge un nuovo oggetto UserDTO al modello per il form di login
        model.addAttribute("userDto", new UserDTO());
        return "login"; // JSP o altra vista per il login, utilizzare il View Resolver
    }



    @PostMapping("/login")
    public String Dologin(@ModelAttribute("userDto") UserDTO userDto, Model model, HttpServletRequest request) {
        try {
            // Effettua il login e riceve un UserDTO se le credenziali sono corrette
            UserDTO utente = userService.login(userDto);

            // Invalida la sessione esistente e crea una nuova sessione
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession newSession = request.getSession(true);//HTTP SERVLET PERCHé non posso springboot mannaggia..
            newSession.setAttribute("utente", utente);

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
        // Invalidare la sessione corrente
        if (session != null) {
            session.invalidate(); // Rimuove tutti gli attributi e invalida la sessione
        }

        return "redirect:/login"; // Reindirizza alla pagina di login
    }
    @PostMapping("/logout")
    public String Dologout(HttpSession session) {
        if (session != null) {
            session.invalidate(); // Invalida la sessione corrente
        }
        return "redirect:/login"; // Reindirizza alla pagina di login
    }


}

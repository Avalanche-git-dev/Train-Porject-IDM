package com.treno.application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.treno.application.dto.UserDTO;
import com.treno.application.exception.InvalidCredentialsException;
import com.treno.application.exception.InvalidPasswordException;
import com.treno.application.exception.UserAlreadyExistsException;
import com.treno.application.exception.UserNotFoundException;
import com.treno.application.service.UserService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("UserService")
    private UserService userService;
    
    
    
    @Autowired
    @Qualifier("Sessione")
    private SessioneUtility sessione;

    
    
    
    // mostraRegistrati
    @GetMapping("/registrati")
    public String mostraRegistrazione(Model model) {
        model.addAttribute("userDto", new UserDTO());
        return "registrati"; // Jsmettila di mettere l'estensione
    }

    
    
    // doRegistrati
    @PostMapping("/registrati")
    public String doRegistrati(@ModelAttribute UserDTO userDto, HttpSession session) {
        try {
            userService.registra(userDto);
            sessione.setUtenteLoggato(session, userDto);
            return "redirect:/dashboard"; // Reindirizza al login dopo la registrazione
        } catch (UserAlreadyExistsException e) {
            session.setAttribute("errorMessage", "Errore durante la registrazione: " + e.getMessage());
            return "registrati"; // Ritorna alla pagina di registrazione se c'è un errore
        }
    }

    // mostraLogin
    @GetMapping("/login")
    public String mostralogin(HttpSession session, Model model) {
        // Invalida eventuale sessione esistente
        if (session != null) {
            session.invalidate();
        }

        // Aggiunge un nuovo oggetto UserDTO al modello per il form di login
        model.addAttribute("userDto", new UserDTO());
        return "login"; // JSP o altra vista per il login, utilizzare il View Resolver
    }

    // doLogin
    @PostMapping("/login")
    public String doLogin(@ModelAttribute("userDto") UserDTO userDto, Model model, HttpServletRequest request) {
        try {
        	
        	UserDTO utenteLoggato = userService.login(userDto);
        	
        	 HttpSession session = request.getSession(false); 
             if (session != null) {
                 session.invalidate();
             }
        	
            session = request.getSession(true);
            sessione.setUtenteLoggato(session, utenteLoggato);
            

            return "redirect:/dashboard";
            
        } catch (UserNotFoundException e) {
            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
            return "login";  // <-----
            
        } catch (InvalidPasswordException e) {
            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
            return "login";  // <-----
            
        } catch (InvalidCredentialsException e) {
            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
            return "login";  // <-----
        }
    }
    
    
    // Logout
    @PostMapping("/logout")
    public String Dologout(HttpSession session) {
        if (session != null) {
            session.invalidate(); // Invalida la sessione corrente
        }
        return sessione.redirectTologin(); // Reindirizza alla pagina di login
    }


}

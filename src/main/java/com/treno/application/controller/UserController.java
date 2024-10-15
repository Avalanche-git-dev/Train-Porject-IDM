package com.treno.application.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.treno.application.dto.UserDto;
import com.treno.application.service.UserService;

@Controller
public class UserController {

    @Autowired
    @Qualifier("UserService")
    private UserService userService;

    //form registrazione
//    @GetMapping("/registrati")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("userDto", new UserDto());
//        return "registrazione"; // JSP , html l'importante è smettere di mettere l'estensione
//    }
//
//    //dati registrazione effettiva.
//    @PostMapping("/registrati")
//    public String registra (@ModelAttribute("userDto") UserDto userDto, Model model) {
//        String risultato = userService.registra(userDto);
//        model.addAttribute("message", risultato);
//        
//        if (risultato.contains("successo")) {
//            return "login"; // Se la registrazione ha successo, si rimanda alla pagina di login
//        } else {
//            return "registrazione"; // Se fallisce, si rimanda alla stessa pagina con il messaggio d'errore
//        }
//    }

    // Get = mostra facciamo prima
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login.html"; // html o altra vista per il login
    }

    // Post = ricevi dal front end
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("userDto") UserDto userDto, Model model) {
        String risultato = userService.login(userDto);
        model.addAttribute("message", risultato);
    
		if (risultato.contains("successo")) {
			// Esempio di salvataggio dell'utente nella sessione (se necessario)
//			 session.setAttribute("utente",
//			 userService.findByUsername(userDto.getUsername()));
			return "dashboard"; // Redirect alla pagina successiva dopo il login (es. dashboard)
		} else {
			return "/login"; // Se fallisce, si rimanda alla pagina di login con messaggio d'errore
		}
	}
}

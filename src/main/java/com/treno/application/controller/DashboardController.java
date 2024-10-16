package com.treno.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.treno.application.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String Dashboard(HttpSession session, Model model) {
        UserDTO utente = (UserDTO) session.getAttribute("utente"); // Usa UserDTO
        if (utente == null) {
            return "redirect:/login"; // Reindirizza alla pagina di login
        }
        model.addAttribute("utente", utente); 
        return "dashboard"; // dashboard
    }

    @GetMapping("/profilo")
    public String Profilo(HttpSession session) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/login";
        }
        return "profilo"; // profilo
    }

    @GetMapping("/treni")
    public String Treni(HttpSession session) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/login";
        }
        return "treni"; // treni
    }

    @GetMapping("/market")
    public String Market(HttpSession session) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/login";
        }
        return "market"; // market
    }

    // Metodo privato per verificare se l'utente è autenticato
    private boolean isUserLoggedIn(HttpSession session) {
        UserDTO utente = (UserDTO) session.getAttribute("utente"); // Usa UserDTO
        return utente != null;
    }
}

package com.treno.application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.treno.application.dto.UserDTO;
import com.treno.application.service.TrenoService;
import com.treno.application.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/profilo")
public class ProfiloController {

    @Autowired
    @Qualifier("UserService")
    private UserService userService;

    @Autowired
    @Qualifier("TrenoService")
    private TrenoService trenoService;

    @GetMapping
    public String visualizzaProfilo(@SessionAttribute("utente") UserDTO utenteDto, Model model) {
        // Recupera le informazioni dell'utente dal database tramite UserDTO
        UserDTO userInfo = userService.findById(utenteDto.getUserId());

        // Aggiungi la lista dei treni e le informazioni dell'utente al model
        model.addAttribute("userInfo", userInfo);
        return "profilo"; // Mostra la vista del profilo
    }

    // Mostra il form per modificare il profilo dell'utente
    @GetMapping("/modifica")
    public String mostraFormModificaProfilo(@SessionAttribute("utente") UserDTO utenteDto, Model model) {
        UserDTO userInfo = userService.findById(utenteDto.getUserId());
        model.addAttribute("userInfo", userInfo);
        return "modificaProfilo"; // Mostra la vista per modificare il profilo
    }

    // Gestisce la modifica del profilo dell'utente
    @PostMapping("/modifica")
    public String modificaProfilo(@Valid @ModelAttribute("userInfo") UserDTO userDto, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors() || !userDto.getEmail().contains("@")) {
            model.addAttribute("error", "Email non valida o altri campi non validi.");
            return "modificaProfilo"; // Ritorna alla pagina di modifica con messaggio di errore
        }

        // Aggiorna le informazioni dell'utente nel database
        userService.update(userDto);

        // Aggiorna la sessione con i nuovi dati dell'utente
        session.setAttribute("utente", userDto);

        return "redirect:/profilo"; // Reindirizza alla vista del profilo aggiornata
    }
}

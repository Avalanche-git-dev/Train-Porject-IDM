package com.treno.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.treno.application.dto.TrenoDto;
import com.treno.application.model.User;
import com.treno.application.service.TrenoService;

@Controller
public class HomepageController {

    @Autowired
    @Qualifier("TrenoService")
    private TrenoService trenoService;

    // GET per visualizzare la homepage
    @GetMapping("/homepage")
    public String showHomepage(Model model) {
        model.addAttribute("trenoDto", new TrenoDto());
        return "homepage"; // Restituisce la vista della homepage
    }

    // POST per gestire la creazione del treno
    @PostMapping("/homepage")
    public String createTreno(@ModelAttribute("trenoDto") TrenoDto trenoDto, Model model) {
        User user = new User();
        user.setUserId(100L);
        try {
            trenoService.creaTreno(trenoDto.getSigla(), user, trenoDto.getMarca());
            model.addAttribute("message", "Treno creato con successo!");
        } catch (Exception e) {
            model.addAttribute("error", "Errore nella creazione del treno");
        }
        return "homepage";
    }
}

package com.treno.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController2 {

	@GetMapping("/")
    public String dashboard(Model model) {
        return "homepage"; // Mostra la vista della dashboard
    }
	
}

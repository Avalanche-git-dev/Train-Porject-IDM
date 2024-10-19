package com.treno.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController2 {

	@GetMapping("/homepage")
    public String homepage(Model model) {
        return "homepage";
    }
	
}

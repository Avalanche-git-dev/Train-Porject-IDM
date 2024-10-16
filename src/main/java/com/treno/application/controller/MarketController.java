package com.treno.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.treno.application.model.Treno;
import com.treno.application.service.TrenoService;

@Controller
@RequestMapping("/market")
public class MarketController {
	
	@Autowired
	@Qualifier("TrenoService")
	TrenoService trenoService;
	
	@GetMapping("/treniInVendita")
    public String mostraTreniInVendita(Model model) {
        // Recupera i treni in vendita
        List<Treno> treniInVendita = trenoService.findTreniInVendita();
        
        // Aggiungi i treni al modello per visualizzarli nella vista
        model.addAttribute("treniInVendita", treniInVendita);
        
        // Ritorna la pagina del marketplace
        return "marketplace"; 
    }
	
	

}

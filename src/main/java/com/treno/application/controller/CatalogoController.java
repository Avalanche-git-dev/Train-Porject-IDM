package com.treno.application.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.service.TrenoService;
import com.treno.application.service.UserService;
import com.treno.application.service.ValutazioneService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {
	
	@Autowired
	@Qualifier("ValutazioneService")
	private ValutazioneService valutazione;
	
	@Autowired
	@Qualifier("Sessione")
	private SessioneUtility sessione;
	
	
	@Autowired
	@Qualifier("TrenoService")
	private TrenoService trenoService;
	
	@Autowired
	@Qualifier("UserService")
	private UserService userService;
	
	
	
	
	
	    @GetMapping
	    public String getAllTreni(Model model, HttpSession session) {
	        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
	        
	        if(!sessione.isUtenteLoggato(session)) {
	        	return sessione.redirectTologin();
	        }
	        List<TrenoDTO> treniDto = trenoService.getAllTreni();
	        model.addAttribute("treni", treniDto);
	        model.addAttribute("utenteLoggato",utenteLoggato);
	        return "catalogo";
	    }
	    
	    
	    
	    
//	    // Applica il filtro sui treni
//	    @GetMapping("/filtro")
//	    public String filtraTreni(@ModelAttribute("filtro") TrenoFilter filtro, Model model, HttpSession session) {
//	        sessione.getUtenteLoggato(session);
//	        List<TrenoDTO> treniDto = trenoService.filtraTreni(filtro);
//	        model.addAttribute("treniDto", treniDto);
//	        model.addAttribute("filtro", filtro);
//	        return "visualizzaTreni";
//	    }
//	    
	    
	    

//	    @PostMapping("/filtra")
//	    public String filtraTreniPost(@ModelAttribute("trenoFilter") TrenoFilter trenoFilter, Model model, HttpSession session) {
//	        // Verifica che l'utente sia loggato
//	        if (!sessione.isUtenteLoggato(session)) {
//	            return sessione.redirectTologin();
//	        }
//
//	        // Recupera l'utente loggato
//	        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
//
//	        // Filtra i treni utilizzando il TrenoFilter
//	        List<TrenoDTO> treniFiltrati = trenoService.filtraTreni(trenoFilter);
//
//	        // Aggiungi la lista dei treni filtrati al modello
//	        model.addAttribute("treni", treniFiltrati);
//	        model.addAttribute("utenteLoggato", utenteLoggato);
//	        model.addAttribute("trenoFilter", trenoFilter);
//
//	        // Restituisci la vista del catalogo
//	        return "catalogo";
//	    }
//
//	    
	    
	    
	    @GetMapping("/filtro")
	    public String filtraTreni(@ModelAttribute("trenoFilter") TrenoFilter trenoFilter, Model model, HttpSession session) {
	        // Verifica che l'utente sia loggato
	        if (!sessione.isUtenteLoggato(session)) {
	            return sessione.redirectTologin();
	        }

	        // Recupera l'utente loggato
	        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);

	        // Filtra i treni utilizzando il TrenoFilter
	        Set<TrenoDTO> treniFiltratiSet = trenoService.filtraTreni(trenoFilter);
	        List<TrenoDTO> treniFiltrati = treniFiltratiSet.stream().collect(Collectors.toList());
	        
	        

	        // Aggiungi la lista dei treni filtrati al modello
	        model.addAttribute("treni", treniFiltrati);
	        model.addAttribute("utenteLoggato", utenteLoggato);
	        model.addAttribute("trenoFilter", trenoFilter);

	        // Restituisci la vista del catalogo
	        return "catalogo";
	    }

}

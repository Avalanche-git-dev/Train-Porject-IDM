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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.exception.ValutazioneException;
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
	
	
	
	
//	String plaintext = "your text here";
//	MessageDigest m = MessageDigest.getInstance("MD5");
//	m.reset();
//	m.update(plaintext.getBytes());
//	byte[] digest = m.digest();
//	BigInteger bigInt = new BigInteger(1,digest);            <----------------------------- Soluzione url con query string criptata.
//	String hashtext = bigInt.toString(16);     
//	// Now we need to zero pad it if you actually want the full 32 chars.
//	while(hashtext.length() < 32 ){
//	  hashtext = "0"+hashtext;
//	}
//	
	
	    @GetMapping
	    public String getAllTreni(Model model, HttpSession session) {
	        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
	        
//	        if(!sessione.isUtenteLoggato(session)) {
//	        	return sessione.redirectTologin();
//	        }
	        
	        
			/*
			 * if(sessione.isUtenteGuest(session)) { boolean permessi = false;
			 * model.addAttribute(permessi); }
			 */
	        	
	        	
	        sessione.setUtenteLoggato(session, utenteLoggato);	
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
//	        if (!sessione.isUtenteLoggato(session)) {
//	            return sessione.redirectTologin();
//	        }

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
	    
	    
	    
   
	    
	    //Versione redirect attributes per mandarmi i messaggi alla prossima vista , è colpa del redirect se i messaggi non vengono visualizzati correttamente 
	    
//	    
	    @PostMapping("/valutaTreno")
	    public String valutaTreno(HttpSession session,
	                              @RequestParam("trenoId") Long trenoId,
	                              @RequestParam("voto") int voto,
	                              Model model,
	                              RedirectAttributes redirectAttributes) {

	        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);

	        try {
	            TrenoDTO trenoSelezionato = trenoService.findById(trenoId);
	            if (trenoSelezionato == null || trenoSelezionato.getIdTreno() == 0) {
	                redirectAttributes.addFlashAttribute("errorMessage", "Il Treno da te selezionato non è al momento disponibile.");
	                return "redirect:/catalogo";
	            }

	            valutazione.UserValutaTreno(utenteLoggato.getUserId(), trenoSelezionato.getIdTreno(), voto);

	            // Aggiorna i dati del treno e dell'utente dopo la valutazione
	            //TrenoDTO trenoAggiornato = trenoService.findById(trenoSelezionato.getIdTreno()); // una varaibile in meno
	            UserDTO utenteAggiornato = userService.findById(utenteLoggato.getUserId());
	            
	            long idTreno = trenoService.findById(trenoSelezionato.getIdTreno()).getIdTreno();
	            
	            

	            // Passa i messaggi di successo come flash attribute
	            redirectAttributes.addFlashAttribute("successMessage", "Il treno è stato valutato con successo.");
	            redirectAttributes.addFlashAttribute("idTreno", idTreno);

	            // Aggiorna i dati dell'utente nella sessione
	            sessione.setUtenteLoggato(session, utenteAggiornato);

	        } catch (ValutazioneException e) {
	            // Passa i messaggi di errore come flash attribute
	            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
	            return "redirect:/catalogo";
	        }

	        return "redirect:/catalogo"; // Reindirizza alla vista del catalogo
	    }

	    
	    
	    
	    
}
	    
	    



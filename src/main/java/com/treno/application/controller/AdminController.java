package com.treno.application.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.treno.application.dto.AdminDTO;
import com.treno.application.dto.TransazioneDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.exception.AdminNotFoundException;
import com.treno.application.exception.FondiInsufficientiException;
import com.treno.application.exception.TransazioneNonTrovataException;
import com.treno.application.filter.UtenteFilter;
import com.treno.application.service.TransazioneService;
import com.treno.application.service.TrenoService;
import com.treno.application.service.UserService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    @Qualifier("TrenoService")
    TrenoService trenoService;

    @Autowired
    @Qualifier("Sessione")
    private SessioneUtility sessione;
    
    @Autowired
    @Qualifier("UserService")
    private UserService userService;
    
    @Autowired
    @Qualifier("TransazioneService")
    private TransazioneService transazioneService;
    
    
    

    
    
    
    
    
    @GetMapping
    public String mostraAdmin(HttpSession session, Model model) {
        AdminDTO admin = sessione.getAdminLoggato(session);
        
        if (!sessione.isAdminLoggato(session)) {
            return sessione.redirectTologin();
        }
        
        // Recupero dei dati da proiettare nel modello
        List<UserDTO> utenti = userService.findAllUsers(); // Tutti gli utenti
        List<UserDTO> utentiBloccati = userService.getAllLockedUsers(); // Utenti bloccati
        List<TransazioneDTO> transazioni = transazioneService.getTransazioniOrdinatePerDataRecente(); // Tutte le transazioni
        
        // Aggiunta dei dati al modello
        model.addAttribute("listaUtenti", utenti);
        model.addAttribute("listaUtentiBloccati", utentiBloccati);
        model.addAttribute("listaTransazioni", transazioni);
        model.addAttribute("user", admin);
        
        return "admin";
    }

    
    
    
    
    
    
    
    
//    @PostMapping("/bloccaUtente")
//    @ResponseBody
//    public ResponseEntity<String> bloccaUtente(@RequestParam("userId") long userId) {
//        userService.bloccaUser(userId);
//        return ResponseEntity.ok("Utente bloccato con successo");
//    }
//    
//    @PostMapping("/sbloccaUtente")
//    @ResponseBody
//    public ResponseEntity<String> sbloccaUtente(@RequestParam("userId") long userId) {
//    	userService.sbloccaUser(userId);
//    	return ResponseEntity.ok("Utente sbloccato con successo!");
//    }	
    
    @GetMapping("/mostra/utente")
    public String mostraProfiloUtente(@PathVariable("userId") long userId, HttpSession session, Model model) {
        UserDTO utenteDto = sessione.getUtenteLoggato(session);
        if (!sessione.isUtenteLoggato(session)) {
            return sessione.redirectTologin();
        }
        UserDTO user = userService.findById(userId); // Assicurati di avere questo metodo nel tuo UserService
        model.addAttribute("userInfo", user);
        // Aggiunge l'utente loggato al modello
        model.addAttribute("utenteLoggato", utenteDto);
        return "profiloUtente";
    }
    
    
    
    @GetMapping("/filtro")
    public String getRicercaByFiltro(@ModelAttribute ("UserFilter") UtenteFilter filtro, Model model, HttpSession session) {
        Set<UserDTO> utentiFiltrati = userService.filtraUtenti(filtro);

        model.addAttribute("listaUtenti", utentiFiltrati);

        return "admin";
    }

    

    
    @PostMapping("/blocca")
    public ModelAndView bloccaUser(@RequestParam("userId") long userId, RedirectAttributes redirectAttributes) {
        userService.bloccaUser(userId);
        redirectAttributes.addFlashAttribute("message", "L'utente è stato bloccato con successo.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return new ModelAndView("redirect:/admin"); // Reindirizza alla lista utenti dopo il blocco
    }

    @PostMapping("/sblocca")
    public ModelAndView sbloccaUser(@RequestParam("userId") long userId, RedirectAttributes redirectAttributes) {
        userService.sbloccaUser(userId);
        redirectAttributes.addFlashAttribute("message", "L'utente è stato sbloccato con successo.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return new ModelAndView("redirect:/admin"); // Reindirizza alla lista utenti dopo lo sblocco
    }
    
    
    
    
    
//    @PostMapping("/nominaAdmin")
//    public ModelAndView nominaAdmin(@RequestParam("userId") long userId) {
//        userService.nominaAdmin(userService.findById(userId));
//        return new ModelAndView("redirect:/admin"); // Reindirizza alla lista utenti dopo la nomina
//    }
//    
    
    @PostMapping("/annulla")
    public ModelAndView annullaTransazione(@RequestParam("idTransazione") long idTransazione, RedirectAttributes redirectAttributes) {
        try {
            transazioneService.annullaTransazione(idTransazione);
            redirectAttributes.addFlashAttribute("message", "Transazione annullata con successo.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (TransazioneNonTrovataException e) {
            redirectAttributes.addFlashAttribute("message", "Errore: transazione non trovata."+ e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
            e.printStackTrace();
        } catch (FondiInsufficientiException e) {
            redirectAttributes.addFlashAttribute("message", "Errore: fondi insufficienti per annullare la transazione."+ e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/admin");
    }
    
    
    @PostMapping("/nominaAdmin")
    public ModelAndView nominaAdmin(@RequestParam("userId") long userId, RedirectAttributes redirectAttributes) {
    	
    	try {
        UserDTO user = userService.findById(userId);
        
        // Controlla se l'utente è già un admin
        if (user instanceof AdminDTO) {
            redirectAttributes.addFlashAttribute("message", "Errore: l'utente è già un amministratore.");
            redirectAttributes.addFlashAttribute("messageType", "error");
        } else {
            // Nomina l'utente come admin
            userService.nominaAdmin(user);
            redirectAttributes.addFlashAttribute("message", "L'utente è stato nominato amministratore con successo.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        }
    	}
       catch (AdminNotFoundException e) {
            // Gestisce l'eccezione e aggiunge il messaggio di errore come attributo flash
            redirectAttributes.addFlashAttribute("message", "Errore durante la nomina dell'utente a amministratore: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }

        // Reindirizza alla lista utenti dopo la nomina
        return new ModelAndView("redirect:/admin");
    }
    
    
}

package com.treno.application.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.treno.application.dto.AdminDTO;
import com.treno.application.dto.TransazioneDTO;
import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.exception.AdminNotFoundException;
import com.treno.application.exception.FondiInsufficientiException;
import com.treno.application.exception.TransazioneNonTrovataException;
import com.treno.application.exception.UserNotFoundException;
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

    
    
    
    
    
    
    
    

    @GetMapping("/mostra/utente")
    public String mostraProfiloUtenteAdmin(HttpSession session, Model model, @RequestParam("userId") long userId, RedirectAttributes redirectAttributes) {
        try {
        	
            // Recupera l'utente tramite userId
            UserDTO utenteView = userService.findById(userId);
            if (utenteView == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "L'utente da te cercato non esiste.");
                return "redirect:/admin"; // Redirect con messaggio di errore
            }

            // Recupera la lista dei treni dell'utente tramite userId
            List<TrenoDTO> treniUtente =  trenoService.findAllTreniByUser(userId);

            // Memorizza i dati utente nella sessione per l'uso nella vista profiloUtente
            session.setAttribute("utenteView", utenteView);
            session.setAttribute("treniDto", treniUtente);

            // Aggiunge al modello i dati necessari per la vista profiloUtente
            model.addAttribute("username", utenteView.getUsername());
            model.addAttribute("nome", utenteView.getNome());
            model.addAttribute("numeroTreni", treniUtente.size());
            model.addAttribute("listaTreni", treniUtente);

        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Utente non trovato: " + e.getMessage());
            return "redirect:/admin"; // Redirect con messaggio di errore
        }

        return "profiloUtente"; // Ritorna direttamente alla vista del profilo utente
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
             
       
            userService.nominaAdmin(user);
            redirectAttributes.addFlashAttribute("message", "L'utente è stato nominato amministratore con successo.");
            redirectAttributes.addFlashAttribute("messageType", "success");
       // }
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
    
    
    
    
//    @GetMapping("/mostra/transazioni")
//    public ModelAndView controllaTransazioni(@RequestParam("userId") long userId, Model model, RedirectAttributes redirectAttributes) {
//        List<TransazioneDTO> transazioniUtente = transazioneService.getAllTransazioniByUser(userId);
//        
//        // Aggiungi la lista delle transazioni al modello
//        redirectAttributes.addFlashAttribute("transazioniUtente", transazioniUtente);
//        return new ModelAndView("admin"); // Nome della vista che contiene la modale
//    }
//    
    
    
    
    @GetMapping("/mostra/transazioni")
    public String mostraTransazioniUtente(@RequestParam("userId") long userId, Model model) {
        // Recupera le transazioni dell'utente
        List<TransazioneDTO> transazioniUtente = transazioneService.getAllTransazioniByUser(userId);

        // Aggiungi le transazioni al modello
        model.addAttribute("transazioniUtente", transazioniUtente);

        // Restituisci la vista del modale per le transazioni
        return "fragmentsModaleTransazioni";
    }

    
}

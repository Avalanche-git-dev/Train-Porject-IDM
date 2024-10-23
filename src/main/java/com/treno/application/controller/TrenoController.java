package com.treno.application.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.dto.UserGuest;
import com.treno.application.service.TrenoService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/treni")
public class TrenoController {

    @Autowired
    @Qualifier("TrenoService")
    TrenoService trenoService;

    @Autowired
    @Qualifier("Sessione")
    private SessioneUtility sessione;

    @GetMapping
    public String mostraTreni(HttpSession session, Model model) {
        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
        
//        if(!sessione.isUtenteLoggato(session)) {
//        	return sessione.redirectTologin();
//        }
        sessione.setUtenteLoggato(session, utenteLoggato);
       	
        	
        
        model.addAttribute("utenteLoggato", utenteLoggato);
        return "treni";
    }

   
    
    
    //Creazione Guest
    @GetMapping("/crea/guest")
    public String mostraCreaGuest(HttpSession session, Model model) {
        // Recupera l'utente guest dalla sessione
    	
    	UserGuest guest = (UserGuest) sessione.getUtenteLoggato(session);
    	if(sessione.isUtenteGuest(session)) {
        	model.addAttribute(guest);
        }
        	
        
       
       
        model.addAttribute("treno", new TrenoDTO());
        model.addAttribute("guest", guest);
        sessione.setUtenteLoggato(session, guest);

        // Restituisce la vista del form di creazione
        return "crea";
    }

    
    //Creazione Guest
    
    @PostMapping("/crea/guest")
    public String creaTrenoGuest(@RequestParam("nomeTreno") String nomeTreno, 
                                 @RequestParam("input") String input,
                                 @RequestParam("marca") String marca, 
                                 HttpSession session, 
                                 Model model) {
        // Recupera la lista dei treni dalla sessione, o creane una nuova se non esiste
        @SuppressWarnings("unchecked")
        List<TrenoDTO> treniGuest = (List<TrenoDTO>) session.getAttribute("treniGuest");
        if (treniGuest == null) {
            treniGuest = new ArrayList<>();
        }

        // Crea un nuovo TrenoDTO e popola i campi
        TrenoDTO treno = new TrenoDTO();
        treno.setNome(nomeTreno);
        treno.setSigla(input);
        treno.setMarca(marca);

        // Aggiungi il nuovo treno alla lista
        treniGuest.add(treno);

        // Salva la lista aggiornata nella sessione
        session.setAttribute("treniGuest", treniGuest);

        // Mostra di nuovo la pagina per creare altri treni o andare alla registrazione
        model.addAttribute("treniGuest", treniGuest);
        
        // Recupera l'utente guest dalla sessione e aggiorna lo stato dell'utente loggato
        UserGuest guest = (UserGuest) session.getAttribute("utenteGuest");
        sessione.setUtenteLoggato(session, guest);
        
        return "crea"; // Rimani sulla stessa pagina per creare altri treni
    }

    
    
    
    
    // <-----------------------------------------------
    
   
    
    
    
    
    // Mostra il form per la creazione di un nuovo treno
    @GetMapping("/crea")
    public String mostraFormCreazioneTreno(HttpSession session, Model model) {
        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
//        
//        if(sessione.isUtenteGuest(session)) {
//        	UserGuest guest = (UserGuest) sessione.getUtenteLoggato(session);
//        	boolean permessi = false;
//        	model.addAttribute(permessi);
//        	model.addAttribute(guest);
//        	return "crea";
//        }
//        
//        else {
//        
        model.addAttribute("utenteLoggato", utenteLoggato);
        model.addAttribute("treno", new TrenoDTO());
        return "crea";
        }
//    }

    
    

    
    
    
    
    
    
    
    
    // Gestisce la creazione del treno
    @PostMapping("/crea")
    public String creaTreno(@RequestParam("nomeTreno") String nomeTreno, @RequestParam("input") String input,
                            @RequestParam("marca") String marca, HttpSession session) {
        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
        TrenoDTO trenoCreato = new TrenoDTO();
        trenoCreato.setNome(nomeTreno);
        trenoCreato.setSigla(input);
        trenoCreato.setMarca(marca);
        trenoService.creaTreno(trenoCreato, utenteLoggato);
        return "redirect:/treni";
    }
    
    

    // Visualizza i TUTTI treni di un utente specifico
    @GetMapping("/visualizza")
    public String visualizzaTreniPerUtente(Model model, HttpSession session) {
        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
        if (!sessione.isUtenteLoggato(session)) {
            return sessione.redirectTologin();
        }
        String immagineTreno="/ProgettoTreno/resources/images/treni/trenoTedesco.jpg";
        session.setAttribute("immagineTreno",immagineTreno);
        Long ownerId = utenteLoggato.getUserId();
        String usernameOwner = utenteLoggato.getUsername();
        // List<TrenoDTO> treniDto = trenoService.findAllTreniByUser(OwnerId);
        List<TrenoDTO> treniDto = trenoService.findTreniByUserEscludiInVendita(ownerId);
        model.addAttribute("treniDto", treniDto);
        session.setAttribute("usernameOwner",usernameOwner);
        model.addAttribute("ownerId", ownerId);
        return "visualizzaTreni";
    }



    
//    // Visualizzazione di un treno.
//    @GetMapping("/visualizza/treno")
//    public String visualizzaTreno(@ModelAttribute("treno") TrenoDTO trenoSelezionato, Model model, HttpSession session) {
//        // Verifica che l'utente sia loggato
//        if (!sessione.isUtenteLoggato(session)) {
//            return sessione.redirectTologin();
//        }
//        
//        if (trenoSelezionato == null) {
//        	model.addAttribute("errorMessage","Il treno da te cercato non è piu disponibile, contatta l'admin. ");// Se non ci sono informazioni sul treno, reindirizza al catalogo
//            return "redirect:/catalogo";
//        }
//        
//        trenoSelezionato = trenoService.findById(trenoSelezionato.getIdTreno());
//
//        model.addAttribute("treno", trenoSelezionato);
//        model.addAttribute("ownerId", trenoSelezionato.getIdOwner());
//        return "dettagliTreno";  // Restituisce la vista dei dettagli del treno
//    }
    
    
    // O post con il dto o get con id tramite post nascosto  .
    @GetMapping("/visualizza/treno")
    public String visualizzaTreno(HttpSession session, Model model) {
        // Recupera l'ID del treno dalla sessione
        Long idTreno = (Long) session.getAttribute("trenoSelezionato");

        if (idTreno == null) {
            model.addAttribute("errorMessage", "Seleziona un treno dalla collezione.");
            return "redirect:/catalogo";
        }

        // Recupera i dettagli del treno tramite il servizio
        TrenoDTO trenoSelezionato = trenoService.findById(idTreno);
        if (trenoSelezionato == null) {
            model.addAttribute("errorMessage", "Il treno non è più disponibile.");
            return "redirect:/catalogo";
        }

        // Aggiungi i dettagli del treno al modello
        model.addAttribute("treno", trenoSelezionato);
        model.addAttribute("ownerId", trenoSelezionato.getIdOwner());

        return "dettagliTreno";  // Restituisce la vista dei dettagli del treno
    }


    
    @PostMapping("/visualizza/treno")
    public String selezionaTreno(@RequestParam("idTreno") Long idTreno, HttpSession session) {
        // Salva l'ID del treno nella sessione
        session.setAttribute("trenoSelezionato", idTreno);
        // Reindirizza alla pagina che mostra i dettagli del treno senza esporre l'ID nell'URL
        return "redirect:/treni/visualizza/treno";
    }


    




    
////////////////////////////// FUNZIONALITA IN PROVA.     



    // Modifica un treno
    @GetMapping("/modifica/treno")
    public String modificaTreno(@PathVariable Long idTreno, Model model, HttpSession session) {
        sessione.getUtenteLoggato(session);
        TrenoDTO trenoDto = trenoService.findById(idTreno);
        if (trenoDto == null) {
            return "redirect:/treni/catalogo";
        }
        model.addAttribute("treno", trenoDto);
        return "modificaTreno";
    }

    
    
    
    // Salva le modifiche al treno
    @PostMapping("/salvaModifica")
    public String salvaModifica(@ModelAttribute("treno") TrenoDTO trenoDto) {
        trenoService.update(trenoDto);
        return "redirect:/treni/dettagli/" + trenoDto.getIdTreno();
    }

    
    


    
    
}

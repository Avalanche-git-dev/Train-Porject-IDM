package com.treno.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
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

    // Pagina Treni (login interceptor + sessione utility per recuperare sempre userDTO dalla sessione
    @GetMapping
    public String mostraTreni(HttpSession session, Model model) {
        UserDTO utenteDto = sessione.getUtenteLoggato(session);
        
        if(!sessione.isUtenteLoggato(session)) {
        	return sessione.redirectTologin();
        }
        
        model.addAttribute("utenteLoggato", utenteDto);
        return "treni";
    }

    // Mostra il form per la creazione di un nuovo treno
    @GetMapping("/crea")
    public String mostraFormCreazioneTreno(HttpSession session, Model model) {
        UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
        
        if(!sessione.isUtenteLoggato(session)) {
        	return sessione.redirectTologin();
        }
        
        model.addAttribute("utenteLoggato", utenteLoggato);
        model.addAttribute("treno", new TrenoDTO());
        return "crea";
    }

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
        Long ownerId = utenteLoggato.getUserId();
        // List<TrenoDTO> treniDto = trenoService.findAllTreniByUser(OwnerId);
        List<TrenoDTO> treniDto = trenoService.findTreniByUserEscludiInVendita(ownerId);
        model.addAttribute("treniDto", treniDto);
        model.addAttribute("ownerId", ownerId);
        return "visualizzaTreni";
    }

    // Visualizza tutti i treni disponibili
//    @GetMapping("/catalogo")
//    public String getAllTreni(Model model, HttpSession session) {
//        sessione.getUtenteLoggato(session);
//        List<TrenoDTO> treniDto = trenoService.getAllTreni();
//        model.addAttribute("treni", treniDto);
//        return "catalogo";
//    }


    
    
    @GetMapping("/visualizza/treno")
    public String visualizzaTreno(@ModelAttribute("treno") TrenoDTO trenoSelezionato, Model model, HttpSession session) {
        // Verifica che l'utente sia loggato
        if (!sessione.isUtenteLoggato(session)) {
            return sessione.redirectTologin();
        }
        
        if (trenoSelezionato == null) {
        	model.addAttribute("errorMessage","Il treno da te cercato non è piu disponibile, contatta l'admin. ");// Se non ci sono informazioni sul treno, reindirizza al catalogo
            return "redirect:/catalogo";
        }
       // TrenoDTO trenoSelezionato = trenoService.findById(trenoSelezionato.getIdTreno());

        model.addAttribute("treno", trenoSelezionato);
        model.addAttribute("ownerId", trenoSelezionato.getIdOwner());
        return "dettagliTreno";  // Restituisce la vista dei dettagli del treno
    }
    
    @GetMapping("/dettagli/{idTreno}")
    public String visualizzaDettagliTreno(@PathVariable("idTreno") Long idTreno, Model model, HttpSession session) {
        // Verifica che l'utente sia loggato
        if (!sessione.isUtenteLoggato(session)) {
            return sessione.redirectTologin();
        }
        // Recupera il treno dal servizio usando l'ID
        TrenoDTO trenoSelezionato = trenoService.findById(idTreno);
        // Se il treno non esiste, mostra un messaggio di errore
        if (trenoSelezionato == null) {
            model.addAttribute("errorMessage", "Il treno da te cercato non è disponibile, contatta l'amministratore.");
            return "redirect:/catalogo"; // Reindirizza alla pagina del catalogo
        }
        model.addAttribute("treno", trenoSelezionato);
        model.addAttribute("ownerId", trenoSelezionato.getIdOwner());
        return "dettagliTreno";
    }

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

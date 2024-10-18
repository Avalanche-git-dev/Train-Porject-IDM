package com.treno.application.controller;

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
import com.treno.application.filter.TrenoFilter;
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
    private SessioneUtility sessioneUtility;

    // Pagina Treni (login interceptor + sessione utility per recuperare sempre userDTO dalla sessione
    @GetMapping
    public String treni(HttpSession session, Model model) {
        UserDTO utenteDto = sessioneUtility.getUtenteLoggato(session);
        model.addAttribute("utente", utenteDto);
        return "treni";
    }

    // Mostra il form per la creazione di un nuovo treno
    @GetMapping("/crea")
    public String mostraFormCreazioneTreno(HttpSession session, Model model) {
        UserDTO utenteDto = sessioneUtility.getUtenteLoggato(session);
        model.addAttribute("utente", utenteDto);
        model.addAttribute("treno", new TrenoDTO());
        return "crea";
    }

    // Gestisce la creazione del treno
    @PostMapping("/crea")
    public String creaTreno(@RequestParam("nomeTreno") String nomeTreno, @RequestParam("input") String input,
                            @RequestParam("marca") String marca, HttpSession session) {
        UserDTO utenteDto = sessioneUtility.getUtenteLoggato(session);
        TrenoDTO trenoDto = new TrenoDTO();
        trenoDto.setNome(nomeTreno);
        trenoDto.setSigla(input);
        trenoDto.setMarca(marca);
        trenoService.creaTreno(trenoDto, utenteDto);
        return "redirect:/treni";
    }

    // Visualizza i treni di un utente specifico
    @GetMapping("/visualizza")
    public String visualizzaTreniPerUtente(Model model, HttpSession session) {
        UserDTO utenteDto = sessioneUtility.getUtenteLoggato(session);
        Long userId = utenteDto.getUserId();
        List<TrenoDTO> treniDto = trenoService.findAllTreniByUser(userId);
        model.addAttribute("treniDto", treniDto);
        model.addAttribute("ownerId", userId);
        return "visualizzaTreni";
    }

    // Visualizza tutti i treni disponibili
    @GetMapping("/catalogo")
    public String getAllTreni(Model model, HttpSession session) {
        sessioneUtility.getUtenteLoggato(session);
        List<TrenoDTO> treniDto = trenoService.getAllTreni();
        model.addAttribute("treni", treniDto);
        return "catalogo";
    }

    // Mostra i dettagli del treno
    @GetMapping("/dettagli/{idTreno}")
    public String dettagliTreno(@PathVariable("idTreno") Long idTreno, Model model, HttpSession session) {
        sessioneUtility.getUtenteLoggato(session);
        TrenoDTO trenoDto = trenoService.findById(idTreno);
        if (trenoDto == null) {
            return "redirect:/treni/catalogo";
        }
        model.addAttribute("treno", trenoDto);
        model.addAttribute("ownerId", trenoDto.getIdOwner());
        return "dettagliTreno";
    }

    // Modifica un treno
    @GetMapping("/modifica/{idTreno}")
    public String modificaTreno(@PathVariable Long idTreno, Model model, HttpSession session) {
        sessioneUtility.getUtenteLoggato(session);
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

    // Applica il filtro sui treni
    @GetMapping("/filtro")
    public String filtraTreni(@ModelAttribute("filtro") TrenoFilter filtro, Model model, HttpSession session) {
        sessioneUtility.getUtenteLoggato(session);
        List<TrenoDTO> treniDto = trenoService.filtraTreni(filtro);
        model.addAttribute("treniDto", treniDto);
        model.addAttribute("filtro", filtro);
        return "visualizzaTreni";
    }

    // Filtra tutti i treni disponibili
    @GetMapping("/tutti/filtro")
    public String filtraTreniTutti(@ModelAttribute("filtro") TrenoFilter filtro, Model model, HttpSession session) {
        sessioneUtility.getUtenteLoggato(session);
        List<TrenoDTO> treniDto = trenoService.filtraTreni(filtro);
        model.addAttribute("treni", treniDto);
        return "tutti";
    }
    
    
    
//    @GetMapping("/dettagli/{idTreno}/valutazioni")
//    public String getAllValutazioniByTreno(@PathVariable("idTreno") Long idTreno, Model model, HttpSession session) {
//        UserDTO utenteDto = sessioneUtility.getUtenteLoggato(session);
//        
//        // Recupera tutte le valutazioni del treno tramite il service
//        //List<ValutazioneDTO> valutazioniDto = trenoService.getAllValutazioniByTreno(idTreno);
//        
//        model.addAttribute("utente", utenteDto);
//        model.addAttribute("valutazioni", valutazioniDto);
//        model.addAttribute("idTreno", idTreno);
//        
//        return "valutazioniTreno"; // Vista che mostra le valutazioni del treno
//    }
    
    
//    @PostMapping("/dettagli/{idTreno}/valuta")
//    public String valutaTreno(@PathVariable("idTreno") Long idTreno,
//                              @RequestParam("valutazione") int valutazione,
//                              @RequestParam("commento") String commento,
//                              HttpSession session) {
//        UserDTO utenteDto = sessioneUtility.getUtenteLoggato(session);
//
//        ValutazioneDTO valutazioneDto = new ValutazioneDTO();
//        valutazioneDto.setIdTreno(idTreno);
//        valutazioneDto.setIdUtente(utenteDto.getUserId());
//        valutazioneDto.setValutazione(valutazione);
//        valutazioneDto.setCommento(commento);
//        
//        // Salva la valutazione tramite il service
//        trenoService.valutaTreno(valutazioneDto);
//        
//        return "redirect:/treni/dettagli/" + idTreno; // Ritorna ai dettagli del treno
//    }


    
    
}

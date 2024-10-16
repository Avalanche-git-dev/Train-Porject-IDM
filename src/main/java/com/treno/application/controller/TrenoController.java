package com.treno.application.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.treno.application.model.Treno;
import com.treno.application.service.TrenoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/treni")
public class TrenoController {

    @Autowired
    @Qualifier("TrenoService")
    TrenoService trenoService;

    // Controllo accesso pagina solo in sessione di login
    @GetMapping
    public String treni(HttpSession session, Model model) {
        UserDTO utenteDto = (UserDTO) session.getAttribute("utente");

        if (utenteDto == null) {
            return "redirect:/login";
        }

        model.addAttribute("utente", utenteDto);
        return "treni"; // dashboard
    }

    // Metodo per mostrare la pagina per creare un nuovo treno
    @GetMapping("/crea")
    public String mostraFormCreazioneTreno(HttpSession session, Model model) {
        UserDTO utenteDto = (UserDTO) session.getAttribute("utente");

        if (utenteDto == null) {
            return "redirect:/login";
        }

        model.addAttribute("treno", new TrenoDTO()); // Se non gli do una istanza giustamente non va .
        return "crea";
    }

    // Metodo per gestire la creazione del treno
    @PostMapping("/crea")
    public String creaTreno(@RequestParam("nomeTreno") String nomeTreno, @RequestParam("input") String input, @RequestParam("marca") String marca,
                            HttpSession session, Model model) {
        UserDTO utenteDto = (UserDTO) session.getAttribute("utente");

        if (utenteDto == null) {
            return "redirect:/login";
        }

        // Crea un oggetto TrenoDTO e impostalo con i valori inseriti dall'utente
        TrenoDTO trenoDto = new TrenoDTO();
        trenoDto.setNome(nomeTreno); // Nome del treno dall'input dell'utente
        trenoDto.setSigla(input);    // Sigla del treno
        trenoDto.setMarca(marca);    // Marca del treno

        // Chiama il servizio per creare il treno
        trenoService.creaTreno(trenoDto, utenteDto);

        return "redirect:/treni"; // Reindirizza alla lista dei treni o alla dashboard
    }


    // Visualizza treni di un utente in base alla sessione di login
    @GetMapping("/visualizza")
    public String visualizzaTreniUtente(HttpSession session, Model model) {
        // Recupera l'utente dalla sessione
        UserDTO utenteDto = (UserDTO) session.getAttribute("utente");

        if (utenteDto == null) {
            return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
        }

        // Recupera i treni dell'utente dal servizio usando il suo ID
        List<Treno> treni = trenoService.findAllTreniByUser(utenteDto.getUserId());

        // Mappa ogni Treno su TrenoDTO
        List<TrenoDTO> treniDto = treni.stream().map(treno -> {
            // Crea un'istanza vuota di TrenoDto
            TrenoDTO dto = new TrenoDTO();

            // Imposta i campi di TrenoDto usando i metodi set
            dto.setIdTreno(treno.getIdTreno());
            dto.setInVendita(treno.getInVendita());
            dto.setImmagine(treno.getImmagine());
            dto.setMarca(treno.getMarca());
            dto.setMediaValutazioni(treno.getMediaValutazioni());
            dto.setPesoTotale(treno.getPeso());
            dto.setPostiTotali(treno.getPostiTotali());

            // Restituisci l'oggetto dto popolato
            return dto;
        }).collect(Collectors.toList());

        // Passa la lista dei DTO alla vista
        model.addAttribute("treniDto", treniDto);
        model.addAttribute("utente", utenteDto); // Passa anche l'utente alla vista per eventuali altre informazioni
        return "visualizzaTreni"; // Restituisce la vista per la lista dei treni
    }

    // Filtro treni
    @GetMapping("/filtro")
    public String filtraTreni(@ModelAttribute("filtro") TrenoFilter filtro, Model model, HttpSession session) {
        UserDTO utenteDto = (UserDTO) session.getAttribute("utente");

        if (utenteDto == null) {
            return "redirect:/login";
        }

        long userId = utenteDto.getUserId();

        // Usa il service per filtrare i treni
        List<Treno> treniFiltrati = trenoService.filtraTreni(filtro, userId);

        // Converti la lista di treni in DTO
        List<TrenoDTO> treniDto = treniFiltrati.stream().map(treno -> {
            TrenoDTO dto = new TrenoDTO();
            dto.setIdTreno(treno.getIdTreno());
            dto.setImmagine(treno.getImmagine());
            dto.setMediaValutazioni(treno.getMediaValutazioni());
            dto.setMarca(treno.getMarca());
            dto.setPesoTotale(treno.getPeso());
            dto.setPostiTotali(treno.getPostiTotali());
            return dto;
        }).collect(Collectors.toList());

        // Aggiungi la lista filtrata al model per il rendering nella JSP
        model.addAttribute("treniDto", treniDto);
        model.addAttribute("filtro", filtro); // Rendi disponibile il filtro corrente nella pagina

        return "visualizzaTreni"; // Il nome della JSP
    }

    @ModelAttribute("filtro")
    public TrenoFilter initFilter() {
        return new TrenoFilter(); // Inizializza un filtro vuoto
    }

    // Dettagli del treno
    @GetMapping("/dettagli/{id}")
    public String dettagliTreno(@PathVariable Long id, Model model) {
        Treno treno = trenoService.findById(id); // Recupera il treno dal database
        TrenoDTO trenoDto = new TrenoDTO();
        trenoDto.setIdTreno(treno.getIdTreno());
        trenoDto.setImmagine(treno.getImmagine());
        trenoDto.setMarca(treno.getMarca());
        trenoDto.setMediaValutazioni(treno.getMediaValutazioni());
        trenoDto.setPesoTotale(treno.getPeso());
        trenoDto.setPostiTotali(treno.getPostiTotali());
        model.addAttribute("treno", trenoDto); // Passa il treno alla vista
        return "dettagliTreno"; // Restituisce la vista
    }

    // Modifica treno
    @GetMapping("/modifica/{id}")
    public String modificaTreno(@PathVariable Long id, Model model) {
        Treno treno = trenoService.findById(id); // Recupera il treno da modificare
        TrenoDTO trenoDto = new TrenoDTO();
        trenoDto.setIdTreno(treno.getIdTreno());
        trenoDto.setImmagine(treno.getImmagine());
        trenoDto.setMarca(treno.getMarca());
        trenoDto.setInVendita(treno.getInVendita());
        model.addAttribute("treno", trenoDto); // Passa il treno al model
        return "modificaTreno"; // Mostra la pagina di modifica
    }

    @PostMapping("/salvaModifica")
    public String salvaModifica(@ModelAttribute("treno") TrenoDTO trenoDto) {
        Treno treno = trenoService.findById(trenoDto.getIdTreno());
        treno.setMarca(trenoDto.getMarca());
        treno.setInVendita(trenoDto.isInVendita());
        treno.setImmagine(trenoDto.getImmagine());
        trenoService.update(treno); // Metodo per salvare le modifiche
        return "redirect:/treni/dettagli/" + treno.getIdTreno(); // Reindirizza ai dettagli del treno modificato
    }
}

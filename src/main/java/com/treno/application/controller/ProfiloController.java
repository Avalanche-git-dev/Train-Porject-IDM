package com.treno.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.treno.application.dto.UserDTO;
import com.treno.application.exception.InvalidCredentialsException;
import com.treno.application.exception.UserNotFoundException;
import com.treno.application.service.UserService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/profilo")
public class ProfiloController {

	@Autowired
	@Qualifier("UserService")
	private UserService userService;

	@Autowired
	@Qualifier("Sessione")
	private SessioneUtility sessione;

	// Mostra la vista del profilo utente
	@GetMapping
	public String mostraProfilo(HttpSession session, Model model) {

		UserDTO userInfo = sessione.getUtenteLoggato(session);
		
		if(!sessione.isUtenteLoggato(session)) {
			return sessione.redirectTologin();
		}
		try {
			sessione.setUtenteLoggato(session, userInfo);
			userInfo = userService.findByUsername(userInfo.getUsername());
			session.setAttribute("utente", userInfo);
			model.addAttribute("userInfo", userInfo);
		} catch (UserNotFoundException e) {
			model.addAttribute("error", e.getMessage());
			return sessione.redirectTologin();
		}
		return "profilo";
	}

	// Per ora troppo semplice
	@PostMapping("/elimina")
	public String eliminaAccount(HttpSession session) {
		UserDTO user = sessione.getUtenteLoggato(session);
		try {
			userService.cancellaAccount(user);
			session.invalidate();
		} catch (UserNotFoundException e) {
			return sessione.redirectTologin();
		}
		return sessione.redirectTologin();
	}

    // /portafoglio/aggiungi flessiblità per /portafoglio un giorno 
	@PostMapping("/portafoglio/aggiungi")
	public String aggiungiDenaro(@RequestParam("importo") double importo, HttpSession session, Model model) {
	    UserDTO user = sessione.getUtenteLoggato(session);
	    if (user == null) {
	        model.addAttribute("error", "Utente non trovato. Per favore, prova ad effettuare nuovamente il login.");
	        return sessione.redirectTologin();
	    }

	    if (importo <= 0) {
	        model.addAttribute("error", "L'importo deve essere maggiore di zero.");
	        model.addAttribute("userInfo", user); // Aggiungi l'utente al modello per la vista
	        return "profilo";
	    }

	    try {
	        user.setPortafoglio(user.getPortafoglio() + importo);
	        userService.update(user); // Questo chiamerà il metodo `update()` con la logica aggiornata
	        session.setAttribute("utente", user); // Aggiorna la sessione
	        model.addAttribute("userInfo", user); // Aggiungi l'utente al modello per aggiornare la vista immediatamente
	    } catch (UserNotFoundException e) {
	        model.addAttribute("error", "Utente non trovato durante l'aggiornamento del portafoglio. Per favore, prova ad effettuare nuovamente il login.");
	        model.addAttribute("userInfo", user); // Aggiungi l'utente al modello per la vista
	        return "profilo";
	    }

	    // Aggiungi messaggio di successo e ritorna la vista del profilo direttamente
	    model.addAttribute("successMessage", "Denaro aggiunto con successo al portafoglio.");
	    return "profilo";
	}


	
	//Troppo blindato
//	
//	@PostMapping("/modifica")
//	public String modificaProfilo(@RequestParam(value = "passwordVecchia", required = false) String passwordVecchia,
//	                              @RequestParam(value = "passwordNuova", required = false) String passwordNuova,
//	                              @RequestParam(value = "email", required = false) String email,
//	                              @RequestParam(value = "telefono", required = false) String telefono, 
//	                              HttpSession session, Model model) throws Exception {
//
//	    // Verifica se l'utente è loggato
//	    if (!sessione.isUtenteLoggato(session)) {
//	        return sessione.redirectTologin();
//	    }
//
//	    UserDTO currentUser = sessione.getUtenteLoggato(session);
//	    boolean hasErrors = false;
//	    StringBuilder errorMessage = new StringBuilder();
//
//	    // Validazione della nuova password (solo se fornita)
//	    if (passwordNuova != null && !passwordNuova.isEmpty()) {
//	        if (passwordNuova.length() < 6) {
//	            hasErrors = true;
//	            errorMessage.append("La nuova password deve avere almeno 6 caratteri.<br/>");
//	        }
//	    }
//
//	    // Validazione dell'email (solo se fornita)
//	    if (email != null && !email.isEmpty()) {
//	        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
//	            hasErrors = true;
//	            errorMessage.append("L'email inserita non è valida.<br/>");
//	        }
//	    }
//
//	    // Validazione del telefono (solo se fornito)
//	    if (telefono != null && !telefono.isEmpty()) {
//	        if (!telefono.matches("\\d{10}")) { // Controllo telefono per 10 cifre
//	            hasErrors = true;
//	            errorMessage.append("Il numero di telefono inserito non è valido.<br/>");
//	        }
//	    }
//
//	    // Se ci sono errori, restituisci la pagina del profilo con i messaggi di errore
//	    if (hasErrors) {
//	        model.addAttribute("error", errorMessage.toString());
//	        model.addAttribute("userInfo", currentUser); // Riporta i dati correnti
//	        return "profilo"; // Nome della vista del profilo
//	    }
//
//	    try {
//	        // Aggiorna l'utente con i parametri forniti
//	        UserDTO updatedUser = userService.updateUserWithParams(currentUser.getUserId(), passwordVecchia, passwordNuova, email, telefono);
//	        // Aggiorna l'utente nella sessione
//	        sessione.setUtenteLoggato(session, updatedUser);
//
//	        // Aggiungi messaggio di successo
//	        model.addAttribute("succes", " Profilo aggiornato con successo ! Controlla i tuoi nuovi dati. ");
//	    } catch (UserNotFoundException e) {
//	        // Gestisci l'eccezione se l'utente non viene trovato
//	        model.addAttribute("error", "Aggiornamento fallito: " + e.getMessage());
//	        model.addAttribute("userInfo", currentUser);
//	        return "profilo";
//	    } catch (InvalidPasswordException e) {
//	        // Gestisci il caso di password vecchia errata
//	        model.addAttribute("error", "Aggiornamento fallito: " + e.getMessage());
//	        model.addAttribute("userInfo", currentUser);
//	        return "profilo";
//	    }
//
//	    // Reindirizza alla pagina del profilo con il messaggio di successo
//	    return "redirect:/profilo";
//	}
	@PostMapping("/modifica")
	public String modificaProfilo(@RequestParam(value = "passwordVecchia", required = false) String passwordVecchia,
	                              @RequestParam(value = "passwordNuova", required = false) String passwordNuova,
	                              @RequestParam(value = "email", required = false) String email,
	                              @RequestParam(value = "telefono", required = false) String telefono,
	                              HttpSession session, Model model) {

	    // Verifica se l'utente è loggato
	    if (!sessione.isUtenteLoggato(session)) {
	        return sessione.redirectTologin();
	    }

	    UserDTO currentUser = sessione.getUtenteLoggato(session);
	    List<String> errorMessages = new ArrayList<>();

	    // Validazione della nuova password (solo se fornita)
	    if (passwordNuova != null && !passwordNuova.isEmpty()) {
	        if (passwordNuova.length() < 6) {
	            errorMessages.add("La nuova password deve avere almeno 6 caratteri.");
	        }
	    }

	    // Validazione dell'email (solo se fornita)
	    if (email != null && !email.isEmpty()) {
	        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	            errorMessages.add("L'email inserita non è valida.");
	        }
	    }

	    // Validazione del telefono (solo se fornito)
	    if (telefono != null && !telefono.isEmpty()) {
	        if (!telefono.matches("\\d{10}")) {
	            errorMessages.add("Il numero di telefono inserito non è valido.");
	        }
	    }

	    // Se ci sono errori di validazione, restituisci la pagina del profilo con i messaggi di errore
	    if (!errorMessages.isEmpty()) {
	        model.addAttribute("errorMessages", errorMessages);
	        model.addAttribute("userInfo", currentUser);
	        return "profilo";
	    }

	    try {
	        // Aggiorna l'utente con i parametri forniti
	        UserDTO updatedUser = userService.updateUserWithParams(currentUser.getUserId(), passwordVecchia, passwordNuova, email, telefono);
	        // Aggiorna l'utente nella sessione
	        sessione.setUtenteLoggato(session, updatedUser);

	        // Aggiungi messaggio di successo
	        model.addAttribute("successMessage", "Profilo aggiornato con successo! Controlla i tuoi nuovi dati.");
	    } catch (InvalidCredentialsException e) {
	        // Gestisci tutte le eccezioni derivate da InvalidCredentialException
	        errorMessages.add("Aggiornamento fallito: " + e.getMessage());
	        model.addAttribute("errorMessages", errorMessages);
	        model.addAttribute("userInfo", currentUser);
	        return "profilo";
	    }

	    // Reindirizza alla pagina del profilo con il messaggio di successo
	    return "redirect:/profilo";
	}


}

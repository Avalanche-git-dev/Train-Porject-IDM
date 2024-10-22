

package com.treno.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.exception.InvalidCredentialsException;
import com.treno.application.exception.UserNotFoundException;
import com.treno.application.service.TrenoService;
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

	@Autowired
	@Qualifier("TrenoService")
	private TrenoService trenoService;

	@GetMapping
	public String mostraProfilo(HttpSession session, Model model) {

		UserDTO utenteLoggato = sessione.getUtenteLoggato(session); // Prendi l'utente loggato
		UserDTO userInfo = userService.findByUsername(utenteLoggato.getUsername()); // Carica l'utente loggato dal
																					// database

		model.addAttribute("userInfo", userInfo);
		// model.addAttribute("userInfo", userInfo);

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
			model.addAttribute("errorMessage", "Utente non trovato. Per favore, prova ad effettuare nuovamente il login.");
			return sessione.redirectTologin();
		}

		if (importo <= 0) {
			model.addAttribute("errorMessage", "L'importo deve essere maggiore di zero.");
			model.addAttribute("userInfo", user); // Aggiungi l'utente al modello per la vista
			return "profilo";
		}

		try {
			user.setPortafoglio(user.getPortafoglio() + importo);
			userService.update(user); 
			session.setAttribute("utente", user); 
			model.addAttribute("userInfo", user); 
		} catch (UserNotFoundException e) {
			model.addAttribute("errorMessage",
					"Utente non trovato durante l'aggiornamento del portafoglio. Per favore, prova ad effettuare nuovamente il login.");
			model.addAttribute("userInfo", user); 
			return "profilo";
		}

		model.addAttribute("successMessage", "Denaro aggiunto con successo al portafoglio.");
		return "profilo";
	}
	/*
	 * // modifica Profilo
	 * 
	 * @PostMapping("/modifica") public String modificaProfilo(@RequestParam(value =
	 * "passwordVecchia", required = false) String passwordVecchia,
	 * 
	 * @RequestParam(value = "passwordNuova", required = false) String
	 * passwordNuova,
	 * 
	 * @RequestParam(value = "email", required = false) String email,
	 * 
	 * @RequestParam(value = "telefono", required = false) String telefono,
	 * HttpSession session, RedirectAttributes redirectAttributes) {
	 * 
	 * // Verifica se l'utente è loggato if (!sessione.isUtenteLoggato(session)) {
	 * return sessione.redirectTologin(); }
	 * 
	 * UserDTO currentUser = sessione.getUtenteLoggato(session);
	 * 
	 * // Validazione della nuova password (solo se fornita) if (passwordNuova !=
	 * null && !passwordNuova.isEmpty()) { if (passwordNuova.length() < 6) {
	 * redirectAttributes.addFlashAttribute("errorMessage",
	 * "La nuova password deve avere almeno 6 caratteri."); return
	 * "redirect:/profilo"; } }
	 * 
	 * // Validazione dell'email (solo se fornita) if (email != null &&
	 * !email.isEmpty()) { if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	 * redirectAttributes.addFlashAttribute("errorMessage",
	 * "L'email inserita non è valida."); return "redirect:/profilo"; } }
	 * 
	 * // Validazione del telefono (solo se fornito) if (telefono != null &&
	 * !telefono.isEmpty()) { if (!telefono.matches("\\d{10}")) {
	 * redirectAttributes.addFlashAttribute("errorMessage",
	 * "Il numero di telefono inserito non è valido."); return "redirect:/profilo";
	 * } }
	 * 
	 * try { // Aggiorna l'utente con i parametri forniti UserDTO updatedUser =
	 * userService.updateUserWithParams(currentUser.getUserId(), passwordVecchia,
	 * passwordNuova, email, telefono);
	 * 
	 * // Aggiorna l'utente nella sessione sessione.setUtenteLoggato(session,
	 * updatedUser);
	 * 
	 * // Aggiungi messaggio di successo
	 * 
	 * } catch (InvalidCredentialsException e) { // Gestisci tutte le eccezioni
	 * derivanti da credenziali non valide
	 * redirectAttributes.addFlashAttribute("errorMessage",
	 * "Aggiornamento fallito: " + e.getMessage()); return "redirect:/profilo"; }
	 * redirectAttributes.addFlashAttribute("successMessage",
	 * "Profilo aggiornato con successo! Controlla i tuoi nuovi dati."); //
	 * Reindirizza alla pagina del profilo con il messaggio di successo return
	 * "redirect:/profilo"; }
	 */

	// modifica Profilo
	@PostMapping("/modifica")
	public String modificaProfilo(@RequestParam(value = "passwordVecchia", required = false) String passwordVecchia,
	                              @RequestParam(value = "passwordNuova", required = false) String passwordNuova,
	                              @RequestParam(value = "email", required = false) String email,
	                              @RequestParam(value = "telefono", required = false) String telefono,
	                              HttpSession session, RedirectAttributes redirectAttributes) {

	    // Verifica se l'utente è loggato
	    if (!sessione.isUtenteLoggato(session)) {
	        return sessione.redirectTologin();
	    }

	    UserDTO currentUser = sessione.getUtenteLoggato(session);

	    // Variabile che indica se ci sono modifiche
	    boolean isModified = false;

	    // Validazione della nuova password (solo se fornita)
	    if (passwordNuova != null && !passwordNuova.isEmpty()) {
	        if (passwordNuova.length() < 6) {
	            redirectAttributes.addFlashAttribute("errorMessage", "La nuova password deve avere almeno 6 caratteri.");
	            return "redirect:/profilo";
	        } else {
	            isModified = true; // La password è cambiata
	        }
	    }

	    // Validazione dell'email (solo se fornita)
	    if (email != null && !email.isEmpty() && !email.equals(currentUser.getEmail())) {
	        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	            redirectAttributes.addFlashAttribute("errorMessage", "L'email inserita non è valida.");
	            return "redirect:/profilo";
	        } else {
	            isModified = true; // L'email è cambiata
	        }
	    }

	    // Validazione del telefono (solo se fornito)
	    if (telefono != null && !telefono.isEmpty() && !telefono.equals(currentUser.getTelefono())) {
	        if (!telefono.matches("\\d{10}")) {
	            redirectAttributes.addFlashAttribute("errorMessage", "Il numero di telefono inserito non è valido.");
	            return "redirect:/profilo";
	        } else {
	            isModified = true; // Il telefono è cambiato
	        }
	    }

	    // Se non ci sono modifiche, invia un messaggio che indica che nessuna modifica è stata apportata
	    if (!isModified) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Nessuna modifica apportata.");
	        return "redirect:/profilo";
	    }

	    try {
	        // Aggiorna l'utente con i parametri forniti
	        UserDTO updatedUser = userService.updateUserWithParams(currentUser.getUserId(), passwordVecchia, passwordNuova, email, telefono);
	        
	        // Aggiorna l'utente nella sessione
	        sessione.setUtenteLoggato(session, updatedUser);

	        // Aggiungi messaggio di successo
	        redirectAttributes.addFlashAttribute("successMessage", "Profilo aggiornato con successo! Controlla i tuoi nuovi dati.");
	    } catch (InvalidCredentialsException e) {
	        // Gestisci tutte le eccezioni derivanti da credenziali non valide
	        redirectAttributes.addFlashAttribute("errorMessage", "Aggiornamento fallito: " + e.getMessage());
	        return "redirect:/profilo";
	    }

	    // Reindirizza alla pagina del profilo con il messaggio di successo
	    return "redirect:/profilo";
	}

	
	
	

	

	@PostMapping("/mostra/utente")
	public String mostraProfiloUtente(HttpSession session, Model model, @RequestParam("username") String username) {
		// Recupera l'utente tramite username dal servizio
		UserDTO utenteView = userService.findByUsername(username);

		if (utenteView == null) {
			session.setAttribute("erroMessage", "L'utente da te cercato non esiste.");
			return "redirect:/profiloUtente";
		}

		try {
			// Recupera la lista dei treni associati all'utente visualizzato
			List<TrenoDTO> treniUtente = trenoService.findTreniByUsername(username);

			// Memorizza sia l'utente che la lista di treni nella sessione
			session.setAttribute("utenteView", utenteView);
			session.setAttribute("treniDto", treniUtente);

			// Aggiungi i dettagli dell'utente e dei treni al modello per la vista
			model.addAttribute("username", utenteView.getUsername());
			model.addAttribute("nome", utenteView.getNome());
			model.addAttribute("numeroTreni", treniUtente.size());
			model.addAttribute("listaTreni", treniUtente);

		} catch (UserNotFoundException e) {
			model.addAttribute("errorMessage", "Utente non trovato: " + e.getMessage());
		}

		return "profiloUtente";
	}

}

//package com.treno.application.controller;
////
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.stereotype.Controller;
//////import org.springframework.ui.Model;
//////import org.springframework.validation.BindingResult;
//////import org.springframework.web.bind.annotation.GetMapping;
//////import org.springframework.web.bind.annotation.ModelAttribute;
//////import org.springframework.web.bind.annotation.PutMapping;
//////import org.springframework.web.bind.annotation.RequestMapping;
//////import org.springframework.web.bind.annotation.SessionAttribute;
//////
//////import com.treno.application.dto.UserDTO;
//////import com.treno.application.service.UserService;
//////
//////import jakarta.servlet.http.HttpSession;
//////import jakarta.validation.Valid;
//////
//////@Controller
//////@RequestMapping("/profilo")
//////public class ProfiloController {
//////
//////    @Autowired
//////    private UserService userService;
//////
//////    // Visualizza il profilo dell'utente
//////    @GetMapping
//////    public String visualizzaProfilo(@SessionAttribute("utente") UserDTO sessionUserDto, Model model, HttpSession session) {
//////        if (sessionUserDto == null) {
//////            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
//////        }
//////
//////        // Recupera le informazioni dell'utente dal database tramite UserService
//////        UserDTO userInfo = userService.findById(sessionUserDto.getUserId());
//////
//////        // Aggiungi le informazioni dell'utente al model
//////        model.addAttribute("userInfo", userInfo);
//////        return "profilo"; // Mostra la vista del profilo
//////    }
//////
//////    // Mostra il form per modificare il profilo dell'utente
//////    @GetMapping("/modifica")
//////    public String mostraFormModificaProfilo(@SessionAttribute("utente") UserDTO sessionUserDto, Model model, HttpSession session) {
//////        if (sessionUserDto == null) {
//////            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
//////        }
//////
//////        // Recupera le informazioni dell'utente dal database tramite UserService
//////        UserDTO userInfo = userService.findById(sessionUserDto.getUserId());
//////
//////        // Aggiungi le informazioni dell'utente al model
//////        model.addAttribute("userInfo", userInfo);
//////        return "modificaProfilo"; // Mostra la vista per modificare il profilo
//////    }
//////
//////    // Gestisce la modifica del profilo dell'utente
//////    @PutMapping("/modifica")
//////    public String modificaProfilo(@Valid @ModelAttribute("userInfo") UserDTO userDto, BindingResult result, HttpSession session, Model model) {
//////        if (result.hasErrors()) {
//////            model.addAttribute("error", "Ci sono errori nei campi forniti.");
//////            return "modificaProfilo"; // Ritorna alla pagina di modifica con messaggio di errore
//////        }
//////
//////        // Verifica che l'email sia valida
//////        if (!userDto.getEmail().contains("@")) {
//////            model.addAttribute("error", "Email non valida.");
//////            return "modificaProfilo"; // Ritorna alla pagina di modifica con messaggio di errore
//////        }
//////
//////        // Aggiorna le informazioni dell'utente nel database tramite UserService
//////        userService.update(userDto);
//////
//////        // Aggiorna la sessione con i nuovi dati dell'utente
//////        session.setAttribute("utente", userDto);
//////
//////        return "redirect:/profilo"; // Reindirizza alla vista del profilo aggiornata
//////    }
//////    
//////    
//////    
//////    
//////}
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.validation.BindingResult;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.ModelAttribute;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RequestParam;
////import org.springframework.web.bind.annotation.SessionAttribute;
////
////import com.treno.application.dto.UserDTO;
////import com.treno.application.service.UserService;
////
////import jakarta.servlet.http.HttpSession;
////import jakarta.validation.Valid;
////
////@Controller
////@RequestMapping("/profilo")
////public class ProfiloController {
////
////    @Autowired
////    private UserService userService;
////
////    // Visualizza il profilo dell'utente
////    @GetMapping
////    public String visualizzaProfilo(@SessionAttribute(name = "utente", required = false) UserDTO sessionUserDto, Model model, HttpSession session) {
////        if (sessionUserDto == null) {
////            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
////        }
////
////        // Recupera le informazioni dell'utente dal database tramite UserService
////        UserDTO userInfo = userService.findById(sessionUserDto.getUserId());
////
////        // Aggiungi le informazioni dell'utente al model
////        model.addAttribute("userInfo", userInfo);
////        return "profilo"; // Mostra la vista del profilo
////    }
////
////    // Gestisce la modifica del profilo dell'utente
////    @PostMapping("/modifica")
////    public String modificaProfilo(@Valid @ModelAttribute("userInfo") UserDTO userDto, BindingResult result, HttpSession session, Model model) {
////        if (result.hasErrors()) {
////            model.addAttribute("error", "Ci sono errori nei campi forniti.");
////            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
////        }
////
////        // Aggiorna le informazioni dell'utente nel database tramite UserService
////        userService.update(userDto);
////
////        // Aggiorna la sessione con i nuovi dati dell'utente
////        session.setAttribute("utente", userDto);
////
////        return "redirect:/profilo"; // Reindirizza alla vista del profilo aggiornata
////    }
////
////    // Gestisce l'aggiunta di fondi al portafoglio dell'utente
////    @PostMapping("/portafoglio/aggiungi")
////    public String aggiungiDenaro(@RequestParam("importo") double importo, @SessionAttribute(name = "utente", required = false) UserDTO sessionUserDto, HttpSession session, Model model) {
////        if (sessionUserDto == null) {
////            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
////        }
////
////        if (importo <= 0) {
////            model.addAttribute("error", "L'importo deve essere maggiore di zero.");
////            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
////        }
////
////        // Aggiungi l'importo al portafoglio dell'utente
////        sessionUserDto.setPortafoglio(sessionUserDto.getPortafoglio() + importo);
////        userService.update(sessionUserDto);
////
////        // Aggiorna la sessione con i nuovi dati dell'utente
////        session.setAttribute("utente", sessionUserDto);
////
////        return "redirect:/profilo"; // Reindirizza alla vista del profilo aggiornata
////    }
////
////    // Gestisce la cancellazione dell'account dell'utente
////    @GetMapping("/elimina")
////    public String eliminaAccount(@SessionAttribute(name = "utente", required = false) UserDTO sessionUserDto, HttpSession session) {
////        if (sessionUserDto == null) {
////            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
////        }
////
////        // Elimina l'utente dal database tramite UserService
////        userService.cancellaAccount(sessionUserDto);
////
////        // Invalida la sessione
////        session.invalidate();
////
////        return "redirect:/login"; // Reindirizza al login dopo la cancellazione dell'account
////    }
////}
////package com.treno.application.controller;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.validation.BindingResult;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.ModelAttribute;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RequestParam;
////import org.springframework.web.bind.annotation.SessionAttribute;
////
////import com.treno.application.dto.UserDTO;
////import com.treno.application.service.UserService;
////
////import jakarta.servlet.http.HttpSession;
////import jakarta.validation.Valid;
////
////@Controller
////@RequestMapping("/profilo")
////public class ProfiloController {
////
////    @Autowired
////    private UserService userService;
////
////    // Visualizza il profilo dell'utente
////    @GetMapping
////    public String visualizzaProfilo(@SessionAttribute(name = "utente", required = false) UserDTO sessionUserDto, Model model, HttpSession session) {
////        if (sessionUserDto == null) {
////            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
////        }
////
////        // Recupera le informazioni dell'utente dal database tramite UserService
////        UserDTO userInfo = userService.findById(sessionUserDto.getUserId());
////
////        // Aggiorna la sessione con i dati aggiornati dell'utente
////        session.setAttribute("utente", userInfo);
////
////        // Aggiungi le informazioni dell'utente al model
////        model.addAttribute("userInfo", userInfo);
////        return "profilo"; // Mostra la vista del profilo
////    }
////
////    // Gestisce la modifica del profilo dell'utente
////    @PostMapping("/modifica")
////    public String modificaProfilo(@Valid @ModelAttribute("userInfo") UserDTO userDto, BindingResult result, HttpSession session, Model model) {
////        if (result.hasErrors()) {
////            model.addAttribute("error", "Ci sono errori nei campi forniti.");
////            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
////        }
////
////        // Aggiorna le informazioni dell'utente nel database tramite UserService
////        userService.update(userDto);
////
////        // Aggiorna la sessione con i nuovi dati dell'utente
////        session.setAttribute("utente", userDto);
////
////        return "redirect:/profilo"; // Reindirizza alla vista del profilo aggiornata
////    }
////
////    // Gestisce l'aggiunta di fondi al portafoglio dell'utente
////    @PostMapping("/portafoglio/aggiungi")
////    public String aggiungiDenaro(@RequestParam("importo") double importo, @SessionAttribute(name = "utente", required = false) UserDTO sessionUserDto, HttpSession session, Model model) {
////        if (sessionUserDto == null) {
////            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
////        }
////
////        if (importo <= 0) {
////            model.addAttribute("error", "L'importo deve essere maggiore di zero.");
////            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
////        }
////
////        // Aggiungi l'importo al portafoglio dell'utente
////        sessionUserDto.setPortafoglio(sessionUserDto.getPortafoglio() + importo);
////        userService.update(sessionUserDto);
////
////        // Aggiorna la sessione con i nuovi dati dell'utente
////        session.setAttribute("utente", sessionUserDto);
////
////        return "redirect:/profilo"; // Reindirizza alla vista del profilo aggiornata
////    }
////
////    // Gestisce la cancellazione dell'account dell'utente
////    @GetMapping("/elimina")
////    public String eliminaAccount(@SessionAttribute(name = "utente", required = false) UserDTO sessionUserDto, HttpSession session) {
////        if (sessionUserDto == null) {
////            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
////        }
////
////        // Elimina l'utente dal database tramite UserService
////        userService.cancellaAccount(sessionUserDto);
////
////        // Invalida la sessione
////        session.invalidate();
////
////        return "redirect:/login"; // Reindirizza al login dopo la cancellazione dell'account
////    }
////}
////
////@Controller
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttribute;
//
//import com.treno.application.dto.UserDTO;
//import com.treno.application.service.UserService;
//import com.treno.eccezzioni.UserNotFoundException;
//
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//@Controller
//@RequestMapping("/profilo")
//public class ProfiloController {
//
//    @Autowired
//    private UserService userService;
//
//    // Visualizza il profilo dell'utente
//    @GetMapping
//    public String visualizzaProfilo(@SessionAttribute(name = "utente", required = false) UserDTO sessionUserDto, Model model, HttpSession session) {
//        if (sessionUserDto == null) {
//            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
//        }
//
//        try {
//            // Recupera le informazioni dell'utente dal database tramite UserService
//            UserDTO userInfo = userService.findById(sessionUserDto.getUserId());
//            // Aggiorna la sessione con i dati aggiornati dell'utente
//            session.setAttribute("utente", userInfo);
//            // Aggiungi le informazioni dell'utente al model
//            model.addAttribute("userInfo", userInfo);
//        } catch (UserNotFoundException e) {
//            model.addAttribute("error", "Utente non trovato. Per favore, prova ad effettuare nuovamente il login.");
//            return "redirect:/login"; // Se l'utente non è trovato, reindirizza al login
//        }
//        
//        return "profilo"; // Mostra la vista del profilo
//    }
//
//    // Gestisce la modifica del profilo dell'utente
//    @PostMapping("/modifica")
//    public String modificaProfilo(@Valid @ModelAttribute("userInfo") UserDTO userDto, BindingResult result, HttpSession session, Model model) {
//    	
////        if (result.hasErrors()) {
////            model.addAttribute("error", "Ci sono errori nei campi forniti.");
////            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
////        }
//    	if (getLoggedInUser(session) != null)
//
//        try {
//        	
//        	// Aggiorna la sessione con i nuovi dati dell'utente
//        	session.setAttribute("utente", userDto);
//            userService.update(userDto);
//            
//            
//        } catch (UserNotFoundException e) {
//            model.addAttribute("error", "Utente non trovato durante l'aggiornamento. Per favore, prova ad effettuare nuovamente il login.");
//            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
//        }
//
//        return "redirect:/profilo"; // Reindirizza alla vista del profilo aggiornata
//    }
//
//    // Gestisce l'aggiunta di fondi al portafoglio dell'utente
//    @PostMapping("/portafoglio/aggiungi")
//    public String aggiungiDenaro(@RequestParam("importo") double importo, @SessionAttribute(name = "utente", required = false) UserDTO sessionUserDto, HttpSession session, Model model) {
//        if (sessionUserDto == null) {
//            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
//        }
//
//        if (importo <= 0) {
//            model.addAttribute("error", "L'importo deve essere maggiore di zero.");
//            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
//        }
//
//        try {
//            // Aggiungi l'importo al portafoglio dell'utente
//            sessionUserDto.setPortafoglio(sessionUserDto.getPortafoglio() + importo);
//            userService.update(sessionUserDto);
//            // Aggiorna la sessione con i nuovi dati dell'utente
//            session.setAttribute("utente", sessionUserDto);
//        } catch (UserNotFoundException e) {
//            model.addAttribute("error", "Utente non trovato durante l'aggiornamento del portafoglio. Per favore, prova ad effettuare nuovamente il login.");
//            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
//        }
//
//        return "redirect:/profilo"; // Reindirizza alla vista del profilo aggiornata
//    }
//
//    // Gestisce la cancellazione dell'account dell'utente
//    @GetMapping("/elimina")
//    public String eliminaAccount(@SessionAttribute(name = "utente", required = false) UserDTO sessionUserDto, HttpSession session) {
//        if (sessionUserDto == null) {
//            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
//        }
//
//        try {
//            // Elimina l'utente dal database tramite UserService
//            userService.cancellaAccount(sessionUserDto);
//            // Invalida la sessione
//            session.invalidate();
//        } catch (UserNotFoundException e) {
//            return "redirect:/login"; // Reindirizza al login se l'utente non è trovato
//        }
//
//        return "redirect:/login"; // Reindirizza al login dopo la cancellazione dell'account
//    }
//    
//    
//    
//    private UserDTO getLoggedInUser(HttpSession session) {
//        return (UserDTO) session.getAttribute("utente");
//    }
//}

//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.treno.application.dto.UserDTO;
//import com.treno.application.service.UserService;
//import com.treno.eccezzioni.UserNotFoundException;
//import com.treno.application.utility.SessioneUtility;
//
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//
//@Controller
//@RequestMapping("/profilo")
//public class ProfiloController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private SessioneUtility sessioneUtility;
//
//    // Visualizza il profilo dell'utente
//    @GetMapping
//    public String visualizzaProfilo(HttpSession session, Model model) {
//        UserDTO userInfo = sessioneUtility.getUtenteLoggato(session);
//        if (userInfo == null) {
//            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
//        }
//
//        try {
//            // Recupera le informazioni aggiornate dell'utente dal database tramite UserService
//            userInfo = userService.findById(userInfo.getUserId());
//            // Aggiorna la sessione con i dati aggiornati dell'utente
//            session.setAttribute("utente", userInfo);
//            // Aggiungi le informazioni dell'utente al model
//            model.addAttribute("userInfo", userInfo);
//        } catch (UserNotFoundException e) {
//            model.addAttribute("error", "Utente non trovato. Per favore, prova ad effettuare nuovamente il login.");
//            return "redirect:/login"; // Se l'utente non è trovato, reindirizza al login
//        }
//
//        return "profilo"; // Mostra la vista del profilo
//    }
//
//    // Gestisce la modifica del profilo dell'utente
//    @PostMapping("/modifica")
//    public String modificaProfilo(@Valid @ModelAttribute("userInfo") UserDTO userDto, BindingResult result, HttpSession session, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("error", "Ci sono errori nei campi forniti.");
//            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
//        }
//
//        UserDTO loggedUser = sessioneUtility.getUtenteLoggato(session);
//        if (loggedUser == null) {
//            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
//        }
//
//        try {
//            // Aggiorna l'utente nel database tramite UserService
//            userService.update(userDto);
//            // Aggiorna la sessione con i nuovi dati dell'utente
//            session.setAttribute("utente", userDto);
//        } catch (UserNotFoundException e) {
//            model.addAttribute("error", "Utente non trovato durante l'aggiornamento. Per favore, prova ad effettuare nuovamente il login.");
//            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
//        }
//
//        return "redirect:/profilo"; // Reindirizza alla vista del profilo aggiornata
//    }
//
//    // Gestisce l'aggiunta di fondi al portafoglio dell'utente
//    @PostMapping("/portafoglio/aggiungi")
//    public String aggiungiDenaro(@RequestParam("importo") double importo, HttpSession session, Model model) {
//        UserDTO user = sessioneUtility.getUtenteLoggato(session);
//        if (user == null) {
//            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
//        }
//
//        if (importo <= 0) {
//            model.addAttribute("error", "L'importo deve essere maggiore di zero.");
//            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
//        }
//
//        try {
//            // Aggiungi l'importo al portafoglio dell'utente
//            user.setPortafoglio(user.getPortafoglio() + importo);
//            userService.update(user);
//            // Aggiorna la sessione con i nuovi dati dell'utente
//            session.setAttribute("utente", user);
//        } catch (UserNotFoundException e) {
//            model.addAttribute("error", "Utente non trovato durante l'aggiornamento del portafoglio. Per favore, prova ad effettuare nuovamente il login.");
//            return "profilo"; // Ritorna alla pagina del profilo con messaggio di errore
//        }
//
//        return "redirect:/profilo"; // Reindirizza alla vista del profilo aggiornata
//    }
//
//    // Gestisce la cancellazione dell'account dell'utente
//    @GetMapping("/elimina")
//    public String eliminaAccount(HttpSession session) {
//        UserDTO user = sessioneUtility.getUtenteLoggato(session);
//        if (user == null) {
//            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
//        }
//
//        try {
//            // Elimina l'utente dal database tramite UserService
//            userService.cancellaAccount(user);
//            // Invalida la sessione
//            session.invalidate();
//        } catch (UserNotFoundException e) {
//            return "redirect:/login"; // Reindirizza al login se l'utente non è trovato
//        }
//
//        return "redirect:/login"; // Reindirizza al login dopo la cancellazione dell'account
//    }
//}
//
//

package com.treno.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.treno.application.dto.UserDTO;
import com.treno.application.exception.InvalidPasswordException;
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
	public String visualizzaProfilo(HttpSession session, Model model) {

		UserDTO userInfo = sessione.getUtenteLoggato(session);
		if(!sessione.isUtenteLoggato(session)) {
			return sessione.redirectTologin();
		}
		try {
			userInfo = userService.findById(userInfo.getUserId());
			session.setAttribute("utente", userInfo);
			model.addAttribute("userInfo", userInfo);
		} catch (UserNotFoundException e) {
			model.addAttribute("error", e.getMessage());
			return sessione.redirectTologin();
		}
		return "profilo";
	}

	// Elimina l'account dell'utente e reindirizza alla pagina di login
	@GetMapping("/elimina")
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

//	// Modifica
//	@PostMapping("/portafoglio/aggiungi")
//	public String aggiungiDenaro(@RequestParam("importo") double importo, HttpSession session, Model model) {
//		UserDTO user = sessione.getUtenteLoggato(session);
//
//		if (importo <= 0) {
//			model.addAttribute("error", "L'importo deve essere maggiore di zero.");
//			return "profilo";
//		}
//
//		try {
//			user.setPortafoglio(user.getPortafoglio() + importo);
//			userService.update(user); // Questo chiamerà il metodo `update()` con la logica aggiornata
//			session.setAttribute("utente", user);
//		} catch (UserNotFoundException e) {
//			model.addAttribute("error",
//					"Utente non trovato durante l'aggiornamento del portafoglio. Per favore, prova ad effettuare nuovamente il login.");
//			return "profilo";
//		}
//
//		return "redirect:/profilo";
//	}

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
	
	@PostMapping("/modifica")
	public String modificaProfilo(@RequestParam(value = "passwordVecchia", required = false) String passwordVecchia,
	                              @RequestParam(value = "passwordNuova", required = false) String passwordNuova,
	                              @RequestParam(value = "email", required = false) String email,
	                              @RequestParam(value = "telefono", required = false) String telefono, 
	                              HttpSession session, Model model) throws Exception {

	    // Verifica se l'utente è loggato
	    if (!sessione.isUtenteLoggato(session)) {
	        return sessione.redirectTologin();
	    }

	    UserDTO currentUser = sessione.getUtenteLoggato(session);
	    boolean hasErrors = false;
	    StringBuilder errorMessage = new StringBuilder();

	    // Validazione della nuova password (solo se fornita)
	    if (passwordNuova != null && !passwordNuova.isEmpty()) {
	        if (passwordNuova.length() < 6) {
	            hasErrors = true;
	            errorMessage.append("La nuova password deve avere almeno 6 caratteri.<br/>");
	        }
	    }

	    // Validazione dell'email (solo se fornita)
	    if (email != null && !email.isEmpty()) {
	        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	            hasErrors = true;
	            errorMessage.append("L'email inserita non è valida.<br/>");
	        }
	    }

	    // Validazione del telefono (solo se fornito)
	    if (telefono != null && !telefono.isEmpty()) {
	        if (!telefono.matches("\\d{10}")) { // Controllo telefono per 10 cifre
	            hasErrors = true;
	            errorMessage.append("Il numero di telefono inserito non è valido.<br/>");
	        }
	    }

	    // Se ci sono errori, restituisci la pagina del profilo con i messaggi di errore
	    if (hasErrors) {
	        model.addAttribute("error", errorMessage.toString());
	        model.addAttribute("userInfo", currentUser); // Riporta i dati correnti
	        return "profilo"; // Nome della vista del profilo
	    }

	    try {
	        // Aggiorna l'utente con i parametri forniti
	        UserDTO updatedUser = userService.updateUserWithParams(currentUser.getUserId(), passwordVecchia, passwordNuova, email, telefono);
	        // Aggiorna l'utente nella sessione
	        sessione.setUtenteLoggato(session, updatedUser);

	        // Aggiungi messaggio di successo
	        model.addAttribute("error", "Profilo aggiornato con successo.");
	    } catch (UserNotFoundException e) {
	        // Gestisci l'eccezione se l'utente non viene trovato
	        model.addAttribute("error", "Aggiornamento fallito: " + e.getMessage());
	        model.addAttribute("userInfo", currentUser);
	        return "profilo";
	    } catch (InvalidPasswordException e) {
	        // Gestisci il caso di password vecchia errata
	        model.addAttribute("error", "Aggiornamento fallito: " + e.getMessage());
	        model.addAttribute("userInfo", currentUser);
	        return "profilo";
	    }

	    // Reindirizza alla pagina del profilo con il messaggio di successo
	    return "redirect:/profilo";
	}

}

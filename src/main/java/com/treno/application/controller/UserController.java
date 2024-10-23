package com.treno.application.controller;


import java.util.List;

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
import com.treno.application.dto.UserGuest;
import com.treno.application.exception.InvalidCredentialsException;
import com.treno.application.exception.InvalidPasswordException;
import com.treno.application.exception.UserAlreadyExistsException;
import com.treno.application.exception.UserNotFoundException;
import com.treno.application.service.TrenoService;
import com.treno.application.service.UserService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("UserService")
    private UserService userService;
    
    
    
    @Autowired
    @Qualifier("Sessione")
    private SessioneUtility sessione;
    
    
    
    
    
    @Autowired
    @Qualifier("TrenoService")
    private TrenoService trenoService;

    // <-----------------------------------------------------------------
    
//    
    // mostraRegistrati
    @GetMapping("/registrati")
    public String mostraRegistrazione(Model model,HttpSession session) {
    	UserGuest guest= (UserGuest) sessione.getUtenteLoggato(session);
    	if((guest!=null)&&(guest instanceof UserGuest))
    	{
    		model.addAttribute("guest",guest);
    	}
        model.addAttribute("userDto", new UserDTO());
        return "registrati"; // Jsmettila di mettere l'estensione
    }
//
//    
//    
//    // doRegistrati
//    @PostMapping("/registrati")
//    public String doRegistrati(@ModelAttribute UserDTO userDto, HttpSession session) {
//        try {
//            userService.registra(userDto);
//            sessione.setUtenteLoggato(session, userDto);
//            return "redirect:/dashboard"; // Reindirizza al login dopo la registrazione
//        } catch (UserAlreadyExistsException e) {
//            session.setAttribute("errorMessage", "Errore durante la registrazione: " + e.getMessage());
//            return "registrati"; // Ritorna alla pagina di registrazione se c'è un errore
//        }
//    }


    
    // mostraLogin + ricevi messaggio di sessioneScaduta dall'interceptor in caso.
    @GetMapping("/login")
    public String mostralogin(@RequestParam(value = "sessioneScaduta", required = false) String sessioneScaduta, HttpSession session, Model model) {
        // Invalida eventuale sessione esistente
        if (session != null) {
            session.invalidate();
        }

        // Aggiungi un messaggio al modello se la sessione è scaduta
		/*
		 * if ("true".equals(sessioneScaduta)) { model.addAttribute("messaggio",
		 * "La tua sessione è scaduta. Effettua nuovamente il login."); }
		 */
        // Aggiunge un nuovo oggetto UserDTO al modello per il form di login
        model.addAttribute("userDto", new UserDTO());
        return "login"; // Nome della vista JSP per il login
    }


    // doLogin
    @PostMapping("/login")
    public String doLogin(@ModelAttribute("userDto") UserDTO userDto, Model model, HttpServletRequest request) {
        try {
        	
        	UserDTO utenteLoggato = userService.login(userDto);
        	
        	 HttpSession session = request.getSession(false); 
             if (session != null) {
                 session.invalidate();
             }
        	
            session = request.getSession(true);
            sessione.setUtenteLoggato(session, utenteLoggato);
            

            return "redirect:/dashboard";
            
        } catch (UserNotFoundException e) {
            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
            return "login";  // <-----
            
        } catch (InvalidPasswordException e) {
            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
            return "login";  // <-----
            
        } catch (InvalidCredentialsException e) {
            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
            return "login";  // <-----
        }
    }
    
    
    // Logout
    @PostMapping("/logout")
    public String Dologout(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session != null) {
            session.invalidate(); // Invalida la sessione corrente
        }
        // Aggiungi un messaggio di conferma del logout
        redirectAttributes.addFlashAttribute("logoutMessage", "Logout effettuato con successo.");
        return sessione.redirectTologin(); // Reindirizza alla pagina di login
    }
    
    
    
//    @GetMapping("/registrati")
//    public String mostraRegistrazione(Model model, HttpSession session) {
//        model.addAttribute("userDto", new UserDTO());
//
//        // Verifica se c'è un utente guest nella sessione
//        UserGuest guest = (UserGuest) session.getAttribute("guest");
//        
//        if ((guest != null)&&(guest instanceof UserGuest)) {
//            @SuppressWarnings("unchecked")
//			List<TrenoDTO> treniGuest = (List<TrenoDTO>) session.getAttribute("treniGuest");
//            if (treniGuest != null && !treniGuest.isEmpty()) {
//                session.setAttribute("treniGuest", treniGuest);
//            }
//            model.addAttribute("guest", guest);
//            
//        }
//
//        return "registrati"; // Mostra la pagina di registrazione
//    }
//    
    
    @PostMapping("/registrati")
    public String doRegistrati(@ModelAttribute UserDTO userDto, HttpSession session,@ModelAttribute UserGuest guest) {
        try {
            // Registra l'utente normalmente
            userService.registra(userDto);
            session.removeAttribute("guest");
            UserDTO userR = userService.findByUsername(userDto.getUsername());

            // Verifica se l'utente era un guest
            
            if ((guest != null)&&(guest instanceof UserGuest)) {
                // Recupera i treni guest dalla sessione
                @SuppressWarnings("unchecked")
				List<TrenoDTO> treniGuest = (List<TrenoDTO>) session.getAttribute("treniGuest");
                if (treniGuest != null && !treniGuest.isEmpty()) {
                    for (TrenoDTO trenoDto : treniGuest) {
                    	
                        trenoService.creaTreno(trenoDto,userR); // Salva il treno nel database
                    }
                    
                    session.removeAttribute("treniGuest");
                }
                
               
            }
            
            sessione.setUtenteLoggato(session,userR);
            // Reindirizza alla dashboard
            return "redirect:/dashboard";
        } catch (UserAlreadyExistsException e) {
            session.setAttribute("errorMessage", "Errore durante la registrazione: " + e.getMessage());
            return "registrati"; // Ritorna alla pagina di registrazione in caso di errore
        }
    }
    


   



}

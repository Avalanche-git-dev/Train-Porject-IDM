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

import com.treno.application.dto.AdminDTO;
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
    
    
//    @Autowired
//    @Qualifier("AdminService")
//    private AdminService adminService;
    
    @Autowired
    @Qualifier("TrenoService")
    private TrenoService trenoService;

    // <-----------------------------------------------------------------
    
    
    // getRegistrati
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
    
    // doRegistrati
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
    
    
    
    
    
    


    
    // mostraLogin + ricevi messaggio di sessioneScaduta dall'interceptor in caso.
    @GetMapping("/login")
    public String mostralogin(@RequestParam(value = "sessioneScaduta", required = false) String sessioneScaduta, HttpSession session, Model model) {
        // Invalida eventuale sessione esistente
        if (session != null) {
            session.invalidate();
        }
        
        
        //If (admin) --> model.addAttributre(admin);

        // Aggiunge un nuovo oggetto UserDTO al modello per il form di login
        model.addAttribute("userDto", new UserDTO());
        return "login"; // Nome della vista JSP per il login
    }

    
    
    
    
//
//    // doLogin
//    @PostMapping("/login")
//    public String doLogin(@ModelAttribute("userDto") UserDTO userDto, Model model, HttpServletRequest request) {
//        try {
//        	
//        	UserDTO utenteLoggato = userService.login(userDto);
//        	//Controllo se in sessione è admin. il guest non puo accedere
//        	 HttpSession session = request.getSession(false); 
//             if (session != null) {
//                 session.invalidate();
//             }
//            session = request.getSession(true);
//            sessione.setUtenteLoggato(session, utenteLoggato);
//            
//            if(session!=null) {
//            	
//            }
//            
//
//            return "redirect:/dashboard";
//            
//        } catch (UserNotFoundException e) {
//            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
//            return "login";  // <-----
//            
//        } catch (InvalidPasswordException e) {
//            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
//            return "login";  // <-----
//            
//        } catch (InvalidCredentialsException e) {
//            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
//            return "login";  // <-----
//        }
//    }
    
    
    
    @PostMapping("/login")
    public String doLogin(@ModelAttribute("userDto") UserDTO userDto, Model model, HttpServletRequest request) {
        try {
            // Autentica l'utente tramite il servizio
            
        	UserDTO utenteLoggato;
				utenteLoggato = userService.login(userDto);
				
			
            // Invalida la sessione esistente, se presente
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            // Crea una nuova sessione
            session = request.getSession(true);
            

            if (utenteLoggato instanceof AdminDTO) {
            	
            	boolean privilegio = ((AdminDTO) utenteLoggato).isPrivilegio();
            	session.setAttribute("privilegio", privilegio);
            	session.setAttribute("admin", utenteLoggato);
                return "redirect:/admin";
                
            }else {
            	sessione.setUtenteLoggato(session, utenteLoggato); 
            	return "redirect:/dashboard";
            }
            
            
			

            

        } catch (UserNotFoundException e) {
            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
            return "login";  

        } catch (InvalidPasswordException e) {
            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
            return "login";  

        } catch (InvalidCredentialsException e) {
            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
            return "login";  
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
    
    
    
    
    
    
//    @PostMapping("/login")
//    public String doLogin(@ModelAttribute("userDto") UserDTO userDto, Model model, HttpServletRequest request) {
//        try {
//            UserDTO utenteLoggato = userService.login(userDto);
//            
//            HttpSession session = request.getSession(false); 
//            if (session != null) {
//                session.invalidate();
//            }
//
//            // Creazione di una nuova sessione
//            session = request.getSession(true);
//
//            sessione.setUtenteLoggato(session, utenteLoggato);  
//            
//            if (utenteLoggato instanceof AdminDTO) {
//                return "redirect:/admin/dashboard";
//            } else if (utenteLoggato instanceof UserGuest) {
//                session.invalidate();
//                model.addAttribute("errorMessage", "Accesso negato: gli ospiti non possono accedere.");
//                return "login";
//            } else {
//                return "redirect:/dashboard";
//            }
//
//        } catch (UserNotFoundException e) {
//            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
//            return "login";  
//            
//        } catch (InvalidPasswordException e) {
//            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
//            return "login";  
//            
//        } catch (InvalidCredentialsException e) {
//            model.addAttribute("errorMessage", "Errore: " + e.getMessage());
//            return "login";  
//        }
//    }


}

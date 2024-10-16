package treno;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.treno.application.dto.UserDTO;
import com.treno.application.service.UserService;
import com.treno.eccezzioni.InvalidPasswordException;
import com.treno.eccezzioni.UserNotFoundException;

import jakarta.servlet.http.HttpSession;

@Controller 
public class UserController {

    @Autowired
    @Qualifier("UserService")
    private UserService userService;

    //form registrazione
    @GetMapping("/registrati")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDTO());
        return "registrazione"; // JSP , html l'importante è smettere di mettere l'estensione
    }

    //dati registrazione effettiva.
//    @PostMapping("/registrati")
//    public String registra (@ModelAttribute("userDto") UserDto userDto, Model model) {
//        String risultato = userService.registra(userDto);
//        model.addAttribute("message", risultato);
//        
//        if (risultato.contains("successo")) {
//            return "redirect:/login"; // Se la registrazione ha successo, si rimanda alla pagina di login
//        } else {
//            return "registrazione"; // Se fallisce, si rimanda alla stessa pagina con il messaggio d'errore
//        }
//    }
    
    
    //Registrazione rifatto.
    @PostMapping("/registrazione")
    public String registerUser(@ModelAttribute UserDTO userDto, HttpSession session) {
        try {
            userService.registra(userDto);
            return "redirect:/login"; // Reindirizza al login dopo la registrazione
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Errore durante la registrazione "+e.getMessage());
            return "registrazione"; // Ritorna alla pagina di registrazione se c'è un errore
        }
    }
    
    
    
    // Get = mostra facciamo prima
    @GetMapping("/login")
    public String Login(Model model) {
        model.addAttribute("userDto", new UserDTO());
        return "login"; // jsp o altra vista per il login , maledizione smettila con l'estensione, e fai sto view resolver html.
        
    }

    // Post = ricevi dal front end
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("userDto") UserDTO userDto, Model model, HttpSession session) {
        try {
            userService.login(userDto);
            
            // Se il login ha successo, salva l'utente nella sessione
            UserDTO utente = userService.findByUsername(userDto.getUsername());
            session.setAttribute("utente", utente);

            return "redirect:/dashboard"; 

        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Utente non trovato.");
            return "login"; 

        } catch (InvalidPasswordException e) {
            model.addAttribute("error", "Password errata.");
            return "login"; 
        }
        
    }

    
    
    
    
	}


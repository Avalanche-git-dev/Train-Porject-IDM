package treno;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.treno.application.model.User;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("/dashboard")
//public class DashboardController {
//
////	@GetMapping
////	public String Dashboard() {
////
////		return "dashboard"; // Nome pagina
////	}
//
//	@GetMapping
//	public String Dashboard(HttpSession session, Model model) {
//		User utente = (User) session.getAttribute("utente");
//
//		if (utente == null) {
//			return "redirect:/login"; // Reindirizza alla pagina di login
//		}
//
//		model.addAttribute("utente", utente); // passa l'utente al modello per la view
//		return "dashboard"; // dashboard
//	}
//	
//	
//
//	@GetMapping("/profilo")
//	public String Profilo() {
//		return "profilo"; // profilo
//	}
//
//	@GetMapping("/treni")
//	public String Treni() {
//		return "treni"; // treni
//	}
//
//	@GetMapping("/market")
//	public String Market() {
//		return "market"; // market
//	}
//	
//	
//	
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.treno.application.dto.UserDTO;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final SessioneUtility sessioneUtility;

    // Iniettiamo SessioneUtility nel costruttore
    @Autowired
    public DashboardController(SessioneUtility sessioneUtility) {
        this.sessioneUtility = sessioneUtility;
    }

    @GetMapping
    public String Dashboard(HttpSession session, Model model) {
        if (!sessioneUtility.isUtenteLoggato(session)) {
            return "redirect:/login"; // Reindirizza alla pagina di login
        }

        UserDTO utente = sessioneUtility.getUtenteLoggato(session);
        model.addAttribute("utente", utente); // passa l'utente al modello per la view
        return "dashboard"; // dashboard
    }

    @GetMapping("/profilo")
    public String Profilo(HttpSession session, Model model) {
        if (!sessioneUtility.isUtenteLoggato(session)) {
            return "redirect:/login"; // Reindirizza alla pagina di login
        }

        UserDTO utente = sessioneUtility.getUtenteLoggato(session);
        model.addAttribute("utente", utente);
        return "profilo"; // profilo
    }

    @GetMapping("/treni")
    public String Treni(HttpSession session, Model model) {
        if (!sessioneUtility.isUtenteLoggato(session)) {
            return "redirect:/login";
        }

        UserDTO utente = sessioneUtility.getUtenteLoggato(session);
        model.addAttribute("utente", utente);
        return "treni"; // treni
    }

    @GetMapping("/market")
    public String Market(HttpSession session, Model model) {
        if (!sessioneUtility.isUtenteLoggato(session)) {
            return "redirect:/login";
        }

        UserDTO utente = sessioneUtility.getUtenteLoggato(session);
        model.addAttribute("utente", utente);
        return "market"; // market
    }
}


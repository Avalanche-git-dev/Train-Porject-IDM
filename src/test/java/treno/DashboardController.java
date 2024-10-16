package treno;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.treno.application.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

//	@GetMapping
//	public String Dashboard() {
//
//		return "dashboard"; // Nome pagina
//	}

	@GetMapping
	public String Dashboard(HttpSession session, Model model) {
		User utente = (User) session.getAttribute("utente");

		if (utente == null) {
			return "redirect:/login"; // Reindirizza alla pagina di login
		}

		model.addAttribute("utente", utente); // passa l'utente al modello per la view
		return "dashboard"; // dashboard
	}
	
	

	@GetMapping("/profilo")
	public String Profilo() {
		return "profilo"; // profilo
	}

	@GetMapping("/treni")
	public String Treni() {
		return "treni"; // treni
	}

	@GetMapping("/market")
	public String Market() {
		return "market"; // market
	}
	
	
	
}

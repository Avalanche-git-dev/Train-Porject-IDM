package treno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.treno.application.dto.UserDTO;
import com.treno.application.service.TrenoService;
import com.treno.application.service.UserService;

@Controller
@RequestMapping("/profilo")
public class ProfiloController {

	@Autowired
	@Qualifier("UserService")
	private UserService userService;

	@Autowired
	@Qualifier ("TrenoService")
	TrenoService trenoService;

//	@GetMapping
//	public String visualizzaProfilo(@SessionAttribute("utente") User utente, Model model) {
//		// Recupera le informazioni dell'utente dal database
//		User userInfo = userService.findById(utente.getUserId());
//		 //Proviamo ad aggiungere la lista dei treni
//		
//		
//		model.addAttribute("userInfo", userInfo);
//		return "profilo"; // Mostra la vista del profilo
//	}
//	
	
	
	@GetMapping
	public String visualizzaProfilo(@SessionAttribute("utente") UserDTO utenteDto, Model model) {
	    // Recupera le informazioni dell'utente dal database tramite UserDTO
	    UserDTO userInfo = userService.findById(utenteDto.getUserId());

	    // Proviamo ad aggiungere la lista dei treni
	    model.addAttribute("userInfo", userInfo);
	    return "profilo"; // Mostra la vista del profilo
	}

	
}

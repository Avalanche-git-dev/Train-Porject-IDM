package com.treno.application.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.treno.application.dto.UserDTO;
import com.treno.application.dto.UserGuest;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;



public class SessioneUtilityUser implements SessioneUtility {
	
	
	
	@Autowired
	@Qualifier("Guest")
	private UserGuest guest;
	

    @Override
    public UserDTO getUtenteLoggato(HttpSession session) {
        return (UserDTO) session.getAttribute("utenteLoggato");
    }
    //bella idea questa, grazie Alessandro, è anche vero che sto imparando alla velocità della luce :)
    @Override
    public boolean isUtenteLoggato(HttpSession session) {
        return getUtenteLoggato(session) != null;
    }
    
    @Override
    public void setUtenteLoggato(HttpSession session, UserDTO userDto) {
        session.setAttribute("utenteLoggato", userDto);
        //System.out.println("SessionUtility.setUtenteLoggato: userDto = " + userDto);
    }
    
	@Override
	public String redirectTologin() {
		return "redirect:/user/login";
	}
	
	//UtenteGuest
	@Override
	public boolean isUtenteGuest(HttpSession session) {
		session.setAttribute("guest", guest);
		return getUtenteLoggato(session) instanceof UserGuest;
	}
}

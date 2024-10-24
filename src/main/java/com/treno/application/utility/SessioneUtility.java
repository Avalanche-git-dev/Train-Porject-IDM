package com.treno.application.utility;
import com.treno.application.dto.AdminDTO;
import com.treno.application.dto.UserDTO;
import jakarta.servlet.http.HttpSession;

public interface SessioneUtility {
	
	//mi sono rotto il cazzo di scrivere controlli che poi mi fanno prendere male per il backend, è incrollabile quella merda.

    UserDTO getUtenteLoggato(HttpSession session);

    boolean isUtenteLoggato(HttpSession session);

	void setUtenteLoggato(HttpSession session, UserDTO updatedUser);

	String redirectTologin();

	boolean isUtenteGuest(HttpSession session);

	AdminDTO getAdminLoggato(HttpSession session);

	boolean isAdminLoggato(HttpSession session);
}

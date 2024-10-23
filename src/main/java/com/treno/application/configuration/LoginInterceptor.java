package com.treno.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

// Questa roba con le interfacce è strafigo pensavo ci andasse di piu
	@Autowired
	@Qualifier("Sessione")
	private SessioneUtility sessioneUtility;

//Praticamente una servlet che due coglioni.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession oldSession = request.getSession(false);

		if (oldSession == null || !sessioneUtility.isUtenteLoggato(oldSession)) {
			response.sendRedirect(request.getContextPath() + "/user/login?sessioneScaduta=true");
			return false; // Interrompe l'esecuzione della richiesta
		}
		
		//if(oldSession == null)||(!sessioneUtility.isUtenteGuest(oldSession)){

		// Se l'utente è loggato, prosegui con la richiesta
		return true;
	}
}

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Recupera la sessione esistente, se c'è
        HttpSession oldSession = request.getSession(false);

        if (oldSession == null || !sessioneUtility.isUtenteLoggato(oldSession)) {
            // Se la sessione non esiste o l'utente non è loggato, crea una nuova sessione e reindirizza al login
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false; // Interrompe l'esecuzione della richiesta
        }

        // Se l'utente è loggato, prosegui con la richiesta
        return true;
    }
}


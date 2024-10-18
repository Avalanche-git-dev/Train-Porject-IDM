package com.treno.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
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
    private SessioneUtility sessioneUtility;
//Praticamente una servlet che due coglioni.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        
        
        if (session == null || !sessioneUtility.isUtenteLoggato(session)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false; // Interrompe l'esecuzione della richiesta, poiché l'utente non è loggato
        }

        // Se l'utente è loggato, prosegui con la richiesta
        return true;
    }
}


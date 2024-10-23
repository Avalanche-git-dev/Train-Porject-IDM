package com.treno.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.treno.application.dto.UserGuest;
import com.treno.application.utility.SessioneUtility;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.treno.application.controller") // Abilita le configurazioni MVC, se non è già
																	// abilitato da qualche altra parte
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registraLogin) {
		registraLogin.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/",
				"/user/registrati", "/user/login", "/resources/**", "/css/**", "/js/**", "/catalogo","/treni/crea/guest","/market");
	}

	
	
	
	
	
	// <WEB Application>
	
	
	
	
	@Bean("Guest")
	@Scope("prototype")
	@Lazy
	public UserGuest getGuest() {
		return new UserGuest();
	}

	// Sessione
	@Bean("Sessione")
	public SessioneUtility getSessione() {
		return new SessioneUtilityUser();
	}

}

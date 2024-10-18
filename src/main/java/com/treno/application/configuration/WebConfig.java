package com.treno.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc // Abilita le configurazioni MVC, se non è già abilitato da qualche altra parte
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registraLogin) {
		registraLogin.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login",
				"/resources/**", "/css/**", "/js/**");
	}

	// <WEB Application>

	// Sessione
	@Bean("Sessione")
	public SessioneUtilityImpl getSessione() {
		return new SessioneUtilityImpl();
	}

}

package com.salesianostriana.dam.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(authz -> authz
				.requestMatchers("/", "/login", "/css/**", "/js/**", 
						"/img/**", "/h2/**").permitAll()
				
				//ADMIN --> Admin podia crear, editar y borrar
				.requestMatchers("/productos/nuevo", "/productos/save",
								"/productos/editar/**", "/productos/borrar/**").hasRole("ADMIN")
				
				.requestMatchers("/clientes/nuevo", "/clientes/save",
								"/clientes/editar/**", "/clientes/borrar/**").hasRole("ADMIN")
				
				.requestMatchers("/pedidos/nuevo", "/pedidos/save", 
						"/pedidos/editar/**", "/pedidos/borrar/**").hasRole("ADMIN")
				
				//Usuarios --> Pueden ver listas y consultas
				.anyRequest().authenticated()
		)
		
		.requestCache(cache -> {
			HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
			requestCache.setMatchingRequestParameterName(null);
			cache.requestCache(requestCache);
		})
		
		.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.permitAll()
		)
		
		.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.permitAll()
		);
		
		//H2
		http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2/**"));
		http.headers(headers -> headers.frameOptions(opts -> opts.disable()));
		
		return http.build();
		
	}
	
}

package com.salesianostriana.dam.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.NullRequestCache;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(authz -> authz
				.requestMatchers("/", "/login", "/logout", "/css/**", "/js/**", 
						"/img/**", "/h2/**", "/error").permitAll()
				
				//ADMIN --> Admin podia crear, editar y borrar
				.requestMatchers("/admin/**").hasRole("ADMIN")
				
				.requestMatchers("/productos/nuevo", "/productos/save",
								"/productos/editar/**", "/productos/borrar/**").hasRole("ADMIN")
				
				.requestMatchers("/clientes/**").hasRole("ADMIN")
				
				.requestMatchers("/pedidos/nuevo", "/pedidos/save", 
						"/pedidos/editar/**", "/pedidos/borrar/**",
						"/pedidos/*/agregar-producto").hasRole("ADMIN")
				
				.requestMatchers("/consultas/**").hasRole("ADMIN")
				
				//Usuarios --> Pueden ver listas y consultas
				.requestMatchers("/user/**").hasRole("USER")
				
				.requestMatchers("/carrito/**", "/productoACarrito/**",
								"/borrarProducto/**", "/eliminarDelCarrito/**"
								).hasAnyRole("USER", "ADMIN")
				
				.requestMatchers("/productos/", "/productos/{id}").authenticated()
				
				.requestMatchers("/pedidos/").hasRole("ADMIN")
				
				.requestMatchers("/pedido/{id}").hasRole("ADMIN")
				
				.anyRequest().authenticated()
		)
		
		//No vamos a guardar la url de origen
		.requestCache(cache -> cache.requestCache(new NullRequestCache()))
		
		.formLogin(form -> form
				.loginPage("/login")
				.successHandler(authenticationSuccessHandler)
				.permitAll()
		)
		
		.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true) //Esto destruye el carrito de la sesion 
				.permitAll()
		)
				//H2
				.csrf(csrf -> csrf.ignoringRequestMatchers("/h2/**"))
				.headers(headers -> headers.frameOptions(opts -> opts.disable()));
		
		return http.build();
		
	}
	
}

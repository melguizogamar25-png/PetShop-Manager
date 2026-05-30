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
                .requestMatchers(
                        "/", "/login", "/logout", "/registro",  
                        "/css/**", "/js/**", "/img/**", "/h2/**", "/error"
                ).permitAll()
 
                // ADMIN
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/productos/nuevo", "/productos/save",
                        "/productos/editar/**", "/productos/borrar/**",
                        "/productos/admin").hasRole("ADMIN")
                .requestMatchers("/clientes/**").hasRole("ADMIN")
                .requestMatchers("/pedidos/nuevo", "/pedidos/save",
                        "/pedidos/editar/**", "/pedidos/borrar/**",
                        "/pedidos/*/agregar-producto",
                        "/pedidos/*/lineas/add"         
                ).hasRole("ADMIN")
                .requestMatchers("/consultas/**").hasRole("ADMIN")
 
                // USER
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/carrito/**", "/productoACarrito/**",
                        "/borrarProducto/**", "/eliminarDelCarrito/**"
                ).hasRole("USER") 
 
                // Autenticados
                .requestMatchers("/productos/", "/productos/{id}").authenticated()
                .requestMatchers("/pedidos/").hasRole("ADMIN")
                .requestMatchers("/pedido/{id}").hasRole("ADMIN")
 
                .anyRequest().authenticated()
        )
 
        .requestCache(cache -> cache.requestCache(new NullRequestCache()))
 
        .formLogin(form -> form
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
        )
 
        .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true) 
                .permitAll()
        )
 
        // H2
        .csrf(csrf -> csrf.ignoringRequestMatchers("/h2/**"))
        .headers(headers -> headers.frameOptions(opts -> opts.disable()));
 
        return http.build();
    }
	
}
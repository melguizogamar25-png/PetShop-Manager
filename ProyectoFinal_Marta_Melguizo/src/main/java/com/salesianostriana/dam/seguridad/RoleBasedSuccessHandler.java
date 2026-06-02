package com.salesianostriana.dam.seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

@Component
@Log
public class RoleBasedSuccessHandler implements AuthenticationSuccessHandler{

	private static final String URL_ADMIN = "/admin/index";
	private static final String URL_USER = "/user/index";
	private static final String URL_DEFAULT = "/login?error=Rol+no+reconocido";
	
	private static final Map<String, Integer> PESOS = Map.of(
			"ROLE_ADMIN", 10,
			"ROLE_USER", 1
			);
	
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	//Nos va a devolver el rol que tenga mayor peso
		private String getRolMaximo(Collection<? extends GrantedAuthority> authorities) {
			List<GrantedAuthority> lista = new ArrayList<>(authorities);
			
			if(lista.isEmpty()) {
				return "ROLE_DEFAULT";
			}
			return lista.stream()
					.map(GrantedAuthority::getAuthority)
					.filter(a -> a.startsWith("ROLE_"))
					.sorted((r1 , r2) -> 
							PESOS.getOrDefault(r2, Integer.MIN_VALUE)
							- PESOS.getOrDefault(r1, Integer.MIN_VALUE))
					.findFirst()
					.orElse("ROLE_DEFAULT");
		} 
		
		//Mapear el rol para la url del destino
		private String determinarUrl(String rol) {
			return switch (rol) {
			case "ROLE_ADMIN" -> URL_ADMIN;
			case "ROLE_USER" -> URL_USER;
			default -> URL_DEFAULT;
			};
		}
		
		
	@Override
	public void onAuthenticationSuccess (HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication)
		throws IOException, ServletException {
		String rol, redirectUrl;
		
		log.info("Login correcto: " + authentication.getName());
		
		rol = getRolMaximo(authentication.getAuthorities());
		redirectUrl = determinarUrl(rol);
		
		log.info("Rol máximo: " + rol + " - redirigiendo a: " + 
		redirectUrl);
		
		if(!response.isCommitted()) {
			redirectStrategy.sendRedirect(request, response, redirectUrl);
		}
	}
	
			
}

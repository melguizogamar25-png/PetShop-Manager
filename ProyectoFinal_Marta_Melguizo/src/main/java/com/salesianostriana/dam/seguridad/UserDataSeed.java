package com.salesianostriana.dam.seguridad;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDataSeed {

	private final UserRepository repo;
	private final PasswordEncoder encoder;
	
	@PostConstruct
	public void init() {
		/*
		 * Esto es para evitar que haya duplicados cuando 
		 * reinicias con el create-drop
		 */
		if(repo.count() > 0) return;
		
	}
}

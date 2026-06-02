package com.salesianostriana.dam.seguridad;

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
	
		Usuario user = Usuario.builder()
				.email("user@user.com")
				.username("user")
				.password(encoder.encode("user"))
				.roles(RolesUsuario.USER)
				.fullname("usuario")
				.build();
		
		repo.save(user);
				
		Usuario admin = Usuario.builder()
				.email("admin@admin.com")
				.username("admin")
				.password(encoder.encode("admin"))
				.roles(RolesUsuario.ADMIN)
				.fullname("administrador")
				.build();
		
		repo.save(admin);
	}
}

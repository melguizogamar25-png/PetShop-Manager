package com.salesianostriana.dam.seguridad;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByUsername(String username);
}

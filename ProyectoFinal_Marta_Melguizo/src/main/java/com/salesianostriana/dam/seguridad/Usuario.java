package com.salesianostriana.dam.seguridad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@Entity
@Table (name="usuario")
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	private String password;
	private String email;
	private String fullname;
	
	private RolesUsuario roles;
	
}

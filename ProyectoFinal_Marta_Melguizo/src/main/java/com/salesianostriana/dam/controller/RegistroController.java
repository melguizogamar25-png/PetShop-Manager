package com.salesianostriana.dam.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.seguridad.RolesUsuario;
import com.salesianostriana.dam.seguridad.UserRepository;
import com.salesianostriana.dam.seguridad.Usuario;
import com.salesianostriana.dam.services.ClienteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistroController {

	private final UserRepository userRepository;
	private final ClienteService clienteService;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/registro")
	public String registro(@RequestParam String username,
						   @RequestParam String password,
						   @RequestParam String email,
						   @RequestParam String fullname,
						   @RequestParam(defaultValue = "0") int telefono,
						   @RequestParam(defaultValue = "false") boolean socioTienda) {

		// Si el usuario ya existe, volver al login con error
		if (userRepository.findByUsername(username).isPresent()) {
			return "redirect:/login?errorRegistro";
		}

		// Crear y guardar el usuario con contraseña encriptada
		Usuario nuevoUsuario = Usuario.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.email(email)
				.fullname(fullname)
				.roles(RolesUsuario.USER)
				.build();
		userRepository.save(nuevoUsuario);

		// Crear también el cliente asociado
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setNombre(fullname);
		nuevoCliente.setEmail(email);
		nuevoCliente.setTelefono(telefono);
		nuevoCliente.setSocioTienda(socioTienda);
		clienteService.save(nuevoCliente);

		// Auto-login: establecer el contexto de seguridad directamente
		UsernamePasswordAuthenticationToken auth =
				new UsernamePasswordAuthenticationToken(nuevoUsuario, null, nuevoUsuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

		// Redirigir directamente al área de usuario
		return "redirect:/user/index";
	}
}
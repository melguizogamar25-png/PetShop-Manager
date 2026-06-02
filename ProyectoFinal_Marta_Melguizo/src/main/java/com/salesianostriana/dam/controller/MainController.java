package com.salesianostriana.dam.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.seguridad.Usuario;
import com.salesianostriana.dam.services.ProductoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final ProductoService productoService;
	
	@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productosDestacados", productoService.productosAleatorios(3));
        return "index";
    }
	
	// admin
	@GetMapping("/user/index")
    public String userIndex(Model model, @AuthenticationPrincipal UserDetails uLogeado) {
    	if (uLogeado instanceof Usuario usuario) {
    		model.addAttribute("usuarioNom", usuario.getFullname());
    	} else {
    		model.addAttribute("usuarioNom", uLogeado.getUsername());
    	}
        return "user/index";
    }
}

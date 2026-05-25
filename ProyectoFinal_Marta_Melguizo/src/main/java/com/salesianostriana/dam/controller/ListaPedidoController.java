package com.salesianostriana.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.services.PedidoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pedidos/{codigo}/lineas")
public class ListaPedidoController {

	private final PedidoService pedidoService;
	

}

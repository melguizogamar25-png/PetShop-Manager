package com.salesianostriana.dam.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.repository.ProductoRepository;
import com.salesianostriana.dam.services.base.BaseServiceImpl;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarritoService extends BaseServiceImpl<Producto, Long, ProductoRepository>{

	private Map<Producto, Integer> carrito = new HashMap<>();
	
	public void addProducto(Producto p) {
		if(carrito.containsKey(p)) {
			carrito.replace(p, carrito.get(p) + 1);
		}else {
			carrito.put(p, 1);
		}
	}
	
	//Eliminar el producto del carrito
	public void removeProducto(Producto p) {
		if(carrito.containsKey(p)) {
			if(carrito.get(p) > 1) {
				carrito.replace(p, carrito.get(p) - 1);
			}else {
				carrito.remove(p);
			}
		}
	}
	
	//Eliminar el producto buscando por id
	public void removeProductoPorId(Long id) {
		if(carrito == null || id == null) return;
		
		carrito.keySet().stream()
			.filter(p -> p.getId().equals(id))
			.findFirst()
			.ifPresent(p -> carrito.remove(p));
	}
	
	//Elimina el producto completo con todas las unidades que tenga
	public void eliminarProductoCompleto (Long id) {
		removeProductoPorId(id);
	}
	
	public void vaciarCarrito() {
		if(carrito != null) carrito.clear();
	}
	
	//Recorrer el carrito
	public Map<Producto, Integer> getProductosInCarr() {
		return Collections.unmodifiableMap(carrito);
	}
	
	public int numeroDiferentesProductos() {
		return carrito.size();
	}
	
	public int totalUnidades() {
		return carrito.values().stream().mapToInt(Integer::intValue).sum();
	}
	
	public boolean estaVacio() {
		return carrito == null || carrito.isEmpty();
	}
	
	//CALCULAR EL TOTAL
	
	public double calcularTotal() {
		double total = 0.0;
		if (carrito != null) {
			for(Producto p:carrito.keySet()) {
				total += p.getPrecio() * carrito.get(p);
			}
		}
		return total;
	}
	
	//Calcularlo con el descuento
	public double calcularTotalConDescuentos(boolean esSocio) {
		if(carrito == null) return 0.0;
		
		return carrito.entrySet().stream()
				.mapToDouble(e -> e.getKey().getPrecioConDescuento(esSocio) * e.getValue())
				.sum();
	}
	
}

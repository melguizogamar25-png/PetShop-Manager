package com.salesianostriana.dam.services;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.excepciones.StockInsuficienteException;
import com.salesianostriana.dam.excepciones.TipoMascotaInvalidoException;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.TipoMascota;
import com.salesianostriana.dam.repository.ProductoRepository;
import com.salesianostriana.dam.services.base.BaseServiceImpl;

@Service
public class ProductoService extends BaseServiceImpl<Producto, Long, ProductoRepository>{
    
    
    //Consultas Derivadas
	public List<Producto> buscarPorNombre(String termino) {
		return repository.findByNombreContainingIgnoreCase(termino);
	}
	
	// - Filtrar tipo mascota
	public List<Producto> porTipoMascota(String tipo) {
		try {
			return repository.findByTipoMascota(TipoMascota.valueOf(tipo));
		}catch (IllegalArgumentException e) {
			throw new TipoMascotaInvalidoException(tipo);
		}
	}
	
	// - Los productos que tengan bajo el stock  y esta ordenado asc
	
	public List<Producto> productosBajoStock(int umbral) {
		return repository.findByStockLessThanEqualOrderByStockAsc(umbral);
	}
	
	public List<Producto> conDevolucion() {
		return repository.findDevolucionTrue();
	}
	
	public List<Producto> masvendidosPorTipos(TipoMascota tipo) {
		return repository.findMasVendidosPorTipo(tipo);
	}
	
	//Logica de negocios
	public double precioMedioPorTipo(TipoMascota tipo) {
		return findAll().stream()
				.filter(p -> p.getTipoMascota() == tipo)
				.mapToDouble(Producto::getPrecio)
				.average() //Calcula la media
				.orElse(0.0);
	}
	
	public Map<TipoMascota, List<Producto>> agrupadosPorTipo() {
		return findAll().stream()
				.collect(Collectors.groupingBy(Producto::getTipoMascota));
	}
	
	public List<Producto> ordenadosPorPrecioAsc() {
		return findAll().stream()
				.sorted(Comparator.comparingDouble(Producto::getPrecio))
				.toList();
	}
	
	// - Control de stock
	public void verificarStock(Producto producto, int cantidad) {
		if(producto.getStock() < cantidad) {
			throw new StockInsuficienteException(producto.getNombre(), 
					producto.getStock(), cantidad);
		}
	}
}

package com.salesianostriana.dam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.TipoMascota;

public interface ProductoRepository extends JpaRepository <Producto, Long>{

	//Consultas Derivadas
	
	List<Producto> findByNombreContainingIgnoreCase(String nombre);
	
	// - Buscar los productos de un tipo de mascota
	List<Producto> findByTipoMascota(TipoMascota tipo);
	
	/* - Los productos que el stock es menor 
	o = umbral y esta ordenado ASC*/
	List<Producto> findByStockLessThanEqualOrderByStockAsc(int umbral);
	
	List<Producto> findDevolucionTrue();
	
	/* - los productos que tengan el precio menor 
	o = al que tengo ordenado asc*/
	List<Producto> findByPrecioLessThanEqualOrderByPrecioAsc(Double precio);
	
	// - los productos de un tipo de mascota ordenado por precio asc
	
	List<Producto> findByTipoMascotaOrderByPrecioAsc(TipoMascota tipo);
	
	// - Cuenta los productos de un tipo de mascota
	long countByTipoMascota(TipoMascota tipo);
	
	// - Hay algun producto que se llame asi
	boolean existsByNombreIgnorecase(String nombre);
	
	// - El producto mas vendido por tipo
	@Query("""
			select p from Producto p where p.tipoMascota = :tipo
			order by (select coalesce(sum(l.cantidad),0)
						from Lineapedido l where l.producto = p)
			desc
			""")
	List<Producto> findMasVendidosPorTipo(@Param("tipo") TipoMascota tipo);
	
	// - Productos aleatorios
	@Query("select p.id from Producto p")
	List<Long> findAllIds();
	
}

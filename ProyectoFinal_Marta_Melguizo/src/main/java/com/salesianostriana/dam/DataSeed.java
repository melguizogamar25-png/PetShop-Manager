package com.salesianostriana.dam;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.EstadoPedido;
import com.salesianostriana.dam.model.LineaPedido;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.TipoMascota;
import com.salesianostriana.dam.repository.ClienteRepository;
import com.salesianostriana.dam.repository.LineaPedidoRepository;
import com.salesianostriana.dam.repository.PedidoRepository;
import com.salesianostriana.dam.repository.ProductoRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeed {

	private final ProductoRepository productoRepository;
	private final ClienteRepository clienteRepository;
	private final LineaPedidoRepository lineaPedidoRepository;
	private final PedidoRepository pedidoRepository;
	
	
	@PostConstruct
	public void init() {
		//Productos
				Producto p1 = Producto.builder()
				.nombre("Pienso Premium para perro Adulto")
				.precio(34.99)
				.stock(50)
				.fechaCaducidad(LocalDate.of(2027, 6, 1))
				.devolucion(false)
				.tipoMascota(TipoMascota.PERRO)
				.imagen("")
				.descripcion("Alimentación completa y equilibrada para perros adultos de todas las razas.")
				.build();
				
				Producto p2 = Producto.builder()
				.nombre("Arnés Acolchado para perros")
				.precio(19.95)
				.stock(30)
				.devolucion(true)
				.tipoMascota(TipoMascota.PERRO)
				.imagen("")
				.descripcion("Arnés cómodo y seguro con cierre de seguridad y pasador reflectante.")
				.build();
		
				Producto p3 = Producto.builder()
				.nombre("Juguete Mordedor para perros")
				.precio(8.50)
				.stock(100)
				.devolucion(false)
				.tipoMascota(TipoMascota.PERRO)
				.imagen("")
				.descripcion("Mordedor de goma natural resistente para entretenimiento y salud dental.")
				.build();
		
				Producto p4 = Producto.builder()
				.nombre("Pienso Sterilised para Gato")
				.precio(22.50)
				.stock(40)
				.fechaCaducidad(LocalDate.of(2027, 3, 15))
				.devolucion(false)
				.tipoMascota(TipoMascota.GATO)
				.imagen("")
				.descripcion("Fórmula especial para gatos esterilizados. Control de peso y bienestar urinario.")
				.build();
		
				Producto p5 = Producto.builder()
				.nombre("Rascador de Cama para Gatos")
				.precio(45.00)
				.stock(15)
				.devolucion(true)
				.tipoMascota(TipoMascota.GATO)
				.imagen("")
				.descripcion("Rascador de sisal con plataformas de descanso y hamaca integrada.")
				.build();
		
				Producto p6 = Producto.builder()
				.nombre("Mezcla de Semillas para Canarios")
				.precio(6.99)
				.stock(8)
				.fechaCaducidad(LocalDate.of(2026, 12, 1))
				.devolucion(false)
				.tipoMascota(TipoMascota.AVE)
				.imagen("")
				.descripcion("Mezcla natural de semillas sin colorantes para canarios y pájaros pequeños.")
				.build();
		
				Producto p7 = Producto.builder()
				.nombre("Jaula Grande para Loros")
				.precio(129.00)
				.stock(8)
				.devolucion(true)
				.tipoMascota(TipoMascota.AVE)
				.imagen("")
				.descripcion("Jaula espaciosa con posaderos naturales, comederos y bandeja extraíble.")
				.build();
		
				Producto p8 = Producto.builder()
				.nombre("Lámpara UVB para Reptiles")
				.precio(38.00)
				.stock(20)
				.devolucion(true)
				.tipoMascota(TipoMascota.REPTIL)
				.imagen("")
				.descripcion("Lámpara de espectro completo UVA/UVB esencial para tortugas y lagartos.")
				.build();
		
				Producto p9 = Producto.builder()
				.nombre("Sustrato Natural para Terrario")
				.precio(12.50)
				.stock(60)
				.devolucion(false)
				.tipoMascota(TipoMascota.REPTIL)
				.imagen("")
				.descripcion("Sustrato de fibra de coco 100% natural. Retención de humedad perfecta.")
				.build();
		
				Producto p10 = Producto.builder()
				.nombre("Rueda Silenciosa para Hámster")
				.precio(14.99)
				.stock(45)
				.devolucion(true)
				.tipoMascota(TipoMascota.ROEDOR)
				.imagen("")
				.descripcion("Rueda de ejercicio sin ruido, superficie antideslizante. Ideal para hámsters.")
				.build();
		
				Producto p11 = Producto.builder()
				.nombre("Pellets para Conejos Enanos")
				.precio(9.25)
				.stock(70)
				.fechaCaducidad(LocalDate.of(2026, 10, 1))
				.devolucion(false)
				.tipoMascota(TipoMascota.ROEDOR)
				.imagen("")
				.descripcion("Pellets enriquecidos con vitaminas C y D para conejos enanos y cobayas.")
				.build();
		
				Producto p12 = Producto.builder()
				.nombre("Acuario Completo 60L con Filtro")
				.precio(89.00)
				.stock(5)
				.devolucion(true)
				.tipoMascota(TipoMascota.PEZ)
				.imagen("")
				.descripcion("Acuario de 60L con filtro interno, iluminación LED y termómetro incluido.")
				.build();
		
				productoRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
		
		//Clientes
				Cliente c1 = Cliente.builder()
				.nombre("María García López")
				.email("maria.garcia@email.com")
				.telefono(612345678)
				.socioTienda(true)
				.build();
		
				Cliente c2= Cliente.builder()
				.nombre("Carlos Martínez Ruiz")
				.email("carlos.martinez@email.com")
				.telefono(698765432)
				.socioTienda(false)
				.build();
		
				Cliente c3= Cliente.builder()
				.nombre("Ana Fernández Torres")
				.email("ana.fernandez@email.com")
				.telefono(655444333)
				.socioTienda(true)
				.build();
	
				Cliente c4 = Cliente.builder()
				.nombre("Pedro Sánchez Gil")
				.email("pedro.sanchez@email.com")
				.telefono(677888999)
				.build();	
		
				clienteRepository.saveAll(List.of(c1, c2, c3, c4));
		
		//Pedido
				Pedido pe1 = Pedido.builder()
				.codigo(1001L)
				.fecha(LocalDate.of(2026, 3, 10))
				.estadoPedido(EstadoPedido.ENTREGADO)
				.descripcion("Pedido mensual de María")
				.cliente(c1)
				.build();
				
				pedidoRepository.saveAll(List.of(pe1));
		
		//Linea de Pedido
		LineaPedido l1 = LineaPedido.builder()
				.cantidad(2)
				.precioUnitario(p1.getPrecioConDescuento(true))
				//.subtotal(l1.getPrecioUnitario()* l1.getCantidad())
				.producto(p1)
				.build();
		
		lineaPedidoRepository.saveAll(List.of(l1));
	}
}

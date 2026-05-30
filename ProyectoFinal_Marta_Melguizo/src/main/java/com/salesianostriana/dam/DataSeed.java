package com.salesianostriana.dam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.EstadoPedido;
import com.salesianostriana.dam.model.LineaPedido;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.TipoMascota;
import com.salesianostriana.dam.repository.ClienteRepository;
import com.salesianostriana.dam.repository.PedidoRepository;
import com.salesianostriana.dam.repository.ProductoRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeed {

	private final ProductoRepository productoRepository;
	private final ClienteRepository clienteRepository;
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
				.imagen("https://cdn.pixabay.com/photo/2020/05/13/21/03/dog-food-5168940_1280.jpg")
				.descripcion("Alimentación completa y equilibrada para perros adultos de todas las razas.")
				.build();

		Producto p2 = Producto.builder()
				.nombre("Arnés Acolchado para perros")
				.precio(19.95)
				.stock(30)
				.devolucion(true)
				.tipoMascota(TipoMascota.PERRO)
				.imagen("https://cdn.pixabay.com/photo/2019/10/12/12/59/yorkie-4543865_1280.jpg")
				.descripcion("Arnés cómodo y seguro con cierre de seguridad y pasador reflectante.")
				.build();

		Producto p3 = Producto.builder()
				.nombre("Juguete Mordedor para perros")
				.precio(8.50)
				.stock(100)
				.devolucion(false)
				.tipoMascota(TipoMascota.PERRO)
				.imagen("https://cdn.pixabay.com/photo/2020/04/05/14/10/puppy-5006299_1280.jpg")
				.descripcion("Mordedor de goma natural resistente para entretenimiento y salud dental.")
				.build();

		Producto p4 = Producto.builder()
				.nombre("Pienso Sterilised para Gato")
				.precio(22.50)
				.stock(40)
				.fechaCaducidad(LocalDate.of(2027, 3, 15))
				.devolucion(false)
				.tipoMascota(TipoMascota.GATO)
				.imagen("https://farmahigiene.es/modules/dbblog/views/img/post/blog%20cual%20es%20el%20mejor%20pienso%20para%20gatos-big.png")
				.descripcion("Fórmula especial para gatos esterilizados. Control de peso y bienestar urinario.")
				.build();

		Producto p5 = Producto.builder()
				.nombre("Rascador de Cama para Gatos")
				.precio(45.00)
				.stock(15)
				.devolucion(true)
				.tipoMascota(TipoMascota.GATO)
				.imagen("https://cdn.pixabay.com/photo/2017/01/31/19/06/cat-furniture-2026488_1280.png")
				.descripcion("Rascador de sisal con plataformas de descanso y hamaca integrada.")
				.build();

		Producto p6 = Producto.builder()
				.nombre("Mezcla de Semillas para Canarios")
				.precio(6.99)
				.stock(8)
				.fechaCaducidad(LocalDate.of(2026, 12, 1))
				.devolucion(false)
				.tipoMascota(TipoMascota.AVE)
				.imagen("https://cdn.pixabay.com/photo/2020/10/04/11/31/grains-5626057_1280.jpg")
				.descripcion("Mezcla natural de semillas sin colorantes para canarios y pájaros pequeños.")
				.build();

		Producto p7 = Producto.builder()
				.nombre("Jaula Grande para Loros")
				.precio(129.00)
				.stock(8)
				.devolucion(true)
				.tipoMascota(TipoMascota.AVE)
				.imagen("https://m.media-amazon.com/images/I/61vFwZVbNcL._AC_SX300_SY300_QL70_ML2_.jpg")
				.descripcion("Jaula espaciosa con posaderos naturales, comederos y bandeja extraíble.")
				.build();

		Producto p8 = Producto.builder()
				.nombre("Lámpara UVB para Reptiles")
				.precio(38.00)
				.stock(20)
				.devolucion(true)
				.tipoMascota(TipoMascota.REPTIL)
				.imagen("https://media.adeo.com/mkp/76ade02e796eceb84d8541727f8dbb6c/media.jpg?width=3000&height=3000&format=jpg&quality=80&fit=bounds")
				.descripcion("Lámpara de espectro completo UVA/UVB esencial para tortugas y lagartos.")
				.build();

		Producto p9 = Producto.builder()
				.nombre("Sustrato Natural para Terrario")
				.precio(12.50)
				.stock(60)
				.devolucion(false)
				.tipoMascota(TipoMascota.REPTIL)
				.imagen("https://static3.zoomalia.com/cdn-cgi/image/width=800,height=800,format=auto/prod_img/22990/lm_f85060325cb5838f9cabfd2b02eec519_st3.jpg")
				.descripcion("Sustrato de fibra de coco 100% natural. Retención de humedad perfecta.")
				.build();

		Producto p10 = Producto.builder()
				.nombre("Rueda Silenciosa para Hámster")
				.precio(14.99)
				.stock(45)
				.devolucion(true)
				.tipoMascota(TipoMascota.ROEDOR)
				.imagen("https://m.media-amazon.com/images/I/71BvF7UXHNL._AC_SX569_.jpg")
				.descripcion("Rueda de ejercicio sin ruido, superficie antideslizante. Ideal para hámsters.")
				.build();

		Producto p11 = Producto.builder()
				.nombre("Pellets para Conejos Enanos")
				.precio(9.25)
				.stock(70)
				.fechaCaducidad(LocalDate.of(2026, 10, 1))
				.devolucion(false)
				.tipoMascota(TipoMascota.ROEDOR)
				.imagen("https://static3.zoomalia.com/cdn-cgi/image/width=800,height=800,format=auto/prod_img/15599/lm_cf9d006bd74b4ba33ed8fd389640b50f_st3.jpg")
				.descripcion("Pellets enriquecidos con vitaminas C y D para conejos enanos y cobayas.")
				.build();

		Producto p12 = Producto.builder()
				.nombre("Acuario Completo 60L con Filtro")
				.precio(89.00)
				.stock(5)
				.devolucion(true).tipoMascota(TipoMascota.PEZ)
				.imagen("https://m.media-amazon.com/images/I/91FFhy1kAJL._AC_SX679_.jpg")
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
		
		Cliente c2 = Cliente.builder()
				.nombre("Carlos Martínez Ruiz")
				.email("carlos.martinez@email.com")
				.telefono(698765432)
				.socioTienda(false)
				.build();
		
		Cliente c3 = Cliente.builder()
				.nombre("Ana Fernández Torres")
				.email("ana.fernandez@email.com")
				.telefono(655444333)
				.socioTienda(true)
				.build();
		
		Cliente c4 = Cliente.builder()
				.nombre("Pedro Sánchez Gil")
				.email("pedro.sanchez@email.com")
				.telefono(677888999)
				.socioTienda(false)
				.build();

		clienteRepository.saveAll(List.of(c1, c2, c3, c4));

		//Pedidos y LineaPedido (Corregidos con .lineas(new ArrayList<>()))
		Pedido pe1 = Pedido.builder()
				.fecha(LocalDate.now().minusMonths(2))
				.estadoPedido(EstadoPedido.ENTREGADO)
				.descripcion("Pedido mensual de María")
				.cliente(c1)
				.lineas(new ArrayList<>())
				.build();
		agregarLinea(pe1, p1, 2, true);
		agregarLinea(pe1, p3, 1, true);
		recalcularTotal(pe1);

		Pedido pe2 = Pedido.builder()
				.fecha(LocalDate.now().minusWeeks(3))
				.estadoPedido(EstadoPedido.ENVIADO)
				.descripcion("Encargo gato esterilizado")
				.cliente(c2)
				.lineas(new ArrayList<>())
				.build();
		agregarLinea(pe2, p4, 1, false);
		agregarLinea(pe2, p5, 1, false);
		recalcularTotal(pe2);

		Pedido pe3 = Pedido.builder()
				.fecha(LocalDate.now().minusDays(5))
				.estadoPedido(EstadoPedido.PENDIENTE)
				.descripcion("Pedido para terrario y aves")
				.cliente(c3)
				.lineas(new ArrayList<>())
				.build();
		agregarLinea(pe3, p6, 3, true);
		agregarLinea(pe3, p8, 1, true);
		agregarLinea(pe3, p9, 2, true);
		recalcularTotal(pe3);

		Pedido pe4 = Pedido.builder()
				.fecha(LocalDate.now().minusMonths(1))
				.estadoPedido(EstadoPedido.ENTREGADO)
				.descripcion("Material roedor y acuario")
				.cliente(c4)
				.lineas(new ArrayList<>())
				.build();
		agregarLinea(pe4, p10, 2, false);
		agregarLinea(pe4, p11, 1, false);
		agregarLinea(pe4, p12, 1, false);
		recalcularTotal(pe4);

		Pedido pe5 = Pedido.builder()
				.fecha(LocalDate.now().minusDays(1))
				.estadoPedido(EstadoPedido.PENDIENTE)
				.descripcion("Reposición pienso y arnés")
				.cliente(c1)
				.lineas(new ArrayList<>())
				.build();
		agregarLinea(pe5, p1, 1, true);
		agregarLinea(pe5, p2, 1, true);
		recalcularTotal(pe5);

		pedidoRepository.saveAll(List.of(pe1, pe2, pe3, pe4, pe5));
		productoRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
	}

	//Metodo para agregar una linea de pedido
	private void agregarLinea(Pedido pedido, Producto producto, int cantidad, boolean esSocio) {
		double precio = producto.getPrecioConDescuento(esSocio);
		LineaPedido linea = LineaPedido.builder()
				.cantidad(cantidad)
				.precioUnitario(precio)
				.subtotal(precio * cantidad)
				.producto(producto)
				.build();
		pedido.addLinea(linea);
		// Descuento de stock para reflejar lo vendido
		producto.setStock(Math.max(0, producto.getStock() - cantidad));
	}

	private void recalcularTotal(Pedido pedido) {
		double total = pedido.getLineas().stream()
				.mapToDouble(LineaPedido::getSubtotal).sum();
		pedido.setTotal(total);
	}
}


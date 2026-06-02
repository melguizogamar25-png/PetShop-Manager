# PetShop Manager

Este proyecto es una aplicación web para gestionar una tienda de productos para mascotas. Permite controlar los productos, los clientes y las ventas desde un mismo sitio.

## ¿De qué va la aplicación?
La aplicación sirve para que una tienda de mascotas pueda llevar el control de su negocio de forma fácil. Los administradores pueden gestionar el almacén y ver estadísticas, mientras que los clientes pueden entrar a ver el catálogo y comprar productos creando un pedido.

## Funcionalidades básicas
* **Gestión de Productos (CRUD):** Permite añadir nuevos productos, ver la lista, editarlos y eliminarlos. Cada producto tiene su nombre, precio, stock disponible y a qué tipo de mascota va dirigido (perro, gato, ave, etc.).
* **Gestión de Clientes (CRUD):** Registro de los clientes de la tienda con su nombre, correo electrónico y teléfono.
* **Gestión de Pedidos (Ventas):** Permite crear un carrito de compra para añadir varios productos con sus cantidades, calculando el precio total automáticamente.
* **Control de Stock:** El sistema avisa si intentas vender un producto sin existencias y resalta en la tabla los artículos con stock bajo (menos de 5 unidades).
* **Descuentos automáticos:** Se aplica un pequeño descuento en el total de la compra dependiendo del tipo de mascota del producto.

## Usuarios, Roles y Contraseñas
La aplicación utiliza Spring Security para proteger las pantallas. Dependiendo de con qué usuario entres, verás unas opciones u otras:

* **Administrador (ROLE_ADMIN):** Tiene acceso a todo. Puede crear, editar y borrar productos o clientes, ver el historial completo de ventas y las estadísticas de la tienda.
  * **Usuario:** admin
  * **Contraseña:** admin

* **Cliente (ROLE_CLIENTE):** Perfil limitado. Solo puede ver la lista de productos disponibles, añadir cosas a su carrito de la compra y ver sus propios pedidos. No puede borrar nada ni ver las pantallas del administrador.
  * **Usuario:** cliente
  * **Contraseña:** cliente

## Tecnologías utilizadas
* Java con Spring Boot
* Spring Data JPA e Hibernate (para guardar los datos)
* Base de datos H2
* Spring Security (para el login y control de accesos)
* HTML5, CSS3 y Bootstrap (para el diseño de las pantallas)
* Thymeleaf (para conectar el backend con las vistas)

## Cómo ponerlo en marcha
1. Clona el repositorio en tu ordenador.
2. Abre la consola de comandos en la carpeta del proyecto.
3. Ejecuta el comando para compilar: `mvn clean package`
4. Ejecuta el comando para arrancar el servidor: `mvn spring-boot:run`
5. Abre el navegador y entra en: `http://localhost:9000/

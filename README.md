# PetShop Manager

Aplicación web para gestionar una tienda de productos para mascotas. Permite controlar los productos, los clientes y las ventas desde un mismo sitio.

## ¿De qué va la aplicación?

La aplicación sirve para que una tienda de mascotas pueda llevar el control de su negocio de forma fácil. Los administradores pueden gestionar el almacén y ver estadísticas, mientras que los clientes pueden entrar a ver el catálogo y comprar productos usando un carrito de compra.

## Funcionalidades

- **Gestión de Productos (CRUD):** Añadir, ver, editar y eliminar productos. Cada producto tiene nombre, precio, stock, tipo de mascota, fecha de caducidad opcional e imagen.
- **Gestión de Clientes (CRUD):** Registro de clientes con nombre, correo y teléfono.
- **Gestión de Pedidos:** El admin puede crear pedidos, añadir líneas con productos y cantidades, y gestionar su estado (pendiente, enviado, entregado).
- **Carrito de compra:** Los clientes añaden productos a un carrito de sesión y confirman la compra al final. Al confirmar, se genera un ticket con el desglose.
- **Control de stock:** El sistema impide vender más unidades de las disponibles y avisa de productos con stock bajo.
- **Descuentos automáticos:** Se aplica un descuento en el precio según el tipo de mascota del producto (mayor para aves y reptiles, menor para perros y gatos).
- **Consultas:** Productos más vendidos por tipo de mascota, pedidos por rango de fechas, clientes con mayor gasto y productos con stock bajo.

## Usuarios y roles

| Rol | Usuario | Contraseña | Acceso |
|-----|---------|------------|--------|
| Administrador | admin | admin | Todo: productos, clientes, pedidos, consultas |
| Cliente | user | user | Catálogo, carrito y compra |

## Tecnologías

- Java con Spring Boot
- Spring Data JPA e Hibernate
- Base de datos H2 (en memoria)
- Spring Security
- Thymeleaf, HTML5, CSS3 y Bootstrap
- JavaScript para validaciones y cálculo dinámico del total

## Cómo ponerlo en marcha

1. Clona el repositorio en tu ordenador.
2. Abre la consola en la carpeta del proyecto.
3. Ejecuta: `mvn spring-boot:run`
4. Abre el navegador en: `http://localhost:9000`

La base de datos es H2 en memoria, así que no hace falta instalar nada. Los datos de ejemplo se cargan solos al arrancar.

La consola H2 está disponible en `http://localhost:9000/h2` por si quieres inspeccionar la base de datos directamente.

## Decisiones técnicas

- El carrito se gestiona en sesión (no se guarda en base de datos hasta que se confirma la compra), así cada usuario tiene el suyo propio sin interferir con otros.
- Los descuentos están calculados directamente en la entidad `Producto`, porque son una regla del negocio ligada al producto en sí, no a quién compra.
- La relación entre `Pedido` y `Producto` se hace a través de `LineaPedido`, que guarda el precio unitario en el momento de la compra para que no cambie si el producto se edita después.
- Los estados de pedido (`PENDIENTE`, `ENVIADO`, `ENTREGADO`) están definidos como enum. Solo se pueden añadir o quitar líneas mientras el pedido está en `PENDIENTE`.
- La entidad `Usuario` (autenticación) está separada de `Cliente` (datos del comprador), lo que permite que el admin gestione clientes sin ser él mismo uno.

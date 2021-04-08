# Bootcamp Mercado Libre - Desafío Spring 👩🏻‍💻

* **Autor:** Sofía Pigino
* **Fecha de Entrega:** 08/04/2021

## Contenido

### Actividad A
A continuación algunos ejemplos para ejecutar las consignas:
* Listado de todos los productos disponibles
    * `GET` http://localhost:8080/api/v1/articles
* Listado de los productos filtrados por categoría
    * `GET` http://localhost:8080/api/v1/articles?category=indumentaria
* Listado que permita la combinación de cualquiera de los filtros
    * `GET` http://localhost:8080/api/v1/articles?category=indumentaria&freeShipping=true
    * `GET` http://localhost:8080/api/v1/articles?brand=Taverniti&price=2400
* Listado ordenado alfabéticamente
    * Ascendente `GET` http://localhost:8080/api/v1/articles?order=0&category=indumentaria
    * Descendente `GET` http://localhost:8080/api/v1/articles?order=1&freeShipping=true
* Listado ordenado por precio 
    * Mayor a Menor `GET` http://localhost:8080/api/v1/articles?order=2&category=Deportes
    * Menor a Mayor `GET` http://localhost:8080/api/v1/articles?order=3&brand=Black %26 Decker
* Envío de Solicitud de Compra
    * `POST` http://localhost:8080/api/v1/purchase-request
    * JSON para el body request  
      `{  
      "articles":
      [
      {
      "productId":1,
      "name":"Desmalezadora",
      "brand":"Makita",
      "quantity":5
      },
      {
      "productId":5,
      "name":"Zapatillas Deportivas",
      "brand":"Nike",
      "quantity":2
      }
      ]
      }`

### Actividad B
A continuación algunos ejemplos para ejecutar las consignas:
* Carrito de Compras
    * Luego de hacer una o más Solicitudes de Compra
      `GET` http://localhost:8080/api/v1/shopping-cart
      
* Dar de alta a nuevos clientes
    * Para esto considero que un cliente es ya existente si su mail ya está registrado
    * `POST` http://localhost:8080/api/v1/clients/new 
    * JSON para el body request
      `{
      "name":"Sofia",
      "lastName":"Pigino",
      "province":"Cordoba",
      "email":"sofiapigino@gmail.com"
      }`
* Obtener un listado completo de todos los clientes
    * Luego de cargar uno o más clientes
      `GET` http://localhost:8080/api/v1/clients/all
* Obtener un listado de todos los clientes filtrados por Provincia
    * `GET` http://localhost:8080/api/v1/clients/province?province=cordoba
    
# Proyecto Semestral: Arquitectura de Microservicios - Tienda de Vinilos

Este repositorio contiene el desarrollo correspondiente a la evaluación integradora del Examen Final Transversal (EFT) de Arquitectura de Microservicios. El proyecto implementa el backend para una plataforma de venta de vinilos, diseñado bajo una arquitectura distribuida, de alta cohesión y bajo acoplamiento, desplegada de manera independiente en la nube.

## Tecnologías y Herramientas Utilizadas

El sistema ha sido construido aplicando estándares y mejores prácticas de la industria, utilizando el siguiente stack tecnológico:

* **Framework Principal:** Java 21 y Spring Boot 3.2.4.
* **Persistencia de Datos:** JPA e Hibernate para la ejecución de operaciones CRUD mediante repositorios `JpaRepository`.
* **Control de Versiones de BD:** Migraciones iniciales controladas y automatizadas mediante Flyway.
* **Validación de Datos:** Implementación de Bean Validation (JSR 380) para asegurar la integridad de la información procesada por los controladores.
* **Comunicación Inter-servicios:** Consumo de endpoints remotos mediante WebClient.
* **API Gateway:** Implementación centralizada para la gestión y enrutamiento de peticiones hacia los microservicios subyacentes.
* **Pruebas Unitarias:** Desarrollo de tests con JUnit y Mockito, garantizando un mínimo de 80% de cobertura lógica.
* **Documentación de API:** Generación de documentación formal e interactiva mediante Swagger/OpenAPI.
* **Despliegue y Producción:** Configuración y puesta en marcha de los servicios en la plataforma cloud Render.
* **Gestión del Proyecto:** Control de versiones en GitHub y planificación de tareas mediante Trello.

---

## Arquitectura y Buenas Prácticas

El desarrollo del código fuente prioriza la mantenibilidad y la legibilidad estructural:

* **Patrón CSR:** Separación estricta de responsabilidades en capas lógicas (Controller, Service y Repository).
* **Manejo Centralizado de Errores:** Uso de `@ControllerAdvice` para el control de excepciones, retornando respuestas estructuradas en formato JSON con sus respectivos códigos de estado HTTP.
* **Trazabilidad del Sistema:** Integración de logs estructurados mediante SLF4J para el registro de eventos transaccionales y monitoreo de las capas lógicas.

---

## Microservicios Implementados

Actualmente, la arquitectura se compone de 3 servicios de negocio principales. Cada uno opera de forma autónoma con su propia base de datos relacional y ha sido desplegado exitosamente en producción:

### 1. Cliente Service
Encargado de la gestión de identidad y datos de contacto de los usuarios. Implementa reglas de negocio para validar la unicidad de los registros (RUT y correo electrónico).
* **Documentación Swagger (Producción):** [https://cliente-service-72rx.onrender.com/swagger-ui/index.html](https://cliente-service-72rx.onrender.com/swagger-ui/index.html)

### 2. Pedido Service
Gestiona las órdenes de compra utilizando un modelo de datos Maestro-Detalle. Previo a la confirmación de un pedido, este servicio se comunica de manera síncrona con `cliente-service` a través de WebClient para validar la existencia y estado del usuario, garantizando la consistencia de los datos.
* **Documentación Swagger (Producción):** [https://pedido-service-pxrl.onrender.com/swagger-ui/index.html](https://pedido-service-pxrl.onrender.com/swagger-ui/index.html)

### 3. Reseña Service
Permite a los usuarios registrar calificaciones y comentarios asociados a los productos (vinilos) adquiridos, gestionando la integridad referencial de las interacciones de la comunidad.
* **Documentación Swagger (Producción):** [https://resena-service.onrender.com/swagger-ui/index.html](https://resena-service.onrender.com/swagger-ui/index.html)

---

## Instrucciones para Ejecución Local

El entorno local está configurado para un levantamiento ágil mediante bases de datos en memoria (H2) y parametrización a través de archivos `application.yml`.

1. **Clonar el repositorio:** ```bash
   git clone [TU_URL_DEL_REPOSITORIO]
# Plataforma Fullstack preparada para Render

Esta versión conserva la ejecución local con Docker Compose y agrega una modalidad educativa para desplegar en Render Free.

# Orden recomendado

1. Ejecutar `scripts/00_verificar_requisitos.ps1`.
2. Generar un `render.yaml` propio con `scripts/configurar-render.ps1 -Prefijo nombreunico`.
3. Ejecutar `scripts/01_validar_estructura.ps1`.
4. Probar localmente con `scripts/02_probar_render_local.ps1`.
5. Subir el repositorio a GitHub.
6. En Render Dashboard, crear un Blueprint y seleccionar el repositorio.
7. Probar el Gateway online con `postman_render_online.json`.


## Modalidades incluidas

1. `docker-compose.yml`: versión local original con Eureka dinámico.
2. `docker-compose.render-local.yml`: simulación local del perfil Render Free, sin Eureka.
3. `render.yaml.template`: plantilla para estudiantes.
4. `render.yaml`: ejemplo generado para el prefijo `demo`.
5. `scripts/configurar-render.ps1`: genera un `render.yaml` con un prefijo único.
6. `render/docker/*.Dockerfile`: Dockerfiles multietapa para que Render compile cada servicio desde GitHub.
7. `postman_render_online.json`: colección Postman para probar la URL pública del Gateway.

## Generar un render.yaml propio

```powershell
.\scripts\configurar-render.ps1 -Prefijo nombreusuario
```

Usar un prefijo corto, único, en minúsculas y sin espacios.

## Nota educativa

Render Free no permite que los Web Services reciban tráfico por red privada. Por eso, esta modalidad usa URLs HTTPS públicas entre servicios y desactiva Eureka en Render. Eureka sigue disponible y funciona normalmente en la etapa local con Docker Compose.

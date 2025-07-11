# task-manager-api

Este es el backend de una aplicación de gestión de tareas, desarrollado como parte de una prueba técnica.

## Tecnologías utilizadas

- Java 21  
- Spring Boot 3.5.3  
- Spring WebFlux (programación reactiva)  
- Clean Architecture  
- JUnit 5 + Mockito + StepVerifier  
- Docker  
- Gradle  

## Funcionalidades

- Crear, completar, eliminar y filtrar tareas  
- Programación reactiva con Mono y Flux  
- Pruebas unitarias con cobertura de lógica de negocio  
- Contenerización con Docker  
- Infraestructura como código  
- Validaciones con Bean Validation  
- Seguridad básica con Spring Security  

## Cómo ejecutar el proyecto

1. Clona el repositorio  
2. Ejecuta:

```bash
./gradlew bootRun

##  Ejecutar pruebas unitarias

./gradlew test

##  Docker

docker build -t task-manager-api .
docker run -p 8080:8080 task-manager-api
docker-compose up --build

##  Endpoints principales

GET /api/tasks
POST /api/tasks
PATCH /api/tasks/{id}/complete
DELETE /api/tasks/{id}
GET /api/tasks/category/{category}


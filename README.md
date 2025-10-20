# 🗞️ Periódico de ayer

## Objetivos

El propósito principal de este proyecto es desarrollar una API REST con Spring Boot que gestione los artículos de un periódico digital.
La aplicación está construida siguiendo una arquitectura MVC de tres capas, con un enfoque cliente-servidor, y se conecta a una base de datos PostgreSQL para almacenar la información de manera eficiente y estructurada.

## Competencias Técnicas

Este proyecto desarrolla las siguientes competencias técnicas:

- **Backend Development:** Implementación de la lógica del servidor y los endpoints RESTful.
- **Database Creation:** Creación y estructuración de la base de datos PostgreSQL.
- **Data Access Components:** Desarrollo de componentes que permiten la comunicación entre la API y la base de datos.
- **Tests:** Validación del comportamiento del sistema utilizando herramientas como JUnit y Mockito.

  
## Tecnologías y Herramientas

Este proyecto fue desarrollado utilizando un conjunto moderno de tecnologías y herramientas que garantizan rendimiento, escalabilidad y buenas prácticas en el desarrollo backend:

- **Lenguaje de Programación:** Java 21
- **Framework Backend:** Spring Boot 3.3.5
- **Base de Datos:** PostgreSQL 42.7.3
- **Gestión de Proyectos:** Jira
- **Control de Versiones:** Git - GitHub
- **Pruebas de API:** Postman 11.41
- **Testing:**
  - spring-boot-starter-test 
  - Mockito
  - JUnit

## Funcionalidades

La API ofrece un conjunto completo de operaciones para la gestión del periódico:

- **Registrar nuevos artículos:** Permite crear artículos con título, contenido, fecha de publicación, categoría y autor.
- **Listar artículos:** Devuelve todos los artículos almacenados en el sistema.
- **Buscar artículo por ID:** Obtiene la información de un artículo específico mediante su identificador único.
- **Actualizar artículos:** Permite modificar los datos de un artículo existente.
- **Eliminar artículos:** Elimina permanentemente un artículo de la base de datos.
- **Registrar usuarios:** Crea nuevos usuarios indicando su nombre y correo electrónico (sin autenticación en esta fase).

**Relaciones:**
  - Usuario → Artículos (1:N): Cada usuario puede crear varios artículos.
  - Artículo → Usuario (N:1): Cada artículo pertenece a un único usuario.

**Validaciones:**
  - El título no puede estar vacío ni superar los 255 caracteres.
  - El contenido debe tener entre 50 y 2000 caracteres.
  - Eliminación en cascada: Si se elimina un usuario, todos sus artículos asociados se eliminan automáticamente.

## Cómo iniciar el proyecto

### Requisitos previos
- Java 21 instalado
- PostgreSQL instalado y en ejecución
- Maven 

### Pasos para iniciar el proyecto

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/PeriodicoDeAyer/PeriodicoDeAyer.git
   ```

2. **Configurar la base de datos**
   - Crear una base de datos en PostgreSQL:
   ```sql
   CREATE DATABASE periodico_ayer;
   ```

3. **Verificar la instalación**
   - La API estará disponible en: `http://localhost:8080`
   - Puedes probar los endpoints con Postman o cualquier cliente HTTP


## Contactos

¿Tienes dudas o quieres saber más sobre el proyecto?

Puedes contactar a las desarrolladoras a través de sus perfiles profesionales:

<table style="width:100%; border-collapse: collapse; border: none; text-align:center;">
  <tr>
    <td style="border: none; padding: 10px;">
      <b>Ana Aguilera</b><br>
      <a href="https://www.linkedin.com/in/ana-aguilera-morales-011b1a238/" target="_blank">LinkedIn</a> |
      <a href="https://github.com/AnaAguileraMorales88" target="_blank">GitHub</a>
    </td>
    <td style="border: none; padding: 10px;">
      <b>Andrea Olivera</b><br>
      <a href="http://www.linkedin.com/in/andrea-olivera-romero-x" target="_blank">LinkedIn</a> |
      <a href="https://github.com/andreaonweb" target="_blank">GitHub</a>
    </td>
    <td style="border: none; padding: 10px;">
      <b>Daniella Pacheco</b><br>
      <a href="https://www.linkedin.com/in/daniellapacheco/" target="_blank">LinkedIn</a> |
      <a href="https://github.com/DaniPacheco8" target="_blank">GitHub</a>
    </td>
  </tr>
</table>


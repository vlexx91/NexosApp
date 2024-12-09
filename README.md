# API REST para la Gestión de Personas Desaparecidas  

Este es el backend de una aplicación dedicada a la gestión de información sobre personas desaparecidas. Proporciona funcionalidades para registrar casos de desaparición, añadirlos a seguimiento, comentar sobre ellos, subir imágenes y gestionar otros aspectos relacionados con la búsqueda y seguimiento.  

## Características  

- **Gestión de casos de desaparición**: Registro, actualización y eliminación de información.  
- **Seguimiento de casos**: Permite a los usuarios seguir casos específicos.  
- **Comentarios**: Los usuarios pueden añadir comentarios relacionados con los casos.  
- **Subida de imágenes**: Integración con Cloudinary para gestionar imágenes asociadas a los casos.  
- **Geolocalización**: Uso de OpenCage para gestionar ubicaciones.  
- **Autenticación y autorización**: Implementado con Spring Security y JWT.  
- **Documentación de la API**: Generada con Swagger UI para facilitar el uso de los endpoints.  

## Tecnologías Utilizadas  

### Backend  

- **Spring Boot**: Framework principal para el desarrollo del backend.  
- **Spring Security**: Gestión de autenticación y autorización.  
- **JWT (JSON Web Tokens)**: Manejo de tokens para la autenticación de usuarios.  
- **Lombok**: Reducción de código repetitivo en las entidades y clases de configuración.  
- **MapStruct**: Para el mapeo de DTOs y entidades.  
- **JavaMailSender**: Envío de notificaciones por correo electrónico.  
- **Cloudinary**: Gestión y almacenamiento de imágenes.  
- **OpenCage API**: Conversión de direcciones en coordenadas geográficas (geocoding).   

### Herramientas de Desarrollo  

- **Swagger UI**: Generación de la documentación interactiva para la API.  
- **Node.js**: Integración para tareas auxiliares relacionadas con el backend.  

## Configuración del Proyecto  

### Prerrequisitos  

- **Java 17 o superior**: Necesario para ejecutar Spring Boot.  
- **Maven**: Para la gestión de dependencias.  
- **Acceso a una base de datos**: Configurable en el archivo `application.properties` o `application.yml`.  
- **Credenciales de servicios externos**:  
  - Cloudinary  
  - OpenCage API  
  - Configuración de correo para JavaMailSender  

### Instalación  

1. **Clonar el repositorio**:
   
   ```bash  
   git clone https://github.com/vlexx91/NexosApp.git  
   cd nombre-del-repositorio

3. **Configurar las propiedades**:
   Modifica el archivo src/main/resources/application.properties con tus credenciales y configuraciones específicas:
   
    ```bash  
    spring.datasource.url=jdbc:jdbc:postgresql://localhost:5432/your_bbdd
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    cloudinary.cloud_name=tu_cloud_name
    cloudinary.api_key=tu_api_key
    cloudinary.api_secret=tu_api_secret
    opencage.api_key=your_api_key

4. **Compilar y ejecutar**:
   
   ```bash
   mvn clean install
   mvn spring-boot:run

5. **Acceder a Swagger UI**:
   Una vez ejecutado el proyecto, puedes acceder a la documentación en http://localhost:8080/swagger-ui/index.html.

## Endpoints Principales

  - **/autoridad**:Gestión de todo lo relacionado con el tipo de usuario autoridad.
  
  - **/aviso**:Gestión de todo lo relacionado con avisos.

  - **/civil**:Gestión de todo lo relacionado con el tipo de usuario civil.

  - **/comentario**:Gestión de todo lo relacionado con comentarios.

  - **/desaparicion**:Gestión de todo lo relacionado con las desapariciones y su forma de ser mostradas.

  - **/foto**:Gestión de todo lo relacionado con las imagenes de desapariciones, comentarios y avisos.

  - **/lugar**:Gestión de todo lo relacionado con los lugares de las desapariciones.

  - **/notificacion**:Gestión de todo lo relacionado con las notificaciones.

  - **/persona**:Gestión de todo lo relacionado con la persona desaparecida.

  - **/usuario**:Gestión de todo lo relacionado con las tareas básicas de usuario.

## Contribución

1. **Realiza un fork del repositorio.**

2. **Crea tu rama para tu funcionalidad**
   
   ```bash
   git checkout -b feature/nueva-funcionalidad
   
4. **Realiza un pull request con tus cambios.**



  


  



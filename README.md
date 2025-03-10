# CreditAplication

## Descripción
CreditAplication es un proyecto de demostración basado en Spring Boot para la gestión de créditos. Utiliza diversas tecnologías para la persistencia de datos, seguridad y gestión de dependencias.

## Requisitos previos
Antes de ejecutar el proyecto, asegúrese de tener instalados los siguientes componentes:
- **Java 17**
- **Maven**
- **PostgreSQL**


## Instalación y Ejecución
1. **Clonar el repositorio:**
   ```sh
   git clone <URL_DEL_REPOSITORIO>
   cd CreditAplication
   ```
2. **Configurar PostgreSQL:**
   - Crear una base de datos en PostgreSQL llamada credit_application_u_central con usuario credit_application_u_central y contraseña 12345.
   - Configurar el archivo `application.properties` o `application.yml` con las credenciales de acceso a la base de datos: 

    spring.datasource.url=jdbc:postgresql://localhost:5432/credit_application_u_central
    spring.datasource.username=credit_application_u_central
    spring.datasource.password=12345

  En url poner la cadena de conexion de su database, en username credit_application_u_central y en password 12345.

3. **Compilar y ejecutar el proyecto:**
   ```sh
   mvn clean install
   ```
4. Abrir la applicacion desde el compilador, idealmente usar intellij idea o Eclipse Spring boot.
5. Ejecutar la aplicacion desde el comilador.

## Licencia
Este proyecto está bajo la licencia MIT.


User management API - Prueba Tecnica

AI=PI REST para gestion de usuarios desarrollada con *Spring boot* y Java 17

>> Caracteristicas

- CRUD complete de usuarios
- Filtrado dinamico con operadores (co, eq, sw, ew)
- Ordenamiento por multiples campos
- Autenticacion con tax_id y password
- Encriptacion AES256 para passwords
- Validacion AndresFormat para telefonos
- Zona Horaria Madagascar para timestamps
- Documentacion Swagger/OpenAPI
- Unit Tests con JUnit 5 y Mockito
- Docker y Docker Compose
- Coleccion Postman incluida

>> Tecnologias

- Java 17
- Spring Boot 3.5.7
- Maven 4.0.0
- Swagger OpenAPI 3
- JUnit 5 + Mockito
- Docker
- Lombok

>> Instalacion y Ejecucion

- Opcion 1: Ejecucion Local con Maven

>>> Prerequisitos

- JDK 17 Instalado
-Maven 3.6.0 o superior

>>> Pasos 

1. Clonar el repositorio
'''bash
git clone https://github.com/juanch-portf/PruebaTecnica.git

2. Compilar el Proyecto
'''bash
mvn clean install

3. Ejecutar tests

mvn test

4. Ejecutar la aplicacion

mvn spring-boot:run

5. Acceder a la aplicacion 

- API: http://localhost:9001
- Swagger UI: http://localhost:9001/swagger-ui.html
- API Docs: http://localhost:9001/api-docs

-------------------------------------------------------------------
>> 🐋 Docker

Lo primero y necesario es navegar a la carpeta del proyecto

### Construcción y ejecución con Docker Compose
```bash
# Construir y ejecutar
docker-compose up -d --build

# Ver logs
docker-compose logs -f

# Detener
docker-compose down
```

### Construcción manual
```bash
# Construir imagen
docker build -t prueba-chakray:1.0.0 .

# Ejecutar contenedor
docker run -d -p 9001:9001 --name prueba-api prueba-chakray:1.0.0

# Ver logs
docker logs -f prueba-api
```

### Cargar mi imagen ###
# Cargar la imagen
docker load -i Angel_Chimal_Docker.tar

# Ejecutar
docker run -d -p 9001:9001 --name prueba-api prueba-chakray:1.0.0

### Acceder a la aplicación

- API: http://localhost:9001
- Swagger: http://localhost:9001/swagger-ui/index.html

----------------------------------------------------------------------------

>> EndPoints de la API

>>> Gestion de Ususarios

>>> 1. Obtener todos los usuarios

GET /users
GET /users?sortedBy=name
GET /users?filter=name+co+user
GET /users?

>> Parámetros:
- `sortedBy` (opcional): Campo para ordenar (email, id, name, phone, tax_id, created_at)
- `filter` (opcional): Filtro en formato `field+operator+value`

>> Operadores de filtro:
- `co` - Contains (contiene)
- `eq` - Equals (igual)
- `sw` - Starts with (empieza con)
- `ew` - Ends with (termina con)

>> Ejemplos de filtros:

/users?filter=name+co+user
/users?filter=email+ew+mail.com
/users?filter=phone+sw++52
/users?filter=tax_id+eq+AARR990101XXX

>>> 2. Crear un usuario

POST /users
Content-Type: application/json

{
  "email": "nuevo@mail.com",
  "name": "nuevo usuario",
  "phone": "+52 55 123 456 78",
  "password": "mySecurePassword",
  "tax_id": "AAAA990101XXX",
  "addresses": [
    {
      "id": 1,
      "name": "home",
      "street": "Calle Principal 123",
      "country_code": "MX"
    }
  ]
}

>>> 3. Actualizar un usuario

PATCH /users/{id}
Content-Type: application/json

{
  "name": "nombre actualizado",
  "email": "nuevo-email@mail.com"
}

>>> 4. Eliminar un usuario

DELETE /users/{id}

>> Autenticacion <<

POST /login
Content-Type: application/json

{
  "tax_id": "AARR990101XXX",
  "password": "password123"
}

>> VALIDACIONES <<

>>> RFC (tax_id)
- Formato: 4 letras + 6 digitos + 3 caracteres
- Ejemplo: `AARR990101XXX`
- Debe ser único en el Sistema

>>> Telefono (AndresFormat)

- Mínimo 10 dígitos
- Puede incluir código de país
- Ejemplos válidos:
  - `+52 55 123 456 78`
  - `55 123 456 78`
  - `5512345678`

>>> Password
- Encriptado con AES256
- Nunca se devuelve en las respuestas

>>> Timestamp

- Zona horaria: Madagascar (Indian/Antananarivo - UTC+3)
- Formato: `dd-MM-yyyy HH:mm:ss`

>> Datos Iniciales <<

La aplicación se inicializa con 3 usuarios de prueba:

**Usuario 1:**
- Email: user1@mail.com
- Tax ID: AARR990101XXX
- Phone: +1 55 555 555 55
- Password: 7c4a8d09ca3762af61e59520943dc26494f8941b

**Usuario 2:**
- Email: user2@mail.com
- Tax ID: BBCC950505YYY
- Phone: +52 55 123 456 78

**Usuario 3:**
- Email: user3@example.com
- Tax ID: DDEE880808ZZZ
- Phone: 55 987 654 32

>> Troubleshooting

>>> Error: "Name for argument not specified"
Asegúrate de que Maven compile con el flag `-parameters`:
```xml

    org.apache.maven.plugins
    maven-compiler-plugin
    
        true
    

```

>>> Error de Lombok
Si tienes problemas con Lombok, asegúrate de tenerlo instalado en tu IDE:
- IntelliJ IDEA: Instalar plugin Lombok y habilitar "Enable annotation processing"
- Eclipse: Ejecutar `lombok.jar` como instalador

## Contacto

Desarrollador: Angel Chimal  
Email: juanangelchimalgomez@gmail.com

>> Licencia

Este proyecto fue desarrollado como prueba técnica para Chakray.


**Nota:** Este proyecto incluye el easter egg solicitado en la prueba técnica.

<h1 align="center"> 🌐 FORO HUB 🌐 </h1>

Este proyecto fue realizado con el objetivo de realizar el Challenge del curso de Java con Spring Boot, el cual tiene la funcionalidad de una API REST para una foro en donde
se podrá registrar, modificar, actulizar y eliminar tópicos de diferentes temas a tratar, asi como tambien la posibilidad de poder hacer login con una usuario y contraseña
mediante la generacion de tokens de sesión.
 
<h2> :hammer: Funcionalidades del proyecto</h2>

- 🧑‍💻: `/login [ POST ]` :: Se inicia sesión con usuario y contraseña
- :memo:: `/topics [ POST ]` : Crea un nuevo tópico.
- :clipboard:: `/topics [ GET ]` : Obtiene todos los tópicos creados mediante paginación.
- 🔎: `/topics/{id} [ GET ]` : Regresa el detalle de un tópico en especifico mediante el {id}.
- ✏️: `/topics/{id} [ PUT ]` : Actualiza un tópico mediante el { id }.
- ❌: `/topics/{id} [ DELETE ]` : Muestra los libros por el idioma en el que se encuentran.


<h2>🧑‍💻 Tecnologías Utilizadas </h2>

- Java
- Spring Boot 3
- JPA
- PostgreSQL
- Open API (Swagger)
- Spring Security
- JWT

<h2> 🧑‍💻 Instalación </h2>

1. Clona este repositorio.
2. Abre el proyecto en tu IDE preferido.
3. Crear la base de datos de manera local.
4. Configura las credenciales de la conexión hacia la base de datos en el archivo de propiedades (`application.properties` o `application.yml`).
5. Ejecuta la aplicación.

# JWT Notes App
![image](https://github.com/not-nic/JWT-Notes-App/assets/67616855/c70de14a-627b-42a6-ab08-4330f581b694)
## Problem Description
This is a project that allows authenticated users to create, delete and view their notes through Postman. This was primarily a learning experience to understand how to use Spring Security with JWTs (Json Web Tokens). For Spring Security, I have utilised code from this video by Amigoscode, to create the [JwtTokenService](https://github.com/not-nic/JWT-Notes-App/blob/master/src/main/java/uk/notnic/jwtnotes/service/JwtTokenService.java). Most of this code was outdated, so I have researched and updated it for Spring Security 6. 

However, the notes implementation is my own and the majority of this code can be viewed from the Note [Service](https://github.com/not-nic/JWT-Notes-App/blob/master/src/main/java/uk/notnic/jwtnotes/service/NotesService.java) & [Controller](https://github.com/not-nic/JWT-Notes-App/blob/master/src/main/java/uk/notnic/jwtnotes/controller/NotesController.java).

## Install Guide
1. Clone this repository on your own machine with the following command:
```bash
https://github.com/not-nic/JWT-Notes-App.git
cd JWT-Notes-App
```
### Docker build
2.  Build the project using Docker Compose:
```bash
docker-compose build
```
3. Start the docker containers with the Postgres & Spring Boot application:
```bash
docker-compose up -d
```
4. check the Spring Boot & Postgres containers are running:
```bash
docker-compose ps
```
### Gradle build
2. Build the application using gradle if you have it installed, or use `./gradlew` if you do not.
```bash
$ gradle build
# or
$ ./gradlew build
```
3.  Run the application once it has built:
```bash
$ java -jar build/libs/JWTNotes.jar
```
## Usage
To use the application once it has built, download this [postman collection](https://red-rocket-177263.postman.co/workspace/Team-Workspace~7a93b06e-8521-436b-8b23-708f04071a50/collection/20934928-882d86a3-b571-40ec-a910-7993c323d471?action=share&creator=20934928) or use *cURL*.

- **POST**: http://localhost:8080/api/auth/register - Register new user.
	```json
	{"username" : "test",
	 "email" : "nic@email.com",
	 "password" : "test"}
	 ```
- **POST**: http://localhost:8080/api/auth/login - Login / recieve new token.
	```json
	{"username" : "test",
	 "password" : "test"}
	```	
- **POST**: http://localhost:8080/api/notes - Create note.
	```json
	{ "content" : "this is a test note for testing."}
	```
- **GET**: http://localhost:8080/api/notes/ - Get users notes.
- **GET**: http://localhost:8080/api/notes/all - If admin, get all notes from the database
- **DEL:** http://localhost:8080/api/notes/2 - if authed, delete their own note by changing path variable.

# Star Wars web app - Server

Star Wars web app - Server: is a server that manage the app logic (such as sorting) the DB and the data from swapi.co. 
A client that use this server you can find on : https://github.com/edenswissa/starWarsWebApp

### Install 
```sh
$ git clone https://github.com/edenswissa/starWarsServer.git
```

 - Open new Java maven project from existing source , in your IDE 
(I will recommand on IntelliJ) choose this folder.
- Run maven clean.
- Run maven install.
- Run the project.

--- You can check if the project run properly on "http://localhost:8080/api/health"  



### Tech

* [Java] - main languege
* [Spring Boot] - fraemwork that help to manage java web server
* [Maven] - software project management and comprehension tool


### Objects Classes
 
* Character (name,url,films - list of strings - films url)
* Film (title,id,url,numOfFavoriteCharacters)
* User (userName, password, uuid, favCharacters -list of Character, films - list of Film)

### Api 

*  http://localhost:8080/api/health - GET
return "Star Wars Server works" to check if the server is running.

* http://localhost:8080/api/getPeoples - POST
connect to swapi.co and return list as json array of characters
(name, url(on swapi.co),films(list of swapi.co url's movies that this character is participates in))

* http://localhost:8080/api/login - POST
get json with userName and password, check if user is exist in the DB, if he is it will return the user data
(userName, password, uuid, favCharacters(list of Characters), fims (list of sorted Films)) - as json
else create new user(userName , password, uuid) and save it to DB and return it to client.

* http://localhost:8080/api/getUser- POST
get json with uuid , if uuid exist on DB - return the user to client. else return HttpStatus 401 with message - "uuid does not exist on DB".
(client need to redirect to login page)

* http://localhost:8080/api/saveFavorites - POST
get User as json from client, the user need to have list of favorite characters. the method responsive of
sorting films order by favorite characters, save this list to DB as property of the user.
return user that contain list of sorted films.

### DB
* Firebase - free DB by google , based on mongoDB

**Enjoy!!!**

[Java]: <https://www.oracle.com/technetwork/java/index.html>
[Spring Boot]: <https://spring.io/projects/spring-boot>
[Maven]: <https://maven.apache.org>
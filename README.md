README :
Install:
1. Download the project with git clone https://github.com/edenswissa/starWarsServer.git
2. Open new Java maven project from existing source , in your IDE (I will recommand on IntelliJ)
   choose this folder.
3. Run maven clean.
4. Run maven install.
5. Run the project.
*6. You can check if the project run properly on "http://localhost:8080/api/health"  

Enjoy. 


Technology:

The project is written in java with spring boot freamwork (using maven)

Objects: 

1.Character (name,url,films - list of strings - films url)
2.Film (title,id,url,numOfFavoriteCharacters)
3.User (userName, password, uuid, favCharacters -list of Character, films - list of Film)

Api:

1.http://localhost:8080/api/health - GET
return "Star Wars Server works" to check if the server is running.

2.http://localhost:8080/api/getPeoples - POST
connect to swapi.co and return list as json array of characters
(name, url(on swapi.co),films(list of swapi.co url's movies that this character is participates in))

3.http://localhost:8080/api/login - POST
get json with userName and password, check if user is exist in the DB, if he is it will return the user data
(userName, password, uuid, favCharacters(list of Characters), fims (list of sorted Films)) - as json
else create new user(userName , password, uuid) and save it to DB and return it to client. 

4.http://localhost:8080/api/getUser- POST
get json with uuid , if uuid exist on DB - return the user to client. else return HttpStatus 401 with message - "uuid does not exist on DB".
(client need to redirect to login page)

5.http://localhost:8080/api/saveFavorites - POST
get User as json from client, the user need to have list of favorite characters. the method responsive of
sorting films order by favorite characters, save this list to DB as property of the user.
return user that contain list of sorted films.

DB:
firebase - free DB by google , based on mongoDB

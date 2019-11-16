package eden.starWarsApiServer.star_wars_server.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import eden.starWarsApiServer.star_wars_server.objects.Film;
import eden.starWarsApiServer.star_wars_server.objects.User;
import eden.starWarsApiServer.star_wars_server.services.DBService;
import eden.starWarsApiServer.star_wars_server.services.RestService;
import eden.starWarsApiServer.star_wars_server.services.SortingService;
import eden.starWarsApiServer.star_wars_server.services.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClientController {

    @Autowired
    SwapiService swapiService;

    @Autowired
    DBService dbService;

    @Autowired
    SortingService sortingService;

    @Value("${firebase.db.url}")
    String dbUrl;

    @RequestMapping(method = RequestMethod.GET, path = "/health")
    public String test() {
        System.out.println("Star Wars Server works");
        return "Star Wars Server works";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/getPeoples")
    public ResponseEntity<String> getPeoples() {
        try {
            JsonArray peoples = swapiService.getPeoples();
            return new ResponseEntity<String>(peoples.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "signUp")
    public ResponseEntity<String> signUp(@RequestBody String bodyStr) {
        try {
            User user = new Gson().fromJson(bodyStr, User.class);
            ResponseEntity<String> postResponse = RestService.post(dbUrl+"/users.json",null,user.toString());
            JsonObject postResponseBody = new Gson().fromJson(postResponse.getBody(),JsonObject.class);
            String uuid = postResponseBody.get("name").getAsString();
            JsonObject putRequestBody = new JsonObject();
            putRequestBody.addProperty(user.getUserName(),uuid);
            RestService.put(dbUrl+"/usersUuid/"+user.getUserName()+".json",null,putRequestBody.toString());
            return postResponse;
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "login")
    public ResponseEntity<String> signIn(@RequestBody String bodyStr) {
        try {
            Gson gson = new Gson();
            JsonObject body = gson.fromJson(bodyStr, JsonObject.class);
            String userName = body.get("userName").getAsString();
            String password = body.get("password").getAsString();
            ResponseEntity<String> getUuidResponse = RestService.get(dbUrl+"/usersUuid/"+userName+".json",null);
            if(!getUuidResponse.getBody().equals("null")) {
                JsonObject jsonObject = gson.fromJson(getUuidResponse.getBody(),JsonObject.class);
                String uuid = jsonObject.get(userName).getAsString();
                ResponseEntity<String> getUserResponse = RestService.get(dbUrl+"/users/"+uuid+".json",null);
                User user = gson.fromJson(getUserResponse.getBody(),User.class);
                user.setUuid(uuid);
                if(user.getPassword().equals(password)) {
                    return new ResponseEntity<String>(user.toString(),HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("password is incorrect",HttpStatus.UNAUTHORIZED);
                }
            } else {
                ResponseEntity<String> postResponse = RestService.post(dbUrl+"/users.json",null,bodyStr);
                JsonObject postResponseBody = new Gson().fromJson(postResponse.getBody(),JsonObject.class);
                String uuid = postResponseBody.get("name").getAsString();
                User newUser = new User();
                newUser.setUuid(uuid);
                newUser.setUserName(userName);
                newUser.setPassword(password);
                JsonObject putRequestBody = new JsonObject();
                putRequestBody.addProperty(userName,uuid);
                RestService.put(dbUrl+"/usersUuid/"+userName+".json",null,putRequestBody.toString());
                return new ResponseEntity<String>(newUser.toString(),HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.POST, path = "getUser")
    public ResponseEntity<String> getUser(@RequestBody String bodyStr) {
        try {
            Gson gson = new Gson();
            JsonObject body = gson.fromJson(bodyStr, JsonObject.class);
            String uuid = body.get("uuid").getAsString();
            User user = dbService.getUser(uuid);
            if(user == null) {
                return new ResponseEntity<>("uuid does not exist on DB",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(user.toString(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "saveFavorites")
    public ResponseEntity<String> saveFavorites(@RequestBody String bodyStr) {
        try {
            User user = new Gson().fromJson(bodyStr,User.class);
            ArrayList<Film> films = swapiService.getFilms();
            films = sortingService.filmSorting(user.getFavCharacters(),films);
            user.setSortedFilms(films);
            dbService.savFavorites(user.getUuid(),user.getFavCharacters());
            dbService.saveSortingFilmList(user.getUuid(),films);
            return new ResponseEntity<String>(user.toString(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

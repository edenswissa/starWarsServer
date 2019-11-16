package eden.starWarsApiServer.star_wars_server.services;

import com.google.gson.Gson;
import eden.starWarsApiServer.star_wars_server.objects.Character;
import eden.starWarsApiServer.star_wars_server.objects.Film;
import eden.starWarsApiServer.star_wars_server.objects.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DBService {

    @Value("${firebase.db.url}")
    String dbUrl;

    public void saveSortingFilmList(String uuid, ArrayList<Film> sortedList) {
        String url = dbUrl+"/users/"+uuid+"/films.json";
        RestService.put(url,null,sortedList.toString());
    }

    public void savFavorites(String uuid, ArrayList<Character> characters) {
        String url = dbUrl+"/users/"+uuid+"/favCharacters.json";
        RestService.put(url,null,characters.toString());
    }

    public User getUser(String uuid) {
        String url = dbUrl+"/users/"+uuid+".json";
        ResponseEntity<String> getUserResponse = RestService.get(url,null);
        if(!getUserResponse.getBody().equals("null")) {
            User result = new Gson().fromJson(getUserResponse.getBody(),User.class);
            return result;
        } else {
            return null;
        }

    }

}

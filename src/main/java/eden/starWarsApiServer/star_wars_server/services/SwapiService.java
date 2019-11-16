package eden.starWarsApiServer.star_wars_server.services;

import com.google.gson.*;
import eden.starWarsApiServer.star_wars_server.objects.Character;
import eden.starWarsApiServer.star_wars_server.objects.Film;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SwapiService {

    public JsonArray getPeoples() throws Exception {
        JsonArray charactersResult = new JsonArray();
        Map<String,Object> headers = new HashMap<>();
        headers.put("user-agent","Application");
        Gson gson = new Gson();
        Integer numOfPages = 1;
        Boolean hasNextPage = true;
        do {
            ResponseEntity<String> response = RestService.get("https://swapi.co/api/people?page="+numOfPages, headers);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                JsonObject jsonResponse = gson.fromJson(response.getBody(),JsonObject.class);
                JsonArray charactersInCurrentPage = jsonResponse.getAsJsonArray("results");
                for(JsonElement characterElement : charactersInCurrentPage) {
                    Character character = gson.fromJson(characterElement,Character.class);
                    charactersResult.add(character.toJson());
                }
                hasNextPage = !jsonResponse.get("next").isJsonNull();
                numOfPages++;
            } else {
                throw new Exception("getPeoples FAILED - response code:" + response.getStatusCode());
            }
        } while (hasNextPage);
        return charactersResult;
    }

    public ArrayList<Film> getFilms() throws Exception{
        ArrayList<Film> films = new ArrayList<>();
        Map<String,Object> headers = new HashMap<>();
        headers.put("user-agent","Application");
        ResponseEntity<String> response = RestService.get("https://swapi.co/api/films",headers);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            JsonObject jsonResponse = new Gson().fromJson(response.getBody(),JsonObject.class);
            JsonArray filmsArray = jsonResponse.getAsJsonArray("results");
            for(JsonElement filmElement : filmsArray) {
                Film film = new Gson().fromJson(filmElement,Film.class);
                films.add(film);
            }
        } else {
            throw new Exception("getFilms FAILED - response code:" + response.getStatusCode());
        }
        return films;
    }
}









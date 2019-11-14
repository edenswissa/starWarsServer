package eden.starWarsApiServer.star_wars_server.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SwapiService {

    public JsonArray getPeoples() {
        Map<String,Object> headers = new HashMap<>();
        headers.put("user-agent","Application");
        ResponseEntity<String> response = RestService.get("https://swapi.co/api/people",headers);
        return null;
    }
}

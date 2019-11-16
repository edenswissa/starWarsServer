package eden.starWarsApiServer.star_wars_server.objects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Character {

    private String name;
    private String url;
    private ArrayList<String> films;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<String> films) {
        this.films = films;
    }

    public JsonObject toJson() {
        return new Gson().fromJson(this.toString(),JsonObject.class);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

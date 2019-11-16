package eden.starWarsApiServer.star_wars_server.objects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class User {

    private String userName;
    private String password;
    private String uuid;
    private ArrayList<Character> favCharacters;
    private ArrayList<Film> films;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Character> getFavCharacters() {
        return favCharacters;
    }

    public void setFavCharacters(ArrayList<Character> characters) {
        this.favCharacters = characters;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ArrayList<Film> getSortedFilms() {
        return films;
    }

    public void setSortedFilms(ArrayList<Film> sortedFilms) {
        this.films = sortedFilms;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public JsonObject toJson() {
        return new Gson().fromJson(this.toString(),JsonObject.class);
    }
}

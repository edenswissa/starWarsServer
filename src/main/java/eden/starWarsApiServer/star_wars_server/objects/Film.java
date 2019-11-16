package eden.starWarsApiServer.star_wars_server.objects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class Film implements Comparable {

    private String title;
    private String id;
    private String url;
    private Integer numOfFavoriteCharacters;

    public Film() {
        this.numOfFavoriteCharacters = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNumOfFavoriteCharacters() {
        return numOfFavoriteCharacters;
    }

    public void setNumOfFavoriteCharacters(Integer numOfFavoriteCharacters) {
        this.numOfFavoriteCharacters = numOfFavoriteCharacters;
    }

    public void incrementNumOfFavoriteCharacters() {
        this.numOfFavoriteCharacters++;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public JsonObject toJson() {
        return new Gson().fromJson(this.toString(),JsonObject.class);
    }

    @Override
    public int compareTo(Object filmToCompare) {
        return ((Film)filmToCompare).getNumOfFavoriteCharacters() - this.numOfFavoriteCharacters;
    }


}

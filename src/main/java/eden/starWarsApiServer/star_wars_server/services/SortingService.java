package eden.starWarsApiServer.star_wars_server.services;

import eden.starWarsApiServer.star_wars_server.objects.Character;
import eden.starWarsApiServer.star_wars_server.objects.Film;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SortingService {

    public ArrayList<Film> filmSorting(ArrayList<Character> characters, ArrayList<Film> notSortedFilmList) {
        Map<String,Film> mapFilms = new HashMap<>();
        for(Film film: notSortedFilmList) {
            mapFilms.put(film.getUrl(),film);
        }

        for (Character character : characters) {
            for (String filmUrl : character.getFilms()) {
                mapFilms.get(filmUrl).incrementNumOfFavoriteCharacters();
            }
        }

        ArrayList<Film> result = new ArrayList<>();
        for(String filmUrl : mapFilms.keySet()) {
            result.add(mapFilms.get(filmUrl));
        }
        Collections.sort(result);

        return result;
    }

}

package eden.starWarsApiServer.star_wars_server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import eden.starWarsApiServer.star_wars_server.objects.Character;
import eden.starWarsApiServer.star_wars_server.objects.Film;
import eden.starWarsApiServer.star_wars_server.services.DBService;
import eden.starWarsApiServer.star_wars_server.services.RestService;
import eden.starWarsApiServer.star_wars_server.services.SortingService;
import eden.starWarsApiServer.star_wars_server.services.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class StarWarsServerApplication {

	@Autowired
	ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(StarWarsServerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void start() {
		System.out.println("----------------------StarWarsServer START-----------------");
//		ArrayList<Character> characters = new ArrayList<>();
//		Character character = new Character();
//		ArrayList<String> films1 = new ArrayList<>();
//		films1.add("1");
//		films1.add("2");
//		films1.add("3");
//		character.setFilms(films1);
//		Character character2 = new Character();
//		ArrayList<String> films2 = new ArrayList<>();
//		films1.add("4");
//		films1.add("2");
//		films1.add("3");
//		character2.setFilms(films2);
//		characters.add(character);
//		characters.add(character2);
//		ArrayList<Film> films = new ArrayList<>();
//		Film film1 = new Film();
//		film1.setUrl("1");
//		Film film2 = new Film();
//		film2.setUrl("2");
//		Film film3 = new Film();
//		film3.setUrl("3");
//		Film film4 = new Film();
//		film4.setUrl("4");
//		films.add(film1);
//		films.add(film2);
//		films.add(film3);
//		films.add(film4);
//		SortingService sortingService = applicationContext.getBean(SortingService.class);
//		ArrayList<Film> filmArrayList = sortingService.filmSorting(characters,films);
//		DBService dbService = applicationContext.getBean(DBService.class);
//		dbService.saveSortingFilmList("-LtjQSNyVKyo8Sv1fL1Q",filmArrayList);
		System.out.println("----------------------StarWarsServer END-----------------");
	}

}

package eden.starWarsApiServer.star_wars_server;

import eden.starWarsApiServer.star_wars_server.services.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

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
		SwapiService swapiService = applicationContext.getBean(SwapiService.class);
		swapiService.getPeoples();
		System.out.println("----------------------StarWarsServer END-----------------");
	}

}

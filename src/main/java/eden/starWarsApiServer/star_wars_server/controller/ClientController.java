package eden.starWarsApiServer.star_wars_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClientController {

    @RequestMapping(method = RequestMethod.GET, path = "/health")
    public String test() {
        System.out.println("Star Wars Server works");
        return "Star Wars Server works";
    }
}

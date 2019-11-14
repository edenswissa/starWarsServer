package eden.starWarsApiServer.star_wars_server.services;

import com.google.gson.JsonObject;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestService {
    public static ResponseEntity<String> get(String url, @Nullable Map<String,Object> headers) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            ResponseEntity<String> responseEntity = null;
            if(headers != null) {
                for (String key : headers.keySet()) {
                    httpHeaders.add(key, String.valueOf(headers.get(key)));
                }
            }
            HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            return responseEntity;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println("get for url: " +url+ " FAILED cause: " +e.getMessage());
            return new ResponseEntity<String>(e.getStatusText(),e.getStatusCode());
        }
    }

    public static ResponseEntity<String> post(String url,@Nullable Map<String,Object> headers, @NonNull JsonObject body) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpHeaders.add(key, String.valueOf(headers.get(key)));
                }
            }
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<String>(body.toString(),httpHeaders);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            return responseEntity;
        } catch (HttpClientErrorException e) {
            System.out.println("post for url: " +url+ " FAILED cause: " +e.getMessage());
            return new ResponseEntity<String>(e.getStatusText(),e.getStatusCode());
        }
    }
}

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
        return rest(HttpMethod.GET,url,headers,null);
    }

    public static ResponseEntity<String> post(String url,@Nullable Map<String,Object> headers, @NonNull String body) {
        return rest(HttpMethod.POST,url,headers,body);
    }

    public static ResponseEntity<String> put(String url,@Nullable Map<String,Object> headers, @NonNull String body) {
        return rest(HttpMethod.PUT,url,headers,body);
    }

    public static ResponseEntity<String> patch(String url,@Nullable Map<String,Object> headers, @NonNull String body) {
        return rest(HttpMethod.PATCH,url,headers,body);
    }

    private static ResponseEntity<String> rest (HttpMethod httpMethod,String url,@Nullable Map<String,Object> headers, @Nullable String body) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpHeaders.add(key, String.valueOf(headers.get(key)));
                }
            }
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<String>(body,httpHeaders);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, String.class);
            return responseEntity;
        } catch (HttpClientErrorException e) {
            System.out.println(httpMethod.toString() + " for url: " +url+ " FAILED cause: " +e.getMessage());
            return new ResponseEntity<String>(e.getStatusText(),e.getStatusCode());
        }
    }
}

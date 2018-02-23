package syed.shahza.harmonia.restapi.client;

import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class RestClient {
    private final RestOperations restOperations;
    private final String baseUrl;

    public RestClient(String baseUrl) {
        this(baseUrl, new RestTemplate());
    }

    public RestClient(String baseUrl, RestOperations restOperations) {
        this.restOperations = restOperations;
        this.baseUrl = baseUrl;
    }

    public <R> R post(String url, Object object, Class<R> responseType) {
        return this.restOperations.postForObject(this.baseUrl + url, object, responseType);
    }
}
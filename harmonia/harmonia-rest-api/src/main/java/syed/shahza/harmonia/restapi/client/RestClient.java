package syed.shahza.harmonia.restapi.client;

import java.util.Map;

import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

// class that encapsulates RESTful requests to backend
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
    
    public <R> R get(String url, Class<R> responseType, Object object) {
    	return this.restOperations.getForObject(this.baseUrl + url, responseType, object);
    }
    
    public void delete(String url, Map<String, Object> variables) {
    	this.restOperations.delete(this.baseUrl + url, variables);
    }
    
    public void put(String url, Object object) {
    	this.restOperations.put(this.baseUrl + url, object);
    }
}

package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.restapi.client.RestClient;
import syed.shahza.harmonia.backend.dto.LecturerDto;

//class encapsulating methods for lecturer login through the backend RESTful endpoint
public class LoginAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecturer/login";

    public LoginAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public Boolean login(LecturerDto lecturerDto) {
        return this.restClient.post(URL_ENDPOINT, lecturerDto, Boolean.class);
    }
}

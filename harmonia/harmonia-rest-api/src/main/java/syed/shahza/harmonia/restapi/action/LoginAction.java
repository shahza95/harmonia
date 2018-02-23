package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.restapi.client.RestClient;
import syed.shahza.harmonia.backend.dto.LecturerDto;

public class LoginAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/login";

    public LoginAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public void login(LecturerDto lecturerDto) {
        this.restClient.post(URL_ENDPOINT, lecturerDto, Boolean.class);
    }
}

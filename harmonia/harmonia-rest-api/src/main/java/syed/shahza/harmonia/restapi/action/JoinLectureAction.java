package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.restapi.client.RestClient;
import syed.shahza.harmonia.backend.dto.LectureDto;

public class JoinLectureAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecture/join";

    public JoinLectureAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public LectureDto join(String password) {
        return this.restClient.post(URL_ENDPOINT, password, LectureDto.class);
    }
}

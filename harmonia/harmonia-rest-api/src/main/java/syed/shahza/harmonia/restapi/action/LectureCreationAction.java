package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.restapi.client.RestClient;
import syed.shahza.harmonia.backend.dto.LectureDto;

//class encapsulating methods for creating lecture through the backend RESTful endpoint
public class LectureCreationAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecturer/lecture/create";

    public LectureCreationAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public LectureDto create(LectureDto lectureDto) {
        return this.restClient.post(URL_ENDPOINT, lectureDto, LectureDto.class);
    }
}

package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.client.RestClient;

//class encapsulating methods for retrieving a lecture through the backend RESTful endpoint
public class GetLectureAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecture/{lectureTitle}";

    public GetLectureAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public LectureDto get(String lectureTitle) {
        return this.restClient.get(URL_ENDPOINT, LectureDto.class, lectureTitle);
    }
}

package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.client.RestClient;

public class GetQuestionAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecture/{lectureTitle}";

    public GetQuestionAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public LectureDto get(String lectureTitle) {
        return this.restClient.get(URL_ENDPOINT, LectureDto.class, lectureTitle);
    }
}

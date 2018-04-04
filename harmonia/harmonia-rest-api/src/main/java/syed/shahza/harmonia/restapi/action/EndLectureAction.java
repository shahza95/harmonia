package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.client.RestClient;

public class EndLectureAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecturer/lecture/active";

    public EndLectureAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public void endLecture(LectureDto lectureDto) {
    	lectureDto.setEnded(true);
        this.restClient.put(URL_ENDPOINT, lectureDto);
    }
}

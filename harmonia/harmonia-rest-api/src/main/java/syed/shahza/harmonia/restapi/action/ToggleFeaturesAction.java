package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.client.RestClient;

public class ToggleFeaturesAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecturer/lecture/active";

    public ToggleFeaturesAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public void disableCommenting(LectureDto lectureDto) {
    	lectureDto.setCommentsEnabled(false);
        this.restClient.put(URL_ENDPOINT, lectureDto);
    }
    
    public void enableCommenting(LectureDto lectureDto) {
    	lectureDto.setCommentsEnabled(true);
    	this.restClient.put(URL_ENDPOINT, lectureDto);
    }
    
    public void disableMood(LectureDto lectureDto) {
    	lectureDto.setMoodEnabled(false);
    	this.restClient.put(URL_ENDPOINT, lectureDto);
    }
    
    public void enableMood(LectureDto lectureDto) {
    	lectureDto.setMoodEnabled(true);
    	this.restClient.put(URL_ENDPOINT, lectureDto);
    }
}

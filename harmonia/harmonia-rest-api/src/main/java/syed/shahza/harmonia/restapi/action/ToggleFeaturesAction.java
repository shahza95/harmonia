package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.client.RestClient;

//class encapsulating methods for toggling all lecture features (commenting, mood sending, questions, end of lecture feedback) through the backend RESTful endpoint
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
    
    public void disableFeedback(LectureDto lectureDto) {
    	lectureDto.setFeedbackEnabled(false);
    	this.restClient.put(URL_ENDPOINT, lectureDto);
    }
    
    public void enableFeedback(LectureDto lectureDto) {
    	lectureDto.setFeedbackEnabled(true);
    	this.restClient.put(URL_ENDPOINT, lectureDto);
    }
    
    public void disableQuestions(LectureDto lectureDto) {
    	lectureDto.setQuestionsEnabled(false);
    	this.restClient.put(URL_ENDPOINT, lectureDto);
    }
    
    public void enableQuestions(LectureDto lectureDto) {
    	lectureDto.setQuestionsEnabled(true);
    	this.restClient.put(URL_ENDPOINT, lectureDto);
    }
}

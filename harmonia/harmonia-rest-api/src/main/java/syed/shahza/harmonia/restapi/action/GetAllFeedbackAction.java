package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.FeedbackDtoList;
import syed.shahza.harmonia.restapi.client.RestClient;

//class encapsulating methods for retrieving all end of lecture feedbacks through the backend RESTful endpoint
public class GetAllFeedbackAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecturer/lecture/active/{lectureTitle}/feedback";

    public GetAllFeedbackAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public FeedbackDtoList getAll(String lectureTitle) {
        return this.restClient.get(URL_ENDPOINT, FeedbackDtoList.class, lectureTitle);
    }
}

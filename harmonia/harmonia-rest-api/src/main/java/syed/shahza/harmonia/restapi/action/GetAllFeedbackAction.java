package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.FeedbackDtoList;
import syed.shahza.harmonia.restapi.client.RestClient;

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

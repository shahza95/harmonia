package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.restapi.client.RestClient;

public class AddFeedbackAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/student/lecture/active/feedback/add";

    public AddFeedbackAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public FeedbackDto addFeedback(FeedbackDto feedbackDto) {
        return this.restClient.post(URL_ENDPOINT, feedbackDto, FeedbackDto.class);
    }
}

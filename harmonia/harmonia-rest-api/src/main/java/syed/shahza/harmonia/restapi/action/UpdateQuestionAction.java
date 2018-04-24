package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.restapi.client.RestClient;

// class encapsulating methods for updating question through the backend RESTful endpoint
public class UpdateQuestionAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecturer/lecture/active/question";

    public UpdateQuestionAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public void update(QuestionDto questionDto) {
        this.restClient.put(URL_ENDPOINT, questionDto);
    }
}

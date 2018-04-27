package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.restapi.client.RestClient;

//class encapsulating methods for updating a question through the backend RESTful endpoint
public class GetQuestionAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecture/active/questions/{questionId}";

    public GetQuestionAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public QuestionDto get(String questionId) {
        return this.restClient.get(URL_ENDPOINT, QuestionDto.class, questionId);
    }
}

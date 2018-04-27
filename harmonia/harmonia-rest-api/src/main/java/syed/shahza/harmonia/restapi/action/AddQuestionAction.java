package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.restapi.client.RestClient;

//class encapsulating methods for adding a question through the backend RESTful endpoint
public class AddQuestionAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/student/lecture/active/question/add";

    public AddQuestionAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public QuestionDto addQuestion(QuestionDto questionDto) {
        return this.restClient.post(URL_ENDPOINT, questionDto, QuestionDto.class);
    }
}

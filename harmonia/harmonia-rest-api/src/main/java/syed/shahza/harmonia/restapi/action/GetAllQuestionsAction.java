package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.QuestionDtoList;
import syed.shahza.harmonia.restapi.client.RestClient;

//class encapsulating methods for getting all questions through the backend RESTful endpoint
public class GetAllQuestionsAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecture/active/{lectureTitle}/questions";

    public GetAllQuestionsAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public QuestionDtoList getAll(String lectureTitle) {
        return this.restClient.get(URL_ENDPOINT, QuestionDtoList.class, lectureTitle);
    }
}

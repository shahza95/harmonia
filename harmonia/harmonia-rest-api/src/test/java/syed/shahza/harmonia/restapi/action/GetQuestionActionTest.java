package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class GetQuestionActionTest {
    private GetQuestionAction getQuestionAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.getQuestionAction = new GetQuestionAction(this.mockRestClient);
    }

    @Test
    public void getWillInvokeMockRestClientGetMethodWithCorrectParameters() {
    	String questionId = "id";
        this.getQuestionAction.get(questionId);

        verify(this.mockRestClient).get("/lecture/active/questions/{questionId}", QuestionDto.class, questionId);
    }
}

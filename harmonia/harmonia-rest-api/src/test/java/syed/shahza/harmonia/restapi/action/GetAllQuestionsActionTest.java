package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.QuestionDtoList;
import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class GetAllQuestionsActionTest {
    private GetAllQuestionsAction getAllQuestionsAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.getAllQuestionsAction = new GetAllQuestionsAction(this.mockRestClient);
    }

    @Test
    public void getAllWillInvokeMockRestClientGetMethodWithCorrectParameters() {
    	String lectureTitle = "title";
        this.getAllQuestionsAction.getAll(lectureTitle);

        verify(this.mockRestClient).get("/lecture/active/{lectureTitle}/questions", QuestionDtoList.class, lectureTitle);
    }
}

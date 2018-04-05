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
public class UpdateQuestionActionTest {
    private UpdateQuestionAction updateQuestionAction;
    private QuestionDto questionDto;

    @Mock
    private RestClient mockRestClient;
    
    @Before
    public void before() {
    	this.questionDto = new QuestionDto();
        this.updateQuestionAction = new UpdateQuestionAction(this.mockRestClient);
    }

    @Test
    public void updateQuestionShouldInvokeMockRestClientPutMethodWithCorrectParameters() {
        this.updateQuestionAction.update(this.questionDto);

        verify(this.mockRestClient).put("/lecturer/lecture/active/question", this.questionDto);
    }
}

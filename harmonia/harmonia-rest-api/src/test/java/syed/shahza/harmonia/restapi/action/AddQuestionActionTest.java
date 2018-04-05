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
public class AddQuestionActionTest {
    private AddQuestionAction addQuestionAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.addQuestionAction = new AddQuestionAction(this.mockRestClient);
    }

    @Test
    public void shouldInvokeMockRestClientPostMethodWithCorrectParameters() {
    	QuestionDto questionDto = new QuestionDto();
        this.addQuestionAction.addQuestion(questionDto);

        verify(this.mockRestClient).post("/student/lecture/active/question/add", questionDto, QuestionDto.class);
    }
}

package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class AddFeedbackActionTest {
    private AddFeedbackAction addFeedbackAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.addFeedbackAction = new AddFeedbackAction(this.mockRestClient);
    }

    @Test
    public void shouldInvokeMockRestClientPostMethodWithCorrectParameters() {
    	FeedbackDto feedbackDto = new FeedbackDto();
        this.addFeedbackAction.addFeedback(feedbackDto);

        verify(this.mockRestClient).post("/student/lecture/active/feedback/add", feedbackDto, FeedbackDto.class);
    }
}

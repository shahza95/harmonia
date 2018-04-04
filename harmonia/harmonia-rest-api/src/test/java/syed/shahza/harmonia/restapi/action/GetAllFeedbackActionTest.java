package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.FeedbackDtoList;
import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class GetAllFeedbackActionTest {
    private GetAllFeedbackAction getAllFeedbackAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.getAllFeedbackAction = new GetAllFeedbackAction(this.mockRestClient);
    }

    @Test
    public void getAllWillInvokeMockRestClientGetMethodWithCorrectParameters() {
    	String lectureTitle = "title";
        this.getAllFeedbackAction.getAll(lectureTitle);

        verify(this.mockRestClient).get("/lecturer/lecture/active/{lectureTitle}/feedback", FeedbackDtoList.class, lectureTitle);
    }
}

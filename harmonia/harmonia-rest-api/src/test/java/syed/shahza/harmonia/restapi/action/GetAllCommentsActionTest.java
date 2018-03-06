package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class GetAllCommentsActionTest {
    private GetAllCommentsAction getAllCommentsAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.getAllCommentsAction = new GetAllCommentsAction(this.mockRestClient);
    }

    @Test
    public void getAllWillInvokeMockRestClientGetMethodWithCorrectParameters() {
    	String lectureTitle = "title";
        this.getAllCommentsAction.getAll(lectureTitle);

        verify(this.mockRestClient).get("/lecture/active/comments/{lectureTitle}", CommentDtoList.class, lectureTitle);
    }
}

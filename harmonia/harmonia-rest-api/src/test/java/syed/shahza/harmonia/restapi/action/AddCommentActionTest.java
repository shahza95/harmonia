package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class AddCommentActionTest {
    private AddCommentAction addCommentAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.addCommentAction = new AddCommentAction(this.mockRestClient);
    }

    @Test
    public void shouldInvokeMockRestClientPostMethodWithCorrectParameters() {
    	CommentDto commentDto = new CommentDto();
        this.addCommentAction.addComment(commentDto);

        verify(this.mockRestClient).post("/student/lecture/active/comments/add", commentDto, CommentDto.class);
    }
}

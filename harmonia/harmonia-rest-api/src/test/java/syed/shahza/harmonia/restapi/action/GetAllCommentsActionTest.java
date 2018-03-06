package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
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
    	LectureDto lectureDto = TestLectureDto.anActiveLectureDto().build();
        this.getAllCommentsAction.getAll(lectureDto);

        verify(this.mockRestClient).get("/student/lecture/active/comments", CommentDtoList.class, lectureDto);
    }
}

package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class GetLectureActionTest {
    private GetLectureAction getLectureAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.getLectureAction = new GetLectureAction(this.mockRestClient);
    }

    @Test
    public void getWillInvokeMockRestClientGetMethodWithCorrectParameters() {
    	String lectureTitle = "title";
        this.getLectureAction.get(lectureTitle);

        verify(this.mockRestClient).get("/lecture/{lectureTitle}", LectureDto.class, lectureTitle);
    }
}

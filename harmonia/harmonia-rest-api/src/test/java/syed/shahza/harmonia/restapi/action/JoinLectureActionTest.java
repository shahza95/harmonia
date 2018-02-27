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
public class JoinLectureActionTest {
    private JoinLectureAction joinLectureAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.joinLectureAction = new JoinLectureAction(this.mockRestClient);
    }

    @Test
    public void lectureCreateWillInvokeMockRestClientGetMethodWithCorrectParameters() {
    	String password = "somePassword";
        this.joinLectureAction.join(password);

        verify(this.mockRestClient).post("/lecture/join", password, LectureDto.class);
    }
}

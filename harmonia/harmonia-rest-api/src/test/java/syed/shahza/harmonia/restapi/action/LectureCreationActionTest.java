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
public class LectureCreationActionTest {
    private LectureCreationAction lectureCreationAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.lectureCreationAction = new LectureCreationAction(this.mockRestClient);
    }

    @Test
    public void lectureCreateWillInvokeMockRestClientGetMethodWithCorrectParameters() {
    	LectureDto lectureDto = new LectureDto();
        this.lectureCreationAction.create(lectureDto);

        verify(this.mockRestClient).post("/lecturer/lecture/create", lectureDto, LectureDto.class);
    }
}

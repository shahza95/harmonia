package syed.shahza.harmonia.restapi.action;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.eq;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class EndLectureActionTest {
    private EndLectureAction endLectureAction;
    private LectureDto lectureDto;

    @Mock
    private RestClient mockRestClient;
    
    @Captor
    private ArgumentCaptor<LectureDto> lectureDtoCaptor;

    @Before
    public void before() {
    	this.lectureDto = new LectureDto();
        this.endLectureAction = new EndLectureAction(this.mockRestClient);
    }

    @Test
    public void disableCommentingShouldInvokeMockRestClientPutMethodWithCorrectParameters() {
        this.endLectureAction.endLecture(this.lectureDto);

        verify(this.mockRestClient).put("/lecturer/lecture/active", this.lectureDto);
    }
    
    @Test
    public void disableCommentingEditsLectureDtoToFalseCommentsEnabled() {
        this.endLectureAction.endLecture(this.lectureDto);

        this.lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class); 
        verify(this.mockRestClient).put(eq("/lecturer/lecture/active"), this.lectureDtoCaptor.capture());
    
        assertThat(this.lectureDtoCaptor.getValue().getEnded(), is(true));
    }
}

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
public class ToggleFeaturesActionTest {
    private ToggleFeaturesAction toggleFeaturesAction;

    @Mock
    private RestClient mockRestClient;
    
    @Captor
    private ArgumentCaptor<LectureDto> lectureDtoCaptor;

    @Before
    public void before() {
        this.toggleFeaturesAction = new ToggleFeaturesAction(this.mockRestClient);
    }

    @Test
    public void disableCommentingShouldInvokeMockRestClientPutMethodWithCorrectParameters() {
    	LectureDto lectureDto = new LectureDto();
        this.toggleFeaturesAction.disableCommenting(lectureDto);

        verify(this.mockRestClient).put("/lecturer/lecture/active/comments", lectureDto);
    }
    
    @Test
    public void enableCommentingShouldInvokeMockRestClientPutMethodWithCorrectParameters() {
    	LectureDto lectureDto = new LectureDto();
    	this.toggleFeaturesAction.enableCommenting(lectureDto);
    	
    	verify(this.mockRestClient).put("/lecturer/lecture/active/comments", lectureDto);
    }
    
    @Test
    public void disableCommentingEditsLectureDtoToFalseEnabled() {
    	LectureDto lectureDto = new LectureDto();
        this.toggleFeaturesAction.disableCommenting(lectureDto);

        this.lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class); 
        verify(this.mockRestClient).put(eq("/lecturer/lecture/active/comments"), this.lectureDtoCaptor.capture());
    
        assertThat(this.lectureDtoCaptor.getValue().getEnabled(), is(false));
    }
    
    @Test
    public void enableCommentingEditsLectureDtoToTrueEnabled() {
    	LectureDto lectureDto = new LectureDto();
    	this.toggleFeaturesAction.enableCommenting(lectureDto);
    	
    	this.lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class); 
    	verify(this.mockRestClient).put(eq("/lecturer/lecture/active/comments"), this.lectureDtoCaptor.capture());
    	
    	assertThat(this.lectureDtoCaptor.getValue().getEnabled(), is(true));
    }
}

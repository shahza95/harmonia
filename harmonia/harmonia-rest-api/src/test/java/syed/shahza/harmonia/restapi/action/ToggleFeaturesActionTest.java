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
    private LectureDto lectureDto;

    @Mock
    private RestClient mockRestClient;
    
    @Captor
    private ArgumentCaptor<LectureDto> lectureDtoCaptor;

    @Before
    public void before() {
    	this.lectureDto = new LectureDto();
        this.toggleFeaturesAction = new ToggleFeaturesAction(this.mockRestClient);
    }

    @Test
    public void disableCommentingShouldInvokeMockRestClientPutMethodWithCorrectParameters() {
        this.toggleFeaturesAction.disableCommenting(this.lectureDto);

        verify(this.mockRestClient).put("/lecturer/lecture/active", this.lectureDto);
    }
    
    @Test
    public void enableCommentingShouldInvokeMockRestClientPutMethodWithCorrectParameters() {
    	this.toggleFeaturesAction.enableCommenting(this.lectureDto);
    	
    	verify(this.mockRestClient).put("/lecturer/lecture/active", this.lectureDto);
    }
    
    @Test
    public void disableCommentingEditsLectureDtoToFalseCommentsEnabled() {
        this.toggleFeaturesAction.disableCommenting(this.lectureDto);

        this.lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class); 
        verify(this.mockRestClient).put(eq("/lecturer/lecture/active"), this.lectureDtoCaptor.capture());
    
        assertThat(this.lectureDtoCaptor.getValue().getCommentsEnabled(), is(false));
    }
    
    @Test
    public void enableCommentingEditsLectureDtoToTrueCommentsEnabled() {
    	this.toggleFeaturesAction.enableCommenting(this.lectureDto);
    	
    	this.lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class); 
    	verify(this.mockRestClient).put(eq("/lecturer/lecture/active"), this.lectureDtoCaptor.capture());
    	
    	assertThat(this.lectureDtoCaptor.getValue().getCommentsEnabled(), is(true));
    }
    
    @Test
    public void disableMoodShouldInvokeMockRestClientPutMethodWithCorrectParameters() {
    	this.toggleFeaturesAction.disableMood(this.lectureDto);
    	
    	verify(this.mockRestClient).put("/lecturer/lecture/active", this.lectureDto);
    }
    
    @Test
    public void enableMoodShouldInvokeMockRestClientPutMethodWithCorrectParameters() {
    	this.toggleFeaturesAction.enableMood(this.lectureDto);
    	
    	verify(this.mockRestClient).put("/lecturer/lecture/active", this.lectureDto);
    }
    
    @Test
    public void disableMoodEditsLectureDtoToFalseMoodEnabled() {
    	this.toggleFeaturesAction.disableMood(this.lectureDto);
    	
    	this.lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class); 
    	verify(this.mockRestClient).put(eq("/lecturer/lecture/active"), this.lectureDtoCaptor.capture());
    	
    	assertThat(this.lectureDtoCaptor.getValue().getMoodEnabled(), is(false));
    }
    
    @Test
    public void enableMoodEditsLectureDtoToTrueMoodEnabled() {
    	this.toggleFeaturesAction.enableMood(this.lectureDto);
    	
    	this.lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class); 
    	verify(this.mockRestClient).put(eq("/lecturer/lecture/active"), this.lectureDtoCaptor.capture());
    	
    	assertThat(this.lectureDtoCaptor.getValue().getMoodEnabled(), is(true));
    }
    
    @Test
    public void disableFeedbackShouldInvokeMockRestClientPutMethodWithCorrectParameters() {
    	this.toggleFeaturesAction.disableFeedback(this.lectureDto);
    	
    	verify(this.mockRestClient).put("/lecturer/lecture/active", this.lectureDto);
    }
    
    @Test
    public void enableFeedbackShouldInvokeMockRestClientPutMethodWithCorrectParameters() {
    	this.toggleFeaturesAction.enableFeedback(this.lectureDto);
    	
    	verify(this.mockRestClient).put("/lecturer/lecture/active", this.lectureDto);
    }
    
    @Test
    public void disableFeedbackEditsLectureDtoToFalseMoodEnabled() {
    	this.toggleFeaturesAction.disableFeedback(this.lectureDto);
    	
    	this.lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class); 
    	verify(this.mockRestClient).put(eq("/lecturer/lecture/active"), this.lectureDtoCaptor.capture());
    	
    	assertThat(this.lectureDtoCaptor.getValue().getFeedbackEnabled(), is(false));
    }
    
    @Test
    public void enableFeedbackEditsLectureDtoToTrueMoodEnabled() {
    	this.toggleFeaturesAction.enableFeedback(this.lectureDto);
    	
    	this.lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class); 
    	verify(this.mockRestClient).put(eq("/lecturer/lecture/active"), this.lectureDtoCaptor.capture());
    	
    	assertThat(this.lectureDtoCaptor.getValue().getFeedbackEnabled(), is(true));
    }
}

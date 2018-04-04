package syed.shahza.harmonia.backend.endpoint.adapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestFeedback.aValidFeedback;
import static syed.shahza.harmonia.backend.dto.TestFeedbackDto.aValidFeedbackDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.dto.FeedbackDto;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackAdapterTest {
    private FeedbackAdapter feedbackAdapter;
    
    @Mock
    private LectureAdapter mockLectureAdapter;

    @Before
    public void before() {
        this.feedbackAdapter = new FeedbackAdapter(this.mockLectureAdapter);
    }
    
    @Test
    public void toDtoInvokesLectureAdapterToDto() {
    	Feedback feedback = aValidFeedback().build();
    	this.feedbackAdapter.toDto(feedback);
    	Mockito.verify(this.mockLectureAdapter).toDto(feedback.getLecture());
    }
    
    @Test
    public void toDomainInvokesLectureAdapterToDomain() {
    	FeedbackDto feedbackDto = aValidFeedbackDto().build();
    	this.feedbackAdapter.toDomain(feedbackDto);
    	Mockito.verify(this.mockLectureAdapter).toDomain(feedbackDto.getLectureDto());
    }
    
    @Test
    public void canAdaptFeedbackRatingToDto() {
    	assertThat(this.feedbackAdapter.toDto(aValidFeedback().rating(3).build()).getRating(), is(3));
    }
    
    @Test
    public void canAdaptFeedbackRatingToDomain() {
    	assertThat(this.feedbackAdapter.toDomain(aValidFeedbackDto().rating(3).build()).getRating(), is(3));
    }

    @Test
    public void canAdaptFeedbackMessageToDto() {
        assertThat(this.feedbackAdapter.toDto(aValidFeedback().message("someMessage").build()).getMessage(), is("someMessage"));
    }

    @Test
    public void canAdaptFeedbackMessageToDomain() {
        assertThat(this.feedbackAdapter.toDomain(aValidFeedbackDto().message("someMessage").build()).getMessage(), is("someMessage"));
    }
}
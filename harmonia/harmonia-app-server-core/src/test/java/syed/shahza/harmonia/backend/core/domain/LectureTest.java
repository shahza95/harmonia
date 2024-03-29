package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestLecture.aValidLecture;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class LectureTest {
    @Test
    public void canGetAndSetTitle() {
        String randomTitleString = "anotherTitle";
        assertThat(aValidLecture().title(randomTitleString).build().getTitle(), is(randomTitleString));
    }

    @Test
    public void canGetAndSetPassword() {
        String randomPasswordString = "anotherPassword";
        assertThat(aValidLecture().password(randomPasswordString).build().getPassword(), is(randomPasswordString));
    }
    
    @Test
    public void canGetAndSetDate() {
    	LocalDate randomDate = new LocalDate(1970,1,1);
    	assertThat(aValidLecture().date(randomDate).build().getDate(), is(randomDate));
    }
    
    @Test
    public void canGetAndSetStartTime() {
    	LocalTime randomStartTime = new LocalTime(12, 30);
    	assertThat(aValidLecture().startTime(randomStartTime).build().getStartTime(), is(randomStartTime));
    }
    
    @Test
    public void canGetAndSetEndTime() {
    	LocalTime randomEndTime = new LocalTime(13,30);
    	assertThat(aValidLecture().endTime(randomEndTime).build().getEndTime(), is(randomEndTime));
    }
    
    @Test
    public void canGetAndSetEnded() {
    	boolean ended = true;
    	assertThat(aValidLecture().ended(ended).build().getEnded(), is(ended));
    }
    
    @Test
    public void canGetAndSetCommentsEnabled() {
    	boolean commentsEnabled = false;
    	assertThat(aValidLecture().commentsEnabled(commentsEnabled).build().getCommentsEnabled(), is(commentsEnabled));
    }
    
    @Test
    public void canGetAndSetMoodEnabled() {
    	boolean moodEnabled = false;
    	assertThat(aValidLecture().moodEnabled(moodEnabled).build().getMoodEnabled(), is(moodEnabled));
    }
    
    @Test
    public void canGetAndSetFeedbackEnabled() {
    	boolean feedbackEnabled = false;
    	assertThat(aValidLecture().feedbackEnabled(feedbackEnabled).build().getFeedbackEnabled(), is(feedbackEnabled));
    }
    
    @Test
    public void canGetAndSetQuestionsEnabled() {
    	boolean questionsEnabled = false;
    	assertThat(aValidLecture().feedbackEnabled(questionsEnabled).build().getFeedbackEnabled(), is(questionsEnabled));
    }
}

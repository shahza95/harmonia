package syed.shahza.harmonia.backend.endpoint.adapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestLecture.aValidLecture;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

public class LectureAdapterTest {
    private LectureAdapter lectureAdapter;

    @Before
    public void before() {
        this.lectureAdapter = new LectureAdapter();
    }

    @Test
    public void canAdaptUsernameToDto() {
        assertThat(this.lectureAdapter.toDto(aValidLecture().title("someTitle").build()).getTitle(), is("someTitle"));
    }

    @Test
    public void canAdaptPasswordToDto() {
        assertThat(this.lectureAdapter.toDto(aValidLecture().password("somePassword").build()).getPassword(), is("somePassword"));
    }
    
    @Test
    public void canAdaptDateToDto() {
    	LocalDate localDate = new LocalDate();
    	assertThat(this.lectureAdapter.toDto(aValidLecture().date(localDate).build()).getDate(), is(localDate));
    }
    
    @Test
    public void canAdaptStartTimeToDto() {
    	LocalTime localTime = new LocalTime();
    	assertThat(this.lectureAdapter.toDto(aValidLecture().startTime(localTime).build()).getStartTime(), is(localTime));
    }
    
    @Test
    public void canAdaptEndTimeToDto() {
    	LocalTime localTime = new LocalTime();
    	assertThat(this.lectureAdapter.toDto(aValidLecture().endTime(localTime).build()).getEndTime(), is(localTime));
    }
    
    @Test
    public void canAdaptEndedToDto() {
    	boolean ended = true;
    	assertThat(this.lectureAdapter.toDto(aValidLecture().ended(ended).build()).getEnded(), is(ended));
    }
    
    @Test
    public void canAdaptCommentsEnabledToDto() {
    	boolean enabled = true;
    	assertThat(this.lectureAdapter.toDto(aValidLecture().commentsEnabled(enabled).build()).getCommentsEnabled(), is(enabled));
    }
    
    @Test
    public void canAdaptMoodEnabledToDto() {
    	boolean enabled = true;
    	assertThat(this.lectureAdapter.toDto(aValidLecture().moodEnabled(enabled).build()).getMoodEnabled(), is(enabled));
    }
    
    @Test
    public void canAdaptFeedbackEnabledToDto() {
    	boolean enabled = true;
    	assertThat(this.lectureAdapter.toDto(aValidLecture().feedbackEnabled(enabled).build()).getFeedbackEnabled(), is(enabled));
    }

    @Test
    public void canAdaptUsernameToDomain() {
        assertThat(this.lectureAdapter.toDomain(aValidLectureDto().title("someTitle").build()).getTitle(), is("someTitle"));
    }

    @Test
    public void canAdaptPasswordToDomain() {
        assertThat(this.lectureAdapter.toDomain(aValidLectureDto().password("somePassword").build()).getPassword(), is("somePassword"));
    }
    
    @Test
    public void canAdaptDateToDomain() {
    	LocalDate localDate = new LocalDate();
    	assertThat(this.lectureAdapter.toDomain(aValidLectureDto().date(localDate).build()).getDate(), is(localDate));
    }
    
    @Test
    public void canAdaptStartTimeToDomain() {
    	LocalTime localTime = new LocalTime();
    	assertThat(this.lectureAdapter.toDomain(aValidLectureDto().startTime(localTime).build()).getStartTime(), is(localTime));
    }
    
    @Test
    public void canAdaptEndTimeToDomain() {
    	LocalTime localTime = new LocalTime();
    	assertThat(this.lectureAdapter.toDomain(aValidLectureDto().endTime(localTime).build()).getEndTime(), is(localTime));
    }
    
    @Test
    public void canAdaptEndedToDomain() {
    	boolean ended = false;
    	assertThat(this.lectureAdapter.toDomain(aValidLectureDto().ended(ended).build()).getEnded(), is(ended));
    }
    
    @Test
    public void canAdaptCommentsEnabledToDomain() {
    	boolean enabled = false;
    	assertThat(this.lectureAdapter.toDomain(aValidLectureDto().commentsEnabled(enabled).build()).getCommentsEnabled(), is(enabled));
    }
    
    @Test
    public void canAdaptMoodEnabledToDomain() {
    	boolean enabled = false;
    	assertThat(this.lectureAdapter.toDomain(aValidLectureDto().moodEnabled(enabled).build()).getMoodEnabled(), is(enabled));
    }
    
    @Test
    public void canAdaptFeedbackEnabledToDomain() {
    	boolean enabled = false;
    	assertThat(this.lectureAdapter.toDomain(aValidLectureDto().feedbackEnabled(enabled).build()).getFeedbackEnabled(), is(enabled));
    }
}
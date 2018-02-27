package syed.shahza.harmonia.backend.endpoint.adapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestLectures.aValidLecture;
import static syed.shahza.harmonia.backend.dto.TestLectureDtos.aValidLectureDto;

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
}
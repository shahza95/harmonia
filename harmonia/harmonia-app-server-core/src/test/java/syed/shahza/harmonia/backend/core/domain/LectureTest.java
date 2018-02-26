package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestLectures.aValidLecture;

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
}

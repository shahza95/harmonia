package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anEmptyLectureDto;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import syed.shahza.harmonia.backend.dto.LectureDto;

public class LectureDtoTest {
    @Test
    public void canRetrieveCorrectTitleOnceSet() {
    	LectureDto lectureDto = aValidLectureDto().build();
        String randomTitleString = "anotherTitle";
        lectureDto.setTitle(randomTitleString);

        assertThat(lectureDto.getTitle(), is(randomTitleString));
    }

    @Test
    public void canRetrievePasswordOnceSet() {
    	LectureDto lectureDto = aValidLectureDto().build();
        String randomPasswordString = "anotherPassword";
        lectureDto.setPassword(randomPasswordString);

        assertThat(lectureDto.getPassword(), is(randomPasswordString));
    }
    
    @Test
    public void canRetrieveDateOnceSet() {
    	LectureDto lectureDto = aValidLectureDto().build();
        LocalDate randomDate = new LocalDate(1970, 1, 1);
        lectureDto.setDate(randomDate);

        assertThat(lectureDto.getDate(), is(randomDate));
    }
    
    @Test
    public void canRetrieveStartTimeOnceSet() {
    	LectureDto lectureDto = aValidLectureDto().build();
        LocalTime randomStartTime = new LocalTime(15, 00);
        lectureDto.setStartTime(randomStartTime);

        assertThat(lectureDto.getStartTime(), is(randomStartTime));
    }
    
    @Test
    public void canRetrieveEndTimeOnceSet() {
    	LectureDto lectureDto = aValidLectureDto().build();
        LocalTime randomEndTime = new LocalTime(16, 00);
        lectureDto.setEndTime(randomEndTime);

        assertThat(lectureDto.getEndTime(), is(randomEndTime));
    }
    
    @Test
    public void isEmptyShouldReturnFalseIfNoFieldIsNull() {
    	LectureDto lectureDto = aValidLectureDto().build();
    	assertThat(lectureDto.isEmpty(), is(false));
    }
    
    @Test
    public void isEmptyShouldReturnFalseIfAtleastOneFieldIsNotNull() {
    	LectureDto lectureDto = anEmptyLectureDto().title("title").build();
    	assertThat(lectureDto.isEmpty(), is(false));
    }
    
    @Test
    public void isEmptyShouldReturnTrueIfAllFieldsAreNull() {
    	LectureDto lectureDto = anEmptyLectureDto().build();
    	assertThat(lectureDto.isEmpty(), is(true));
    }
}
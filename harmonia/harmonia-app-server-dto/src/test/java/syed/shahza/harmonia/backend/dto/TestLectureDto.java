package syed.shahza.harmonia.backend.dto;

import static syed.shahza.harmonia.backend.dto.LectureDto.aLectureDto;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import syed.shahza.harmonia.backend.dto.LectureDto.Builder;

public class TestLectureDto {
    public static Builder aValidLectureDto() {
        return aLectureDto().title("title").password("password").date(new LocalDate()).startTime(new LocalTime()).endTime(new LocalTime());
    }
    
    public static Builder anEmptyLectureDto() {
        return aLectureDto();
    }
    
    public static Builder anActiveLectureDto() {
    	return aValidLectureDto().date(new LocalDate()).startTime(new LocalTime()).endTime(new LocalTime().plusHours(1));
    }
}

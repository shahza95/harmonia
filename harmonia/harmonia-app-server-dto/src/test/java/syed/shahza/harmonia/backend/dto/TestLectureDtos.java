package syed.shahza.harmonia.backend.dto;

import static syed.shahza.harmonia.backend.dto.LectureDto.aLectureDto;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import syed.shahza.harmonia.backend.dto.LectureDto.Builder;

public class TestLectureDtos {
    public static Builder aValidLectureDto() {
        return aLectureDto().title("title").password("password").date(new LocalDate()).startTime(new LocalTime()).endTime(new LocalTime().plusHours(1));
    }
    
    public static Builder anEmptyLectureDto() {
        return aLectureDto();
    }
}
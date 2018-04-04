package syed.shahza.harmonia.backend.core.domain;

import static syed.shahza.harmonia.backend.core.domain.Lecture.aLecture;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import syed.shahza.harmonia.backend.core.domain.Lecture.Builder;

public class TestLecture {
    public static Builder aValidLecture() {
        return aLecture().title("title").password("password").date(new LocalDate()).startTime(new LocalTime()).endTime(new LocalTime()).commentsEnabled(true).moodEnabled(true).ended(false);
    }
    
    public static Builder anEmptyLecture() {
    	return aLecture();
    }
}
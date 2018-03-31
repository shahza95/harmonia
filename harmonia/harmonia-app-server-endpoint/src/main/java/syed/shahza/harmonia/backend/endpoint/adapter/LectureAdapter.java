package syed.shahza.harmonia.backend.endpoint.adapter;

import static syed.shahza.harmonia.backend.core.domain.Lecture.aLecture;
import static syed.shahza.harmonia.backend.dto.LectureDto.aLectureDto;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.dto.LectureDto;


public class LectureAdapter {

    public LectureDto toDto(Lecture lecture) {
        return aLectureDto().title(lecture.getTitle()).password(lecture.getPassword()).date(lecture.getDate()).startTime(lecture.getStartTime()).endTime(lecture.getEndTime()).commentsEnabled(lecture.getCommentsEnabled()).moodEnabled(lecture.getMoodEnabled()).build();
    }
    
    public Lecture toDomain(LectureDto lectureDto) {
    	return aLecture().title(lectureDto.getTitle()).password(lectureDto.getPassword()).date(lectureDto.getDate()).startTime(lectureDto.getStartTime()).endTime(lectureDto.getEndTime()).commentsEnabled(lectureDto.getCommentsEnabled()).moodEnabled(lectureDto.getMoodEnabled()).build();
    }
}
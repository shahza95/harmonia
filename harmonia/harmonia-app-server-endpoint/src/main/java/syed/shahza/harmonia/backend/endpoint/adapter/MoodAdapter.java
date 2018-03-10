package syed.shahza.harmonia.backend.endpoint.adapter;

import static syed.shahza.harmonia.backend.core.domain.Mood.aMood;
import static syed.shahza.harmonia.backend.dto.MoodDto.aMoodDto;
import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.dto.MoodDto;


public class MoodAdapter {
	private final LectureAdapter lectureAdapter;
	
	public MoodAdapter(LectureAdapter lectureAdapter) {
		this.lectureAdapter = lectureAdapter;
	}

    public MoodDto toDto(Mood mood) {
        return aMoodDto().emoji(mood.getEmoji()).lectureDto(this.lectureAdapter.toDto(mood.getLecture())).build();
    }
    
    public Mood toDomain(MoodDto moodDto) {
    	return aMood().emoji(moodDto.getEmoji()).lecture(this.lectureAdapter.toDomain(moodDto.getLectureDto())).build();
    }
}
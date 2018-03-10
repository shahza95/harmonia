package syed.shahza.harmonia.backend.endpoint.adapter;

import static syed.shahza.harmonia.backend.core.domain.Mood.aMood;
import static syed.shahza.harmonia.backend.core.domain.Moods.aMoodListBuilder;
import static syed.shahza.harmonia.backend.dto.MoodDto.aMoodDto;

import java.util.ArrayList;
import java.util.List;

import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Moods;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.MoodDtoList;


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
    
    public MoodDtoList toDto(Moods moods) {
    	MoodDtoList moodDtoList = new MoodDtoList();
    	for(Mood mood: moods.getMoodList()) {
    		moodDtoList.addMoodDtoToList(toDto(mood));
    	}
    	return moodDtoList;
    }
    
    public Moods toDomain(MoodDtoList moodDtoList) {
    	List<Mood> moodList = new ArrayList<Mood>();
    	for(MoodDto moodDto: moodDtoList.getMoodDtoList()) {
    		moodList.add(toDomain(moodDto));
    	}
    	return aMoodListBuilder().moodList(moodList).build();
    }
}
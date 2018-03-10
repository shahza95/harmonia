package syed.shahza.harmonia.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class MoodDtoList {
    private List<MoodDto> moodDtoList = new ArrayList<>();
    
    public MoodDtoList() {
    	super();
    }
    
    public MoodDtoList(List<MoodDto> moodDtoList) {
    	this.moodDtoList = moodDtoList;
    }

    public List<MoodDto> getMoodDtoList() {
        return new ArrayList<>(moodDtoList);
    }

    public void addMoodDtoToList(MoodDto moodDto) {
        this.moodDtoList.add(moodDto);
    }
}

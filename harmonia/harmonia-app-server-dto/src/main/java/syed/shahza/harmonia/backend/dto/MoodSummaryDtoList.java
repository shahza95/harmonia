package syed.shahza.harmonia.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class MoodSummaryDtoList {
    private List<MoodSummaryDto> moodSummaryDtoList = new ArrayList<>();
    
    public MoodSummaryDtoList() {
    	super();
    }
    
    public MoodSummaryDtoList(List<MoodSummaryDto> moodSummaryDtoList) {
    	this.moodSummaryDtoList = moodSummaryDtoList;
    }

    public List<MoodSummaryDto> getMoodSummaryDtoList() {
        return new ArrayList<>(moodSummaryDtoList);
    }

    public void addMoodSummaryDtoToList(MoodSummaryDto moodSummaryDto) {
        this.moodSummaryDtoList.add(moodSummaryDto);
    }
}

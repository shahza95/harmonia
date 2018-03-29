package syed.shahza.harmonia.backend.dto;

public class TestMoodSummaryDtoList {
	public static MoodSummaryDtoList aFilledMoodSummaryDtoList() {
		MoodSummaryDtoList moodSummaryDtoList = new MoodSummaryDtoList();
		for(EmotionDto emotionDto: EmotionDto.values()) {
			moodSummaryDtoList.addMoodSummaryDtoToList(TestMoodSummaryDto.aValidMoodSummaryDto().emotionDto(emotionDto).build());
		}
		return moodSummaryDtoList;
	}
	
	public static MoodSummaryDtoList anEmptyMoodSummaryDtoList() {
		return new MoodSummaryDtoList();
	}
}

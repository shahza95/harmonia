package syed.shahza.harmonia.backend.dto;

public class TestMoodDtoList {
	public static MoodDtoList aFilledMoodDtoList(int numberOfMoods) {
		MoodDtoList moodDtoList = new MoodDtoList();
		for(int i=0; i<numberOfMoods; i++) {
			moodDtoList.addMoodDtoToList(TestMoodDto.aValidMoodDto().build());
		}
		return moodDtoList;
	}
	
	public static MoodDtoList anEmptyMoodDtoList() {
		return new MoodDtoList();
	}
}

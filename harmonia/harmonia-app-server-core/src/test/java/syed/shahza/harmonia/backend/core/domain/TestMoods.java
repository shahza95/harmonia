package syed.shahza.harmonia.backend.core.domain;

import java.util.ArrayList;
import java.util.List;

public class TestMoods {
	
	public static Moods aFilledMoodsList(int numberOfMoods) {
		List<Mood> moods = new ArrayList<>();
		for(int i=0; i<numberOfMoods; i++) {
			moods.add(TestMood.aValidMood().build());
		}
		return Moods.aMoodListBuilder().moodList(moods).build();
	}
	
	public static Moods anEmptyMoodsList() {
		return Moods.aMoodListBuilder().moodList(null).build();
	}
}

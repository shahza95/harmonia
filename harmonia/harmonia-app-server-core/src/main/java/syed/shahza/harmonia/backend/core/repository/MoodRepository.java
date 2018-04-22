package syed.shahza.harmonia.backend.core.repository;

import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Moods;

public interface MoodRepository {

	Mood addMood(Mood mood);
	
	Moods getAllMoods(String lectureTitle);
	
	Boolean removeMood(String lectureTitle, String emoji);
}

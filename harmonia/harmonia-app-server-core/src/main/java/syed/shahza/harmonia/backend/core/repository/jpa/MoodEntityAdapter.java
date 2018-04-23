package syed.shahza.harmonia.backend.core.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Moods;

public class MoodEntityAdapter {
	private final LectureEntityAdapter lectureAdapter;

	public MoodEntityAdapter(LectureEntityAdapter lectureEntityAdapter) {
		this.lectureAdapter = lectureEntityAdapter;
	};

	public MoodEntity toEntity(Mood mood) {
		MoodEntity moodEntity = new MoodEntity();
		moodEntity.setEmoji(mood.getEmoji());
		moodEntity.setEmotion(mood.getEmotion());
		moodEntity.setLecture(this.lectureAdapter.toEntity(mood.getLecture()));
		return moodEntity;
	}

	public Mood toDomain(MoodEntity moodEntity) {
		Mood.Builder moodBuilder = Mood.aMood();

		moodBuilder.emoji(moodEntity.getEmoji())
				.emotion(moodEntity.getEmotion())
				.lecture(this.lectureAdapter.toDomain(moodEntity.getLecture()));

		return moodBuilder.build();
	}

	public Moods toDomain(List<MoodEntity> moodEntities) {
		List<Mood> moodList = new ArrayList<Mood>();
		for (MoodEntity moodEntity : moodEntities) {
			moodList.add(toDomain(moodEntity));
		}
		return Moods.aMoodListBuilder().moodList(moodList).build();
	}
}
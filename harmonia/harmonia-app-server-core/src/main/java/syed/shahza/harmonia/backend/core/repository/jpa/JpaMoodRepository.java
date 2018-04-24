package syed.shahza.harmonia.backend.core.repository.jpa;

import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Moods;
import syed.shahza.harmonia.backend.core.repository.MoodRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2MoodRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;

//data access layer, Mood related requests, for any database implementation (currently set to the only option: H2)
public class JpaMoodRepository implements MoodRepository {
	private final H2MoodRepository moodRepository;
	private final H2LectureRepository lectureRepository;
	private final MoodEntityAdapter moodEntityAdapter;
	
    public JpaMoodRepository(H2MoodRepository moodRepository, MoodEntityAdapter moodEntityAdapter, H2LectureRepository lectureRepository) {
        this.moodRepository = moodRepository;
        this.moodEntityAdapter = moodEntityAdapter;
        this.lectureRepository = lectureRepository;
    }

	@Override
	public Moods getAllMoods(String lectureTitle) {
		return this.moodEntityAdapter.toDomain(this.moodRepository.findByLectureTitle(lectureTitle));
	}

	@Override
	public Mood addMood(Mood mood) {
		MoodEntity moodEntity = this.moodEntityAdapter.toEntity(mood);
		moodEntity.setLecture(this.lectureRepository.findByTitle(mood.getLecture().getTitle()));
		this.moodRepository.save(moodEntity);
		return mood;
	}

	@Override
	public Boolean removeMood(String lectureTitle, String emoji) {
		this.moodRepository.delete(this.moodRepository.findByTitleAndEmoji(lectureTitle, emoji));
		return true;
	}
}

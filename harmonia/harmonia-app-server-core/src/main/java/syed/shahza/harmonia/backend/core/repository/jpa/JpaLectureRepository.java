package syed.shahza.harmonia.backend.core.repository.jpa;

import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;

public class JpaLectureRepository implements LectureRepository {
	private final H2LectureRepository lectureRepository;
	private final LectureEntityAdapter lectureEntityAdapter;
	
    public JpaLectureRepository(H2LectureRepository lectureRepository, LectureEntityAdapter lectureEntityAdapter) {
        this.lectureRepository = lectureRepository;
        this.lectureEntityAdapter = lectureEntityAdapter;
    }
    
	@Override
	public Lecture create(Lecture lecture) {
		this.lectureRepository.save(this.lectureEntityAdapter.toEntity(lecture));
		return lecture;
	}

	@Override
	public Lecture retrieveLectureFromPassword(String password) {
		return this.lectureEntityAdapter.toDomain(this.lectureRepository.findByPassword(password));
	}

	@Override
	public Lecture retrieveLectureFromTitle(String lectureTitle) {
		return this.lectureEntityAdapter.toDomain(this.lectureRepository.findByTitle(lectureTitle));
	}

	@Override
	public void update(Lecture lecture) {
		LectureEntity lectureEntity = this.lectureRepository.findByTitle(lecture.getTitle());
		lectureEntity.setEnded(lecture.getEnded());
		lectureEntity.setCommentsEnabled(lecture.getCommentsEnabled());
		lectureEntity.setMoodEnabled(lecture.getMoodEnabled());
		lectureEntity.setFeedbackEnabled(lecture.getFeedbackEnabled());
		lectureEntity.setQuestionsEnabled(lecture.getQuestionsEnabled());
		this.lectureRepository.save(lectureEntity);
	}
}

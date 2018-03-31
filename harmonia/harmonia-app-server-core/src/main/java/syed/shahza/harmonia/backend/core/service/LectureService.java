package syed.shahza.harmonia.backend.core.service;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Moods;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;

public class LectureService {
	private final LectureRepository lectureRepository;
	
	public LectureService(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}

	public Lecture create(Lecture lecture) {
		return this.lectureRepository.create(lecture);
	}
	
	public Lecture join(String password) {
		return this.lectureRepository.retrieveLectureFromPassword(password);
	}
	
	public Lecture getLecture(String lectureTitle) {
		return this.lectureRepository.retrieveLectureFromTitle(lectureTitle);
	}
	
	public Comments getAllComments(String lectureTitle) {
		return this.lectureRepository.getAllComments(lectureTitle);
	}
	
	public Comment addComment(Comment comment) {
		return this.lectureRepository.addComment(comment);
	}
	
	public Mood addMood(Mood mood) {
		return this.lectureRepository.addMood(mood);
	}
	
	public Moods getAllMoods(String lectureTitle) {
		return this.lectureRepository.getAllMoods(lectureTitle);
	}
	
	public void removeMood(String lectureTitle, String emoji) {
		this.lectureRepository.removeMood(lectureTitle, emoji);
	}
	
	public void update(Lecture lecture) {
		this.lectureRepository.update(lecture);
	}
}
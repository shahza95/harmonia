package syed.shahza.harmonia.backend.core.service;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;

public class LectureService {
	private final LectureRepository lectureRepository;
	
	public LectureService(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}

	public Lecture create(Lecture lecture) {
		return lectureRepository.create(lecture);
	}
	
	public Lecture join(String password) {
		return lectureRepository.retrieveLectureFromPassword(password);
	}
	
	public Comment addComment(String lectureTitle, Comment comment) {
		Comment returnedComment = this.lectureRepository.addComment(lectureTitle, comment);
		//if persisted (i.e. returnedComment not null?)
		if (returnedComment != null) {
			//post to active lecture Lecturer side - TO-DO
		}
		return returnedComment;
	}
}
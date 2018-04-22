package syed.shahza.harmonia.backend.core.service;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.core.domain.Feedbacks;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Moods;
import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.Questions;
import syed.shahza.harmonia.backend.core.repository.CommentRepository;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;

public class LectureService {
	private final LectureRepository lectureRepository;
	private final CommentRepository commentRepository;
	
	public LectureService(LectureRepository lectureRepository, CommentRepository commentRepository) {
		this.lectureRepository = lectureRepository;
		this.commentRepository = commentRepository;
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
		return this.commentRepository.getAllComments(lectureTitle);
	}
	
	public Comment addComment(Comment comment) {
		return this.commentRepository.addComment(comment);
	}
	
	public Mood addMood(Mood mood) {
//		return this.lectureRepository.addMood(mood);
		return null;
	}
	
	public Moods getAllMoods(String lectureTitle) {
//		return this.lectureRepository.getAllMoods(lectureTitle);
		return null;
	}
	
	public void removeMood(String lectureTitle, String emoji) {
//		this.lectureRepository.removeMood(lectureTitle, emoji);
	}
	
	public void update(Lecture lecture) {
//		this.lectureRepository.update(lecture);	
	}
	
	public Feedback addFeedback(Feedback feedback) {
//		return this.lectureRepository.addFeedback(feedback);
		return null;
	}
	
	public Feedbacks getAllFeedback(String lectureTitle) {
//		return this.lectureRepository.getAllFeedback(lectureTitle);
		return null;
	}
	
	public Question addQuestion(Question question) {
//		return this.lectureRepository.addQuestion(question);
		return null;
	}
	
	public Questions getAllQuestions(String lectureTitle) {
//		return this.lectureRepository.getAllQuestions(lectureTitle);
		return null;
	}
	
	public Question getQuestion(String id) {
//		return this.lectureRepository.getQuestion(id);
		return null;
	}
	
	public void updateQuestion(Question question) {
//		this.lectureRepository.updateQuestion(question);
	}
}
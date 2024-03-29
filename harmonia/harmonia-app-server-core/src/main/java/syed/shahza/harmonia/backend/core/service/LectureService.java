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
import syed.shahza.harmonia.backend.core.repository.FeedbackRepository;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;
import syed.shahza.harmonia.backend.core.repository.MoodRepository;
import syed.shahza.harmonia.backend.core.repository.QuestionRepository;

//entry point to core application where business logic occurs
// methods handle calls done at rest controller (app-server-endpoint)
public class LectureService {
	private final LectureRepository lectureRepository;
	private final CommentRepository commentRepository;
	private final MoodRepository moodRepository;
	private final FeedbackRepository feedbackRepository;
	private final QuestionRepository questionRepository;
	
	public LectureService(LectureRepository lectureRepository, CommentRepository commentRepository, MoodRepository moodRepository, FeedbackRepository feedbackRepository, QuestionRepository questionRepository) {
		this.lectureRepository = lectureRepository;
		this.commentRepository = commentRepository;
		this.moodRepository = moodRepository;
		this.feedbackRepository = feedbackRepository;
		this.questionRepository = questionRepository;
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
		return this.moodRepository.addMood(mood);
	}
	
	public Moods getAllMoods(String lectureTitle) {
		return this.moodRepository.getAllMoods(lectureTitle);
	}
	
	public void removeMood(String lectureTitle, String emoji) {
		this.moodRepository.removeMood(lectureTitle, emoji);
	}
	
	public void update(Lecture lecture) {
		this.lectureRepository.update(lecture);	
	}
	
	public Feedback addFeedback(Feedback feedback) {
		return this.feedbackRepository.addFeedback(feedback);
	}
	
	public Feedbacks getAllFeedback(String lectureTitle) {
		return this.feedbackRepository.getAllFeedback(lectureTitle);
	}
	
	public Question addQuestion(Question question) {
		return this.questionRepository.addQuestion(question);
	}
	
	public Questions getAllQuestions(String lectureTitle) {
		return this.questionRepository.getAllQuestions(lectureTitle);
	}
	
	public Question getQuestion(String id) {
		return this.questionRepository.getQuestion(id);
	}
	
	public void updateQuestion(Question question) {
		this.questionRepository.updateQuestion(question);
	}
}
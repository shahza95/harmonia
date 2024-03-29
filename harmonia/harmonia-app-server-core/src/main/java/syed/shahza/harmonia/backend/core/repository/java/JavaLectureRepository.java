package syed.shahza.harmonia.backend.core.repository.java;

import static syed.shahza.harmonia.backend.core.domain.Lecture.aLecture;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.core.domain.Feedbacks;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Moods;
import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.Questions;

//data access layer, for lecture related requests, using java collections for storage
public class JavaLectureRepository {
	private List<Lecture> lectures = new ArrayList<>();
	private List<Comment> comments = new ArrayList<>();
	private List<Mood> moods = new ArrayList<>();
	private List<Feedback> feedbacks = new ArrayList<>();
	private List<Question> questions = new ArrayList<>();
	
	public Lecture create(Lecture lecture) {
		lectures.add(lecture);
		return lecture;
	}

	public Lecture retrieveLectureFromPassword(String password) {
		for (Lecture lecture : lectures) {
			if (lecture.getPassword().equals(password)) {
				return lecture;
			}
		}
		return aLecture().build();
	}
	
	public Lecture retrieveLectureFromTitle(String lectureTitle) {
		for (Lecture lecture : lectures) {
			if (lecture.getTitle().equals(lectureTitle)) {
				return lecture;
			}
		}
		return aLecture().build();
	}
	
	public Comments getAllComments(String lectureTitle) {
		List<Comment> commentList = new ArrayList<Comment>();
		for(Comment comment: comments) {
			if(comment.getLecture().getTitle().equals(lectureTitle)) {
				commentList.add(comment);
			}
		}
		return Comments.aCommentListBuilder().commentList(commentList).build();
	}

	public Comment addComment(Comment comment) {
		comments.add(comment);
		return comment;
	}
	
	public Mood addMood(Mood mood) {
		moods.add(mood);
		return mood;
	}
	
	public Moods getAllMoods(String lectureTitle) {
		List<Mood> moodList = new ArrayList<Mood>();
		for(Mood mood: moods) {
			if(mood.getLecture().getTitle().equals(lectureTitle)) {
				moodList.add(mood);
			}
		}
		return Moods.aMoodListBuilder().moodList(moodList).build();
	}
	
	public Boolean removeMood(Mood moodToRemove) {
		return moods.removeIf(mood -> mood.getLecture().getTitle().equals(moodToRemove.getLecture().getTitle()) && mood.getEmoji().equals(moodToRemove.getEmoji()));
	}

	public void update(Lecture updatedLecture) {
		for(Lecture lecture: lectures) {
			if(lecture.getTitle().equals(updatedLecture.getTitle())) {
				lectures.set(lectures.indexOf(lecture), updatedLecture);
			}
		}
	}
	
	public Feedback addFeedback(Feedback feedback) {
		feedbacks.add(feedback);
		return feedback;
	}
	
	public Feedbacks getAllFeedback(String lectureTitle) {
		return new Feedbacks(feedbacks.stream().filter(feedback -> feedback.getLecture().getTitle().equals(lectureTitle)).collect(Collectors.toList()));
	}
	
	public Question addQuestion(Question question) {
		questions.add(question);
		return question;
	}
	
	public Questions getAllQuestions(String lectureTitle) {
		return new Questions(questions.stream().filter(question -> question.getLecture().getTitle().equals(lectureTitle)).collect(Collectors.toList()));
	}
	
	public Question getQuestion(String id) {
		return questions.stream().filter(question -> question.getId().equals(id)).findAny().orElse(new Question());
	}
	
	public void updateQuestion(Question updatedQuestion) {
		for(Question question: questions) {
			if(question.getId().equals(updatedQuestion.getId())) {
				questions.set(questions.indexOf(question), updatedQuestion);
			}
		}
	}
}

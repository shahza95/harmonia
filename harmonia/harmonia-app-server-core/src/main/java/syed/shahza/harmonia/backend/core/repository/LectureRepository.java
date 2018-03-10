package syed.shahza.harmonia.backend.core.repository;

import static syed.shahza.harmonia.backend.core.domain.Lecture.aLecture;

import java.util.ArrayList;
import java.util.List;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Moods;

public class LectureRepository {
	private List<Lecture> lectures = new ArrayList<>();
	private List<Comment> comments = new ArrayList<>();
	private List<Mood> moods = new ArrayList<>();
	
	public LectureRepository() {
		Lecture lecture = Lecture.aLecture().title("myTitle").build();
		this.comments.add(Comment.aComment().message("comment1").lecture(lecture).build());
		this.comments.add(Comment.aComment().message("comment2").lecture(lecture).build());
		this.moods.add(Mood.aMood().emoji(":)").lecture(lecture).build());
	}

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
}

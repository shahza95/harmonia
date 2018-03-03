package syed.shahza.harmonia.backend.core.repository;

import static syed.shahza.harmonia.backend.core.domain.Lecture.aLecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.core.domain.Lecture;

public class LectureRepository {
	private List<Lecture> lectures = new ArrayList<>();
	private Map<String, ArrayList<Comment>> comments = new HashMap<String, ArrayList<Comment>>();

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

	public Comments getComments(String lectureTitle) {
		return Comments.aCommentListBuilder().commentList(comments.get(lectureTitle)).build();
	}

	public Comment addComment(String lectureTitle, Comment comment) {
		ArrayList<Comment> commentList;
		if(comments.containsKey(comment)) {
			commentList = comments.get(lectureTitle);
		} else {
			commentList = new ArrayList<Comment>();
		}
		
		commentList.add(comment);
		comments.put(lectureTitle, commentList);
		return comment;
	}
}

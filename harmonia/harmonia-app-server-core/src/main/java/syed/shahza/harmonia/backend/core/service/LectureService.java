package syed.shahza.harmonia.backend.core.service;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;

@EnableJms
public class LectureService {
	private final LectureRepository lectureRepository;
	private final JmsTemplate jmsTemplate;
	private static final String JMS_DESTINATION = "lecture";
	
	public LectureService(LectureRepository lectureRepository, JmsTemplate jmsTemplate) {
		this.lectureRepository = lectureRepository;
		this.jmsTemplate = jmsTemplate;
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
		System.out.println("SERVICE--->" + lectureTitle);
		return this.lectureRepository.getAllComments(lectureTitle);
	}
	
	public Comment addComment(Comment comment) {
		Comment returnedComment = this.lectureRepository.addComment(comment);
		//if persisted (i.e. returnedComment not null?)
		if (returnedComment != null) {
	        this.jmsTemplate.convertAndSend(JMS_DESTINATION, comment);
		}
		return returnedComment;
	}
}
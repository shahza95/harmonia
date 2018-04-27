package syed.shahza.harmonia.backend.core.repository.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//Hibernate compatible (direct mapping to database) Question object
@Entity(name = "QUESTION")
@Table(name = "QUESTION")
public class QuestionEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "QUESTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "UUID", nullable = false)
	private String uuid;

    @Column(name = "QUESTION", nullable = false)
    private String question;
    
    @Column(name = "ANSWER", nullable = true)
    private String answer;

    @OneToOne
    @JoinColumn(name = "LECTURE_ID")
    private LectureEntity lecture;
    
    public void setUuid(String uuid) {
    	this.uuid = uuid;
    }
    
    public String getUuid() {
    	return uuid;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
    
    public void setAnswer(String answer) {
    	this.answer = answer;
    }
    
    public String getAnswer() {
    	return answer;
    }
    
    public void setLecture(LectureEntity lecture) {
    	this.lecture = lecture;
    }

    public LectureEntity getLecture() {
        return lecture;
    }
}
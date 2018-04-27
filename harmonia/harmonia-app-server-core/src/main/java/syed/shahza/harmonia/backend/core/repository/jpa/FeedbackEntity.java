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


//Hibernate compatible (direct mapping to database) Feedback object
@Entity(name = "FEEDBACK")
@Table(name = "FEEDBACK")
public class FeedbackEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FEEDBACK_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RATING", nullable = false)
    private int rating;
    
    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @OneToOne
    @JoinColumn(name = "LECTURE_ID")
    private LectureEntity lecture;
    
    public void setRating(int rating) {
    	this.rating = rating;
    }
    
    public int getRating() {
    	return rating;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    public void setLecture(LectureEntity lecture) {
    	this.lecture = lecture;
    }

    public LectureEntity getLecture() {
        return lecture;
    }
}
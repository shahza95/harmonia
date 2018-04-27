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

//Hibernate compatible (direct mapping to database) Comment object
@Entity(name = "COMMENT")
@Table(name = "COMMENT")
public class CommentEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COMMENT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @OneToOne
    @JoinColumn(name = "LECTURE_ID")
    private LectureEntity lecture;

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
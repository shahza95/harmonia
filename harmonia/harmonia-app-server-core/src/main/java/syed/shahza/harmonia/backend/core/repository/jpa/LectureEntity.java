package syed.shahza.harmonia.backend.core.repository.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

@Entity(name = "LECTURE")
@Table(name = "LECTURE")
public class LectureEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LECTURE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", nullable = false, unique=true)
    private String title;

    @Column(name = "PASSWORD", nullable = false, unique=true)
    private String password;
    
    @Column(name = "DATE", nullable = false)
    private LocalDate date;
    
    @Column(name = "START_TIME", nullable = false)
    private LocalTime startTime;
    
    @Column(name = "END_TIME", nullable = false)
    private LocalTime endTime;
    
    @Column(name = "ENDED", nullable = false)
    private boolean ended;
    
    @Column(name = "COMMENTS_ENABLED", nullable = false)
    private boolean commentsEnabled;
    
    @Column(name = "MOOD_ENABLED", nullable = false)
    private boolean moodEnabled;
    
    @Column(name = "FEEDBACK_ENABLED", nullable = false)
    private boolean feedbackEnabled;
    
    @Column(name = "QUESTIONS_ENABLED", nullable = false)
    private boolean questionsEnabled;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public void setDate(LocalDate date) {
    	this.date = date;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
    
    public void setStartTime(LocalTime startTime) {
    	this.startTime = startTime;
    }
    
    public LocalTime getStartTime() {
        return this.startTime;
    }
    
    public void setEndTime(LocalTime endTime) {
    	this.endTime = endTime;
    }
    
    public LocalTime getEndTime() {
        return this.endTime;
    }
    
    public void setEnded(boolean ended) {
    	this.ended = ended;
    }
    
    public boolean getEnded() {
    	return this.ended;
    }
    
    public void setCommentsEnabled(boolean commentsEnabled) {
    	this.commentsEnabled = commentsEnabled;
    }
    
    public boolean getCommentsEnabled() {
    	return this.commentsEnabled;
    }
    
    
    public void setMoodEnabled(boolean moodEnabled) {
    	this.moodEnabled = moodEnabled;
    }
    
    public boolean getMoodEnabled() {
    	return this.moodEnabled;
    }
    
    
    public void setFeedbackEnabled(boolean feedbackEnabled) {
    	this.feedbackEnabled = feedbackEnabled;
    }
    
    public boolean getFeedbackEnabled() {
    	return this.feedbackEnabled;
    }
    
    public void setQuestionsEnabled(boolean questionsEnabled) {
    	this.questionsEnabled = questionsEnabled;
    }
    
    public boolean getQuestionsEnabled() {
    	return this.questionsEnabled;
    }
}
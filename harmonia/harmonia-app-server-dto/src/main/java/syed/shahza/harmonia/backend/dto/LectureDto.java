package syed.shahza.harmonia.backend.dto;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

//POJO for transferable (serializable) Lecture object
public class LectureDto {
    private String title;
    private String password;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean ended = false;
    private boolean commentsEnabled = true;
    private boolean moodEnabled = true;
    private boolean feedbackEnabled = true;
    private boolean questionsEnabled = true;

    public LectureDto() {
        super();
    }

    private LectureDto(Builder builder) {
        this();
        this.title = builder.title;
        this.password = builder.password;
        this.date = builder.date;
        this.startTime = builder.startTime;      
        this.endTime = builder.endTime;      
        this.ended = builder.ended;      
        this.commentsEnabled = builder.commentsEnabled;      
        this.moodEnabled = builder.moodEnabled;      
        this.feedbackEnabled = builder.feedbackEnabled;      
        this.questionsEnabled = builder.questionsEnabled;      
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    
    public boolean getEnded() {
    	return this.ended;
    }
    
    public void setEnded(boolean ended) {
    	this.ended = ended;
    }
    
    public boolean getCommentsEnabled() {
    	return this.commentsEnabled;
    }
    
    public void setCommentsEnabled(boolean commentsEnabled) {
    	this.commentsEnabled = commentsEnabled;
    }
    
    public boolean getMoodEnabled() {
    	return this.moodEnabled;
    }
    
    public void setMoodEnabled(boolean moodEnabled) {
    	this.moodEnabled = moodEnabled;
    }
    
    public boolean getFeedbackEnabled() {
    	return this.feedbackEnabled;
    }
    
    public void setFeedbackEnabled(boolean feedbackEnabled) {
    	this.feedbackEnabled = feedbackEnabled;
    }
    
    public boolean getQuestionsEnabled() {
    	return this.questionsEnabled;
    }
    
    public void setQuestionsEnabled(boolean questionsEnabled) {
    	this.questionsEnabled = questionsEnabled;
    }
    
    public Boolean isEmpty() {
    	return this.title == null && this.password == null && this.date == null && this.startTime == null && this.endTime == null;
    }

    public static Builder aLectureDto() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String password;
        private LocalDate date;
        private LocalTime startTime;
        private LocalTime endTime;
        private boolean ended = false;
        private boolean commentsEnabled = true;
        private boolean moodEnabled = true;
        private boolean feedbackEnabled = true;
        private boolean questionsEnabled = true;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }
        
        public Builder date(LocalDate date) {
        	this.date = date;
        	return this;
        }
        
        public Builder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }
        
        public Builder endTime(LocalTime endTime) {
        	this.endTime = endTime;
        	return this;
        }
        
        public Builder ended(boolean ended) {
        	this.ended = ended;
        	return this;
        }
        
        public Builder commentsEnabled(boolean commentsEnabled) {
        	this.commentsEnabled = commentsEnabled;
        	return this;
        }
        
        public Builder moodEnabled(boolean moodEnabled) {
        	this.moodEnabled = moodEnabled;
        	return this;
        }
        
        public Builder feedbackEnabled(boolean feedbackEnabled) {
        	this.feedbackEnabled = feedbackEnabled;
        	return this;
        }
        
        public Builder questionsEnabled(boolean questionsEnabled) {
        	this.questionsEnabled = questionsEnabled;
        	return this;
        }

        public LectureDto build() {
            return new LectureDto(this);
        }
    }
}
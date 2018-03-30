package syed.shahza.harmonia.backend.core.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class Lecture {
    private String title;
    private String password;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean commentsEnabled;

    public Lecture() {
        super();
    }

    private Lecture(Builder builder) {
        this();
        this.title = builder.title;
        this.password = builder.password;
        this.date = builder.date;
        this.startTime = builder.startTime;      
        this.endTime = builder.endTime;      
        this.commentsEnabled = builder.commentsEnabled;      
    }

    public String getTitle() {
        return this.title;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
    
    public LocalTime getStartTime() {
        return this.startTime;
    }
    
    public LocalTime getEndTime() {
        return this.endTime;
    }
    
    public boolean getCommentsEnabled() {
    	return this.commentsEnabled;
    }

    public Boolean isEmpty() {
    	return this.title == null && this.password == null && this.date == null && this.startTime == null && this.endTime == null;
    }

    public static Builder aLecture() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String password;
        private LocalDate date;
        private LocalTime startTime;
        private LocalTime endTime;
        private boolean commentsEnabled;

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
        
        public Builder commentsEnabled(boolean commentsEnabled) {
        	this.commentsEnabled = commentsEnabled;
        	return this;
        }

        public Lecture build() {
            return new Lecture(this);
        }
    }
}
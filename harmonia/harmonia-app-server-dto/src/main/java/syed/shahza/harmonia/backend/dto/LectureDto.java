package syed.shahza.harmonia.backend.dto;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class LectureDto {
    private String title;
    private String password;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean enabled;

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
        this.enabled = builder.enabled;      
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
    
    public boolean getEnabled() {
    	return this.enabled;
    }
    
    public void setEnabled(boolean enabled) {
    	this.enabled = enabled;
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
        private boolean enabled = true;

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
        
        public Builder enabled(boolean enabled) {
        	this.enabled = enabled;
        	return this;
        }

        public LectureDto build() {
            return new LectureDto(this);
        }
    }
}
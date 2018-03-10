package syed.shahza.harmonia.backend.core.domain;

public class Comment {
    private String message;
    private Lecture lecture;

    public Comment() {
        super();
    }

    private Comment(Builder builder) {
        this();
        this.message = builder.message;  
        this.lecture = builder.lecture;  
    }

    public String getMessage() {
        return this.message;
    }
  
    
    public Lecture getLecture() {
    	return this.lecture;
    }
    
    public static Builder aComment() {
        return new Builder();
    }

    public static class Builder {
        private String message;
        private Lecture lecture;

        public Builder message(String message) {
            this.message = message;
            return this;
        }
        
        public Builder lecture(Lecture lecture) {
        	this.lecture = lecture;
        	return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }
}
package syed.shahza.harmonia.backend.core.domain;

public class Feedback {
    private Lecture lecture;
    private int rating;
    private String message;

    public Feedback() {
        super();
    }

    private Feedback(Builder builder) {
        this();
        this.lecture = builder.lecture;  
        this.rating = builder.rating;  
        this.message = builder.message;  
    }
    
    public Lecture getLecture() {
    	return this.lecture;
    }
    
    public int getRating() {
        return this.rating;
    }
  
    public String getMessage() {
    	return this.message;
    }
    
    public static Builder aFeedback() {
        return new Builder();
    }

    public static class Builder {
    	private Lecture lecture;
        private int rating;
        private String message;
        
        public Builder lecture(Lecture lecture) {
        	this.lecture = lecture;
        	return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }
        
        public Builder message(String message) {
        	this.message = message;
        	return this;
        }

        public Feedback build() {
            return new Feedback(this);
        }
    }
}
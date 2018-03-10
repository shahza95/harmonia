package syed.shahza.harmonia.backend.core.domain;

public class Mood {
    private String emoji;
    private Lecture lecture;

    public Mood() {
        super();
    }

    private Mood(Builder builder) {
        this();
        this.emoji = builder.emoji;  
        this.lecture = builder.lecture;  
    }

    public String getEmoji() {
        return this.emoji;
    }
  
    
    public Lecture getLecture() {
    	return this.lecture;
    }
    
    public static Builder aMood() {
        return new Builder();
    }

    public static class Builder {
        private String emoji;
        private Lecture lecture;

        public Builder emoji(String emoji) {
            this.emoji = emoji;
            return this;
        }
        
        public Builder lecture(Lecture lecture) {
        	this.lecture = lecture;
        	return this;
        }

        public Mood build() {
            return new Mood(this);
        }
    }
}
package syed.shahza.harmonia.backend.core.domain;

public class Question {
	private String question;
    private String answer;
    private Lecture lecture;

    public Question() {
        super();
    }

    private Question(Builder builder) {
        this();
        this.question = builder.question;  
        this.answer = builder.answer;  
        this.lecture = builder.lecture;  
    }
    
    public String getQuestion() {
    	return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }
    
    public Lecture getLecture() {
    	return this.lecture;
    }
    
    public static Builder aQuestion() {
        return new Builder();
    }

    public static class Builder {
    	private String question;
        private String answer;
        private Lecture lecture;
        
        public Builder question(String question) {
        	this.question = question;
        	return this;
        }      

        public Builder answer(String answer) {
            this.answer = answer;
            return this;
        }
        
        public Builder lecture(Lecture lecture) {
        	this.lecture = lecture;
        	return this;
        }

        public Question build() {
            return new Question(this);
        }
    }
}
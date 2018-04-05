package syed.shahza.harmonia.backend.dto;

public class QuestionDto {
	private LectureDto lectureDto;
    private String question;
    private String answer;

    public QuestionDto() {
        super();
    }

    private QuestionDto(Builder builder) {
        this();
        this.question = builder.question;
        this.answer = builder.answer;
        this.lectureDto = builder.lectureDto;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getAnswer() {
    	return this.answer;
    }
    
    public void setAnswer(String answer) {
    	this.answer = answer;
    }
    
    public LectureDto getLectureDto() {
    	return this.lectureDto;
    }
    
    public void setLectureDto(LectureDto lectureDto) {
    	this.lectureDto = lectureDto;
    }

    public static Builder aQuestionDto() {
        return new Builder();
    }

    public static class Builder {
        private String question;
        private String answer;
        private LectureDto lectureDto;

        public Builder question(String question) {
            this.question = question;
            return this;
        }        
        
        public Builder answer(String answer) {
        	this.answer = answer;
        	return this;
        }
        
        public Builder lectureDto(LectureDto lectureDto) {
        	this.lectureDto = lectureDto;
        	return this;
        }

        public QuestionDto build() {
            return new QuestionDto(this);
        }
    }
}

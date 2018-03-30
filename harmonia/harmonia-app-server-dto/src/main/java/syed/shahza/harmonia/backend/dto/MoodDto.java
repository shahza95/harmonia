package syed.shahza.harmonia.backend.dto;

public class MoodDto {
	private String emoji;
	private EmotionDto emotionDto;
	private LectureDto lectureDto;

    public MoodDto() {
        super();
    }

    private MoodDto(Builder builder) {
        this();
        this.emoji = builder.emoji;
        this.emotionDto = builder.emotionDto;
        this.lectureDto = builder.lectureDto;
    }

    public String getEmoji() {
        return this.emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
    
    public EmotionDto getEmotionDto() {
    	return this.emotionDto;
    }
    
    public void setEmotionDto(EmotionDto emotionDto) {
    	this.emotionDto = emotionDto;
    }
    
    public LectureDto getLectureDto() {
    	return this.lectureDto;
    }
    
    public void setLectureDto(LectureDto lectureDto) {
    	this.lectureDto = lectureDto;
    }

    public static Builder aMoodDto() {
        return new Builder();
    }

    public static class Builder {
        private String emoji;
        private EmotionDto emotionDto;
        private LectureDto lectureDto;

        public Builder emoji(String emoji) {
            this.emoji = emoji;
            return this;
        }
        
        public Builder emotionDto(EmotionDto emotionDto) {
        	this.emotionDto = emotionDto;
        	return this;
        }
        
        public Builder lectureDto(LectureDto lectureDto) {
        	this.lectureDto = lectureDto;
        	return this;
        }

        public MoodDto build() {
            return new MoodDto(this);
        }
    }
}

package syed.shahza.harmonia.backend.dto;

public class MoodSummaryDto {
	private EmotionDto emotionDto;
	private Integer sum;

    public MoodSummaryDto() {
        super();
    }

    private MoodSummaryDto(Builder builder) {
        this();
        this.emotionDto = builder.emotionDto;
        this.sum = builder.sum;
    }

    public EmotionDto getEmotionDto() {
    	return this.emotionDto;
    }
    
    public void setEmotionDto(EmotionDto emotionDto) {
    	this.emotionDto = emotionDto;
    }
    
    public Integer getSum() {
    	return this.sum;
    }
    
    public void setLectureDto(Integer sum) {
    	this.sum = sum;
    }

    public static Builder aMoodSummaryDto() {
        return new Builder();
    }

    public static class Builder {
        private EmotionDto emotionDto;
        private Integer sum;
        
        public Builder emotionDto(EmotionDto emotionDto) {
        	this.emotionDto = emotionDto;
        	return this;
        }
        
        public Builder sum(Integer sum) {
        	this.sum = sum;
        	return this;
        }

        public MoodSummaryDto build() {
            return new MoodSummaryDto(this);
        }
    }
}

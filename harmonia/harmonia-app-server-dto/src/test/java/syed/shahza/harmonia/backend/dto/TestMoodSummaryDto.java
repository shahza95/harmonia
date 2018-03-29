package syed.shahza.harmonia.backend.dto;

import static syed.shahza.harmonia.backend.dto.MoodSummaryDto.aMoodSummaryDto;
import syed.shahza.harmonia.backend.dto.MoodSummaryDto.Builder;

public class TestMoodSummaryDto {
    public static Builder aValidMoodSummaryDto() {
        return aMoodSummaryDto().emotionDto(EmotionDto.HAPPY).sum(3);
    }
}

package syed.shahza.harmonia.backend.dto;

import static syed.shahza.harmonia.backend.dto.QuestionDto.aQuestionDto;

import java.util.UUID;

import syed.shahza.harmonia.backend.dto.QuestionDto.Builder;

public class TestQuestionDto {
    public static Builder aValidQuestionDto() {
        return aQuestionDto().id(UUID.randomUUID().toString()).question("Some question here?").answer("Some answer here.").lectureDto(TestLectureDto.aValidLectureDto().build());
    }
}

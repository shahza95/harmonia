package syed.shahza.harmonia.backend.endpoint.adapter;

import static syed.shahza.harmonia.backend.core.domain.Question.aQuestion;
import static syed.shahza.harmonia.backend.dto.QuestionDto.aQuestionDto;
import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.dto.QuestionDto;


public class QuestionAdapter {
	private final LectureAdapter lectureAdapter;
	
	public QuestionAdapter(LectureAdapter lectureAdapter) {
		this.lectureAdapter = lectureAdapter;
	}

    public QuestionDto toDto(Question question) {
        return aQuestionDto().question(question.getQuestion()).answer(question.getAnswer()).lectureDto(this.lectureAdapter.toDto(question.getLecture())).build();
    }
    
    public Question toDomain(QuestionDto questionDto) {
    	return aQuestion().question(questionDto.getQuestion()).answer(questionDto.getAnswer()).lecture(this.lectureAdapter.toDomain(questionDto.getLectureDto())).build();
    }
}
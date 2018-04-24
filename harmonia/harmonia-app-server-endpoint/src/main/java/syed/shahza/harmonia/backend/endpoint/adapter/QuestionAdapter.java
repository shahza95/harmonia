package syed.shahza.harmonia.backend.endpoint.adapter;

import static syed.shahza.harmonia.backend.core.domain.Question.aQuestion;
import static syed.shahza.harmonia.backend.core.domain.Questions.aQuestionListBuilder;
import static syed.shahza.harmonia.backend.dto.QuestionDto.aQuestionDto;

import java.util.ArrayList;
import java.util.List;

import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.Questions;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.backend.dto.QuestionDtoList;

// Question object: DTO / DAO adapter
public class QuestionAdapter {
	private final LectureAdapter lectureAdapter;
	
	public QuestionAdapter(LectureAdapter lectureAdapter) {
		this.lectureAdapter = lectureAdapter;
	}

    public QuestionDto toDto(Question question) {
        return aQuestionDto().id(question.getId()).question(question.getQuestion()).answer(question.getAnswer()).lectureDto(this.lectureAdapter.toDto(question.getLecture())).build();
    }
    
    public Question toDomain(QuestionDto questionDto) {
    	return aQuestion().id(questionDto.getId()).question(questionDto.getQuestion()).answer(questionDto.getAnswer()).lecture(this.lectureAdapter.toDomain(questionDto.getLectureDto())).build();
    }
    
    // convert custom collection of questions
    
    public QuestionDtoList toDto(Questions questions) {
    	QuestionDtoList questionDtoList = new QuestionDtoList();
    	for(Question question: questions.getQuestionList()) {
    		questionDtoList.addQuestionDtoToList(toDto(question));
    	}
    	return questionDtoList;
    }

    public Questions toDomain(QuestionDtoList questionDtoList) {
    	List<Question> questionList = new ArrayList<Question>();
    	for(QuestionDto questionDto: questionDtoList.getQuestionDtoList()) {
    		questionList.add(toDomain(questionDto));
    	}
    	return aQuestionListBuilder().questionList(questionList).build();
    }
}
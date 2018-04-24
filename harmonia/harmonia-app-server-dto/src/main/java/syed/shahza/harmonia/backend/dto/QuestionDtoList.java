package syed.shahza.harmonia.backend.dto;

import java.util.ArrayList;
import java.util.List;

// Custom high level object of multiple QuestionDtos
public class QuestionDtoList {
    private List<QuestionDto> questionDtoList = new ArrayList<>();
    
    public QuestionDtoList() {
    	super();
    }
    
    public QuestionDtoList(List<QuestionDto> questionDtoList) {
    	this.questionDtoList = questionDtoList;
    }

    public List<QuestionDto> getQuestionDtoList() {
        return new ArrayList<>(questionDtoList);
    }

    public void addQuestionDtoToList(QuestionDto questionDto) {
        this.questionDtoList.add(questionDto);
    }
}

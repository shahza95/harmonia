package syed.shahza.harmonia.backend.dto;

public class TestQuestionDtoList {
	public static QuestionDtoList aFilledQuestionDtoList(int numberOfQuestions) {
		QuestionDtoList questionDtoList = new QuestionDtoList();
		for(int i=0; i<numberOfQuestions; i++) {
			questionDtoList.addQuestionDtoToList(TestQuestionDto.aValidQuestionDto().build());
		}
		return questionDtoList;
	}
	
	public static QuestionDtoList anEmptyQuestionDtoList() {
		return new QuestionDtoList();
	}
}

package syed.shahza.harmonia.backend.core.domain;

import java.util.ArrayList;
import java.util.List;

public class TestQuestions {
	public static Questions aFilledQuestionsList(int numberOfQuestions) {
		List<Question> questions = new ArrayList<>();
		for(int i=0; i<numberOfQuestions; i++) {
			questions.add(TestQuestion.aValidQuestion().build());
		}
		return Questions.aQuestionListBuilder().questionList(questions).build();
	}
	
	public static Questions anEmptyQuestionsList() {
		return Questions.aQuestionListBuilder().questionList(null).build();
	}
}

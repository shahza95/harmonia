package syed.shahza.harmonia.backend.core.repository;

import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.Questions;

// data access layer Question interface
public interface QuestionRepository {

	Question addQuestion(Question question);
	
	Questions getAllQuestions(String lectureTitle);
	
	Question getQuestion(String id);
	
	void updateQuestion(Question updatedQuestion);
}

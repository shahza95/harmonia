package syed.shahza.harmonia.backend.core.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.Questions;

public class QuestionEntityAdapter {
	private final LectureEntityAdapter lectureAdapter;

	public QuestionEntityAdapter(LectureEntityAdapter lectureEntityAdapter) {
		this.lectureAdapter = lectureEntityAdapter;
	};

	public QuestionEntity toEntity(Question question) {
		QuestionEntity questionEntity = new QuestionEntity();
		questionEntity.setUuid(question.getId());
		questionEntity.setQuestion(question.getQuestion());
		questionEntity.setAnswer(question.getAnswer());
		questionEntity.setLecture(this.lectureAdapter.toEntity(question.getLecture()));
		return questionEntity;
	}

	public Question toDomain(QuestionEntity questionEntity) {
		Question.Builder questionBuilder = Question.aQuestion();

		questionBuilder.id(questionEntity.getUuid())
				.question(questionEntity.getQuestion())
				.answer(questionEntity.getAnswer())
				.lecture(this.lectureAdapter.toDomain(questionEntity.getLecture()));

		return questionBuilder.build();
	}

	public Questions toDomain(List<QuestionEntity> questionEntities) {
		List<Question> questionList = new ArrayList<Question>();
		for (QuestionEntity questionEntity : questionEntities) {
			questionList.add(toDomain(questionEntity));
		}
		return Questions.aQuestionListBuilder().questionList(questionList).build();
	}
}
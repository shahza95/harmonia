package syed.shahza.harmonia.backend.core.repository.jpa;

import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.Questions;
import syed.shahza.harmonia.backend.core.repository.QuestionRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2QuestionRepository;

public class JpaQuestionRepository implements QuestionRepository {
	private final H2QuestionRepository questionRepository;
	private final H2LectureRepository lectureRepository;
	private final QuestionEntityAdapter questionEntityAdapter;
	
    public JpaQuestionRepository(H2QuestionRepository questionRepository, QuestionEntityAdapter questionEntityAdapter, H2LectureRepository lectureRepository) {
        this.questionRepository = questionRepository;
        this.questionEntityAdapter = questionEntityAdapter;
        this.lectureRepository = lectureRepository;
    }

	@Override
	public Question addQuestion(Question question) {
		QuestionEntity questionEntity = this.questionEntityAdapter.toEntity(question);
		questionEntity.setLecture(this.lectureRepository.findByTitle(question.getLecture().getTitle()));
		this.questionRepository.save(questionEntity);
		return question;
	}

	@Override
	public Questions getAllQuestions(String lectureTitle) {
		return this.questionEntityAdapter.toDomain(this.questionRepository.findByLectureTitle(lectureTitle));
	}

	@Override
	public Question getQuestion(String id) {
		return this.questionEntityAdapter.toDomain(this.questionRepository.findByUuid(id));
	}

	@Override
	public void updateQuestion(Question updatedQuestion) {
		QuestionEntity questionEntity = this.questionRepository.findByUuid(updatedQuestion.getId());
		questionEntity.setAnswer(updatedQuestion.getAnswer());
		this.questionRepository.save(questionEntity);
	}

}

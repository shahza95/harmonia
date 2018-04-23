package syed.shahza.harmonia.backend.core.repository.jpa;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.TestQuestion;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2QuestionRepository;

@RunWith(MockitoJUnitRunner.class)
public class JpaQuestionRepositoryTest {
	private JpaQuestionRepository repository;
	private Question question;
	private QuestionEntity questionEntity;
	
	@Mock
	private H2QuestionRepository mockH2QuestionRepository;
	
	@Mock
	private H2LectureRepository mockH2LectureRepository;
	
	@Mock
	private QuestionEntityAdapter mockQuestionEntityAdapter;
	
	@Before
	public void setUp() {
		question = TestQuestion.aValidQuestion().build();
		questionEntity = TestQuestionEntity.aQuestionEntity();
		repository = new JpaQuestionRepository(this.mockH2QuestionRepository, this.mockQuestionEntityAdapter, this.mockH2LectureRepository);
	}
	
	@Test
	public void getAllQuestionShouldInvokeQuestionRepository() {
		this.repository.getAllQuestions("lectureTitle");
		
		verify(this.mockH2QuestionRepository).findByLectureTitle("lectureTitle");
	}
	
	@Test
	public void getAllQuestionShouldInvokeEntityAdapter() {
		String title = "lectureTitle";
		List<QuestionEntity> questionEntityList = new ArrayList<QuestionEntity>();
		when(this.mockH2QuestionRepository.findByLectureTitle(title)).thenReturn(questionEntityList);
		this.repository.getAllQuestions(title);
		
		verify(this.mockQuestionEntityAdapter).toDomain(questionEntityList);
	}
	
	@Test
	public void addQuestionShouldInvokeEntityAdapter() {
		when(this.mockQuestionEntityAdapter.toEntity(question)).thenReturn(TestQuestionEntity.aQuestionEntity());
		this.repository.addQuestion(question);
		
		verify(this.mockQuestionEntityAdapter).toEntity(question);
	}
	
	@Test
	public void addQuestionShouldInvokeQuestionRepository() {
		QuestionEntity questionEntity = TestQuestionEntity.aQuestionEntity();
		when(this.mockQuestionEntityAdapter.toEntity(question)).thenReturn(questionEntity);
		this.repository.addQuestion(question);
		
		verify(this.mockH2QuestionRepository).save(questionEntity);
	}
	
	@Test
	public void getQuestionShouldInvokeQuestionRepository() {
		this.repository.getQuestion(question.getId());
		
		verify(this.mockH2QuestionRepository).findByUuid(question.getId());
	}
	
	@Test
	public void retrieveLectureFromTitleShouldInvokeEntityAdapter() {
		when(this.mockH2QuestionRepository.findByUuid(question.getId())).thenReturn(questionEntity);
		this.repository.getQuestion(question.getId());
		
		verify(this.mockQuestionEntityAdapter).toDomain(questionEntity);
	}
	
	@Test
	public void updateShouldInvokeQuestionRepositoryFindByUuid() {
		when(this.mockH2QuestionRepository.findByUuid(question.getId())).thenReturn(questionEntity);
		this.repository.updateQuestion(question);
		
		verify(this.mockH2QuestionRepository).findByUuid(question.getId());
	}
	
	@Test
	public void updateShouldInvokeQuestionRepository() {
		when(this.mockH2QuestionRepository.findByUuid(question.getId())).thenReturn(questionEntity);
		this.repository.updateQuestion(question);
		
		verify(this.mockH2QuestionRepository).save(questionEntity);
	}	
}

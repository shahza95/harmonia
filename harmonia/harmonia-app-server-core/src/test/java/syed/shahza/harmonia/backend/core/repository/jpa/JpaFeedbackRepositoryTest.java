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

import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.core.domain.TestFeedback;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2FeedbackRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;

@RunWith(MockitoJUnitRunner.class)
public class JpaFeedbackRepositoryTest {
	private JpaFeedbackRepository repository;
	private Feedback feedback;
	
	@Mock
	private H2FeedbackRepository mockH2FeedbackRepository;
	
	@Mock
	private H2LectureRepository mockH2LectureRepository;
	
	@Mock
	private FeedbackEntityAdapter mockFeedbackEntityAdapter;
	
	@Before
	public void setUp() {
		feedback = TestFeedback.aValidFeedback().build();
		repository = new JpaFeedbackRepository(this.mockH2FeedbackRepository, this.mockFeedbackEntityAdapter, this.mockH2LectureRepository);
	}
	
	@Test
	public void getAllFeedbackShouldInvokeFeedbackRepository() {
		this.repository.getAllFeedback("lectureTitle");
		
		verify(this.mockH2FeedbackRepository).findByLectureTitle("lectureTitle");
	}
	
	@Test
	public void getAllFeedbackShouldInvokeEntityAdapter() {
		String title = "lectureTitle";
		List<FeedbackEntity> feedbackEntityList = new ArrayList<FeedbackEntity>();
		when(this.mockH2FeedbackRepository.findByLectureTitle(title)).thenReturn(feedbackEntityList);
		this.repository.getAllFeedback(title);
		
		verify(this.mockFeedbackEntityAdapter).toDomain(feedbackEntityList);
	}
	
	@Test
	public void addFeedbackShouldInvokeEntityAdapter() {
		when(this.mockFeedbackEntityAdapter.toEntity(feedback)).thenReturn(TestFeedbackEntity.aFeedbackEntity());
		this.repository.addFeedback(feedback);
		
		verify(this.mockFeedbackEntityAdapter).toEntity(feedback);
	}
	
	@Test
	public void addFeedbackShouldInvokeFeedbackRepository() {
		FeedbackEntity feedbackEntity = TestFeedbackEntity.aFeedbackEntity();
		when(this.mockFeedbackEntityAdapter.toEntity(feedback)).thenReturn(feedbackEntity);
		this.repository.addFeedback(feedback);
		
		verify(this.mockH2FeedbackRepository).save(feedbackEntity);
	}
}

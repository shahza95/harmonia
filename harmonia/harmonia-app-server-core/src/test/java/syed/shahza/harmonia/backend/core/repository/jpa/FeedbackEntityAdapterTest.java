package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.core.domain.TestFeedback;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackEntityAdapterTest {
    private FeedbackEntityAdapter adapter;
    private Feedback.Builder feedbackBuilder;

    @Mock
    private LectureEntityAdapter mockLectureEntityAdapter;
    

    @Before
    public void Before() {
        this.adapter = new FeedbackEntityAdapter(this.mockLectureEntityAdapter);
        this.feedbackBuilder = TestFeedback.aValidFeedback();
    }

    @Test
    public void adaptsMessageFromDomainToEntityCorrectlyAndBackAgain() {
    	String message = "some message";
        FeedbackEntity feedbackEntity = adapter.toEntity(feedbackBuilder.message(message).build());

        assertThat(adapter.toDomain(feedbackEntity).getMessage(), is(message));
    }
    
    @Test
    public void adaptsEmotionFromDomainToEntityCorrectlyAndBackAgain() {
    	int rating = 5;
    	FeedbackEntity feedbackEntity = adapter.toEntity(feedbackBuilder.rating(rating).build());
    	
    	assertThat(adapter.toDomain(feedbackEntity).getRating(), is(rating));
    }

    @Test
    public void invokesLectureAdapterFromDomainToEntity() {
        this.adapter.toEntity(feedbackBuilder.build());
        verify(this.mockLectureEntityAdapter).toEntity(feedbackBuilder.build().getLecture());
    }
    
    @Test
    public void invokesLectureAdapterFromEntityToDomain() {
    	FeedbackEntity feedbackEntity = TestFeedbackEntity.aFeedbackEntity();
    	this.adapter.toDomain(feedbackEntity);
    	verify(this.mockLectureEntityAdapter).toDomain(feedbackEntity.getLecture());
    }
    
    @Test
    public void canAdaptListOfCommentEntitiesToCommentsObject(){
    	FeedbackEntity feedbackEntity = TestFeedbackEntity.aFeedbackEntity();
    	FeedbackEntity feedbackEntity2 = TestFeedbackEntity.aFeedbackEntity();
    	List<FeedbackEntity> feedbackEntityList = new ArrayList<FeedbackEntity>();
    	feedbackEntityList.add(feedbackEntity);
    	feedbackEntityList.add(feedbackEntity2);
    	assertThat(this.adapter.toDomain(feedbackEntityList).getFeedbackList().size(), is(2));
    }
}

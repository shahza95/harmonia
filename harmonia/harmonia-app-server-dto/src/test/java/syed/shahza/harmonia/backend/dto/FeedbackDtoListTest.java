package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FeedbackDtoListTest {
	private FeedbackDtoList feedbackDtoList;
	private FeedbackDto feedbackDto;
	
	@Before
	public void before() {
		feedbackDto = TestFeedbackDto.aValidFeedbackDto().build();
        feedbackDtoList = TestFeedbackDtoList.aFilledFeedbackDtoList(5);
	}
	
    @Test
    public void shouldBeAbleToCreateAFeedbackDtoListAndGetIt() {
        List<FeedbackDto> listFeedbackDto = feedbackDtoList.getFeedbackDtoList();
        
        assertThat(new FeedbackDtoList(listFeedbackDto).getFeedbackDtoList(), is(listFeedbackDto));
    }
    
    @Test
    public void shouldBeAbleToAddAFeedbackDtoToEmptyList() {
    	FeedbackDtoList feedbackDtoList = TestFeedbackDtoList.anEmptyFeedbackDtoList();
    	feedbackDtoList.addFeedbackDtoToList(feedbackDto);
    	
    	assertThat(feedbackDtoList.getFeedbackDtoList().get(0), is(feedbackDto));
    }
    
    @Test
    public void shouldBeAbleToAddAFeedbackDtoToExistingList() {
        feedbackDtoList.addFeedbackDtoToList(feedbackDto);

        assertThat(feedbackDtoList.getFeedbackDtoList().get(5), is(feedbackDto));
    }
}

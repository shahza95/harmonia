package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.TestCommentDtoList;
import syed.shahza.harmonia.backend.dto.TestMoodDtoList;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetAllMoodsAction;

@RunWith(MockitoJUnitRunner.class)
public class ActiveLectureRestControllerTest {
    private ActiveLectureRestController lectureController;
    private String lectureTitle;
    
    @Mock
    private GetAllCommentsAction mockGetAllCommentsAction;
    
    @Mock
    private GetAllMoodsAction mockGetAllMoodsAction;

    
    @Before
    public void before() {
    	this.lectureTitle = "someTitle";
        this.lectureController = new ActiveLectureRestController(this.mockGetAllCommentsAction, this.mockGetAllMoodsAction);
    }
    
    @Test
    public void getAllCommentsInvokesGetAllCommentsAction() {
    	this.lectureController.getAllComments(this.lectureTitle);
    	
    	Mockito.verify(this.mockGetAllCommentsAction).getAll(this.lectureTitle);
    }
    
    @Test
    public void getAllCommentsReturnsACommentDtoList() {
    	CommentDtoList commentDtoList = TestCommentDtoList.aFilledCommentDtoList(3);
    	when(this.mockGetAllCommentsAction.getAll(lectureTitle)).thenReturn(commentDtoList);
    
    	assertThat(this.lectureController.getAllComments(this.lectureTitle), is(commentDtoList));
    }
    
    @Test
    public void getMoodSummaryInvokesGetAllMoodsAction() {
    	when(this.mockGetAllMoodsAction.getAll(lectureTitle)).thenReturn(TestMoodDtoList.aFilledMoodDtoList(2));
    	this.lectureController.getMoodSummary(this.lectureTitle);
    	
    	Mockito.verify(this.mockGetAllMoodsAction).getAll(this.lectureTitle);
    }
    
    @Test
    public void getMoodSummaryReturnsACommentDtoList() {
    	int numberOfSameMood = 2;
    	when(this.mockGetAllMoodsAction.getAll(lectureTitle)).thenReturn(TestMoodDtoList.aFilledMoodDtoList(numberOfSameMood));
    	Map<String, Integer> moodMap = new HashMap<String, Integer>();
    	moodMap.put("HAPPY", numberOfSameMood);
    	moodMap.put("SAD", 0);
    	moodMap.put("CONFUSED", 0);
    	
    	assertThat(this.lectureController.getMoodSummary(this.lectureTitle), is(moodMap));
    }
}

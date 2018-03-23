package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.TestCommentDtoList;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;

@RunWith(MockitoJUnitRunner.class)
public class ActiveLectureRestControllerTest {
    private ActiveLectureRestController lectureController;
    private String lectureTitle;
    
    @Mock
    private GetAllCommentsAction mockGetAllCommentsAction;

    
    @Before
    public void before() {
    	this.lectureTitle = "someTitle";
        this.lectureController = new ActiveLectureRestController(this.mockGetAllCommentsAction);
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
}

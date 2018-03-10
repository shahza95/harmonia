package syed.shahza.harmonia.backend.endpoint.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.core.domain.TestLecture.aValidLecture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.TestComment;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.TestCommentDto;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerStudentTest {
    private LectureControllerStudent lectureController;
    private Lecture lecture;
    private CommentDto commentDto;
    private Comment comment;
    
    @Mock
    private LectureService mockLectureService;
    
    @Mock
    private LectureAdapter mockLectureAdapter;
    
    @Mock
    private CommentAdapter mockCommentAdapter;

    @Before
    public void before() {
        this.lectureController = new LectureControllerStudent(this.mockLectureService, this.mockLectureAdapter, this.mockCommentAdapter);
        lecture = aValidLecture().build();
        commentDto = TestCommentDto.aValidCommentDto().build();
        comment = TestComment.aValidComment().build();
    }
    
    @Test
    public void joinInvokesServiceWithPasswordString() {
    	String password = "somePassword";
        this.lectureController.join(password);

        verify(this.mockLectureService).join(password);
    }
    
    @Test
    public void joinInvokesAdapterToDtoForReturn() {
    	String password = "somePassword";
    	when(mockLectureService.join(password)).thenReturn(lecture);
    	this.lectureController.join(password);
    	
    	verify(this.mockLectureAdapter).toDto(lecture);
    }
    
    @Test
    public void addCommentInvokesAdapterToDomain() {
    	CommentDto commentDto = TestCommentDto.aValidCommentDto().build();
    	this.lectureController.addComment(commentDto);
    	
    	verify(this.mockCommentAdapter).toDomain(commentDto);
    }
    
    @Test
    public void addCommentInvokesLectureServiceWithComment() {
    	when(this.mockCommentAdapter.toDomain(commentDto)).thenReturn(comment);
    	this.lectureController.addComment(commentDto);
    	
    	verify(this.mockLectureService).addComment(comment);
    }
    
    @Test
    public void addCommentInvokesAdapterToDto() {
    	when(this.mockCommentAdapter.toDomain(commentDto)).thenReturn(comment);
    	when(this.mockLectureService.addComment(comment)).thenReturn(comment);
    	this.lectureController.addComment(commentDto);
    	
    	verify(this.mockCommentAdapter).toDto(comment);
    }
}
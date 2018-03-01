package syed.shahza.harmonia.backend.endpoint.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.core.domain.TestLectures.aValidLecture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerStudentTest {
    private LectureControllerStudent lectureController;
    private Lecture lecture;
    
    @Mock
    private LectureAdapter mockLectureAdapter;

    @Mock
    private LectureService mockLectureService;

    @Before
    public void before() {
        this.lectureController = new LectureControllerStudent(this.mockLectureService, this.mockLectureAdapter);
        lecture = aValidLecture().build();
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
}
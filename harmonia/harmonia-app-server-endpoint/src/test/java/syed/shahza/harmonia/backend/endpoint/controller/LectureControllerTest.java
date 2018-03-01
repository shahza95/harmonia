package syed.shahza.harmonia.backend.endpoint.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.core.domain.TestLectures.aValidLecture;
import static syed.shahza.harmonia.backend.dto.TestLectureDtos.aValidLectureDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerTest {
    private LectureController lectureController;
    private LectureDto lectureDto;
    private Lecture lecture;
    
    @Mock
    private LectureAdapter mockLectureAdapter;

    @Mock
    private LectureService mockLectureService;

    @Before
    public void before() {
        this.lectureController = new LectureController(this.mockLectureService, this.mockLectureAdapter);
        lectureDto = aValidLectureDto().build();
        lecture = aValidLecture().build();
    }

    @Test
    public void createInvokesAdapterToDomain() {
    	this.lectureController.create(lectureDto);
    	verify(this.mockLectureAdapter).toDomain(lectureDto);
    }

    @Test
    public void createInvokesServiceWithLectureDomainObject() {
    	when(mockLectureAdapter.toDomain(lectureDto)).thenReturn(lecture);
        this.lectureController.create(lectureDto);

        verify(this.mockLectureService).create(lecture);
    }
    
    @Test
    public void createInvokesAdapterToDtoForReturn() {
    	when(mockLectureAdapter.toDomain(lectureDto)).thenReturn(lecture);
    	when(mockLectureService.create(lecture)).thenReturn(lecture);
    	this.lectureController.create(lectureDto);
    	
    	verify(this.mockLectureAdapter).toDto(lecture);
    }

    @Test
    public void createReturnsLectureDto() {
    	when(mockLectureAdapter.toDomain(lectureDto)).thenReturn(lecture);
    	when(mockLectureService.create(lecture)).thenReturn(lecture);
    	when(mockLectureAdapter.toDto(lecture)).thenReturn(lectureDto);
        
        assertThat(this.lectureController.create(lectureDto), is(lectureDto));
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
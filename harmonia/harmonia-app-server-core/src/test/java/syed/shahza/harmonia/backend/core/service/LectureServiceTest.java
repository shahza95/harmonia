package syed.shahza.harmonia.backend.core.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.core.domain.TestLectures.aValidLecture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;

@RunWith(MockitoJUnitRunner.class)
public class LectureServiceTest {
	private LectureService lectureService;
	private Lecture lecture;
	
	@Mock
	private LectureRepository mockLectureRepository;
	
	@Before
	public void before() {
		lecture = aValidLecture().build();
		this.lectureService = new LectureService(this.mockLectureRepository);
	}
	
    @Test
    public void createInvokesLecturerRepository() {
    	lectureService.create(lecture);
    	verify(this.mockLectureRepository).create(lecture);
    }
	
    @Test
    public void createReturnsCorrectObjectIfSuccess() {
        when(this.mockLectureRepository.create(lecture)).thenReturn(lecture);

        assertThat(lectureService.create(lecture), is(lecture));
    }
}
package syed.shahza.harmonia.backend.core.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestLectures.aValidLecture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Lecture;

@RunWith(MockitoJUnitRunner.class)
public class LectureRepositoryTest {
	private LectureRepository lectureRepository;
	
    @Test
    public void createReturnsAddedLecture() {
    	this.lectureRepository = new LectureRepository();
        Lecture lecture = aValidLecture().build();

        assertThat(lectureRepository.create(lecture), is(lecture));
    }
}
package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.TestLecture;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;

@RunWith(MockitoJUnitRunner.class)
public class JpaLectureRepositoryTest {
	private JpaLectureRepository repository;
	private Lecture lecture;
	private LectureEntity lectureEntity;
	
	@Mock
	private H2LectureRepository mockH2LectureRepository;
	
	@Mock
	private LectureEntityAdapter mockLectureEntityAdapter;
	
	@Before
	public void setUp() {
		lecture = TestLecture.aValidLecture().build();
		lectureEntity = TestLectureEntity.aLectureEntity();
		repository = new JpaLectureRepository(this.mockH2LectureRepository, this.mockLectureEntityAdapter);
	}
	
	@Test
	public void createShouldInvokeEntityAdapter() {
		this.repository.create(lecture);
		
		verify(this.mockLectureEntityAdapter).toEntity(lecture);
	}
	
	@Test
	public void createShouldInvokeLecturerRepository() {
		LectureEntity lectureEntity = TestLectureEntity.aLectureEntity();
		when(this.mockLectureEntityAdapter.toEntity(lecture)).thenReturn(lectureEntity);
		this.repository.create(lecture);
		
		verify(this.mockH2LectureRepository).save(lectureEntity);
	}
	
	@Test
	public void retrieveLectureFromPasswordShouldInvokeLecturerRepository() {
		this.repository.retrieveLectureFromPassword(lecture.getPassword());
		
		verify(this.mockH2LectureRepository).findByPassword(lecture.getPassword());
	}
	
	@Test
	public void retrieveLectureFromPasswordShouldInvokeEntityAdapter() {
		when(this.mockH2LectureRepository.findByPassword(lecture.getPassword())).thenReturn(lectureEntity);
		this.repository.retrieveLectureFromPassword(lecture.getPassword());
		
		verify(this.mockLectureEntityAdapter).toDomain(lectureEntity);
	}
	
	@Test
	public void retrieveLectureFromTitleShouldInvokeLecturerRepository() {
		this.repository.retrieveLectureFromTitle(lecture.getTitle());
		
		verify(this.mockH2LectureRepository).findByTitle(lecture.getTitle());
	}
	
	@Test
	public void retrieveLectureFromTitleShouldInvokeEntityAdapter() {
		when(this.mockH2LectureRepository.findByTitle(lecture.getTitle())).thenReturn(lectureEntity);
		this.repository.retrieveLectureFromTitle(lecture.getTitle());
		
		verify(this.mockLectureEntityAdapter).toDomain(lectureEntity);
	}
}

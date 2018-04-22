package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.TestMood;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2MoodRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;

@RunWith(MockitoJUnitRunner.class)
public class JpaMoodRepositoryTest {
	private JpaMoodRepository repository;
	private Mood mood;
	
	@Mock
	private H2MoodRepository mockH2MoodRepository;
	
	@Mock
	private H2LectureRepository mockH2LectureRepository;
	
	@Mock
	private MoodEntityAdapter mockMoodEntityAdapter;
	
	@Before
	public void setUp() {
		mood = TestMood.aValidMood().build();
		repository = new JpaMoodRepository(this.mockH2MoodRepository, this.mockMoodEntityAdapter, this.mockH2LectureRepository);
	}
	
	@Test
	public void getAllMoodsShouldInvokeMoodRepository() {
		this.repository.getAllMoods("lectureTitle");
		
		verify(this.mockH2MoodRepository).findByLectureTitle("lectureTitle");
	}
	
	@Test
	public void getAllMoodsShouldInvokeEntityAdapter() {
		String title = "lectureTitle";
		List<MoodEntity> moodEntityList = new ArrayList<MoodEntity>();
		when(this.mockH2MoodRepository.findByLectureTitle(title)).thenReturn(moodEntityList);
		this.repository.getAllMoods(title);
		
		verify(this.mockMoodEntityAdapter).toDomain(moodEntityList);
	}
	
	@Test
	public void addMoodShouldInvokeEntityAdapter() {
		when(this.mockMoodEntityAdapter.toEntity(mood)).thenReturn(TestMoodEntity.aMoodEntity());
		this.repository.addMood(mood);
		
		verify(this.mockMoodEntityAdapter).toEntity(mood);
	}
	
	@Test
	public void addMoodShouldInvokeMoodRepository() {
		MoodEntity moodEntity = TestMoodEntity.aMoodEntity();
		when(this.mockMoodEntityAdapter.toEntity(mood)).thenReturn(moodEntity);
		this.repository.addMood(mood);
		
		verify(this.mockH2MoodRepository).save(moodEntity);
	}
	
	@Test
	public void removeMoodShouldInvokeMoodRepositoryFind() {
		String title = "title";
		String emoji = ":D";
		this.repository.removeMood(title, emoji);
		
		verify(this.mockH2MoodRepository).findByTitleAndEmoji(title, emoji);
	}
	
	@Test
	public void removeMoodShouldInvokeMoodRepositoryDelete() {
		MoodEntity moodEntity = TestMoodEntity.aMoodEntity();
		when(this.mockH2MoodRepository.findByTitleAndEmoji("title", "emoji")).thenReturn(moodEntity);
		this.repository.removeMood("title", "emoji");
		
		verify(this.mockH2MoodRepository).delete(moodEntity);
	}
}

package syed.shahza.harmonia.backend.endpoint.adapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestMood.aValidMood;
import static syed.shahza.harmonia.backend.core.domain.TestMoods.aFilledMoodsList;
import static syed.shahza.harmonia.backend.dto.TestMoodDto.aValidMoodDto;
import static syed.shahza.harmonia.backend.dto.TestMoodDtoList.aFilledMoodDtoList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Emotion;
import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Moods;
import syed.shahza.harmonia.backend.dto.EmotionDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.MoodDtoList;

@RunWith(MockitoJUnitRunner.class)
public class MoodAdapterTest {
    private MoodAdapter moodAdapter;
    
    @Mock
    private LectureAdapter mockLectureAdapter;

    @Before
    public void before() {
        this.moodAdapter = new MoodAdapter(this.mockLectureAdapter);
    }

    @Test
    public void canAdaptMoodMessageToDto() {
        assertThat(this.moodAdapter.toDto(aValidMood().emoji(":S").build()).getEmoji(), is(":S"));
    }

    @Test
    public void canAdaptMoodMessageToDomain() {
        assertThat(this.moodAdapter.toDomain(aValidMoodDto().emoji(":D").build()).getEmoji(), is(":D"));
    }
    
    @Test
    public void canAdaptMoodEmotionToDto() {
    	assertThat(this.moodAdapter.toDto(aValidMood().emotion(Emotion.CONFUSED).build()).getEmotionDto(), is(EmotionDto.CONFUSED));
    }
    
    @Test
    public void canAdaptMoodEmotionDtoToDomain() {
    	assertThat(this.moodAdapter.toDomain(aValidMoodDto().emotionDto(EmotionDto.SAD).build()).getEmotion(), is(Emotion.SAD));
    }
    
    @Test
    public void toDtoInvokesLectureAdapterToDto() {
    	Mood mood = aValidMood().build();
    	this.moodAdapter.toDto(mood);
    	Mockito.verify(this.mockLectureAdapter).toDto(mood.getLecture());
    }
    
    @Test
    public void toDomainInvokesLectureAdapterToDomain() {
    	MoodDto moodDto = aValidMoodDto().build();
    	this.moodAdapter.toDomain(moodDto);
    	Mockito.verify(this.mockLectureAdapter).toDomain(moodDto.getLectureDto());
    }

    @Test
    public void canAdaptMoodsToMoodDtoList() {
    	Moods moods = aFilledMoodsList(2);
    	MoodDtoList moodDtoList = this.moodAdapter.toDto(moods);
        assertThat(moodDtoList.getMoodDtoList().size(), is(moods.getMoodList().size()));
    }
    
    @Test
    public void canAdaptMoodDtoListToMoods() {
    	MoodDtoList moodDtoList = aFilledMoodDtoList(3);
    	Moods moods = this.moodAdapter.toDomain(moodDtoList);
    	assertThat(moods.getMoodList().size(), is(moodDtoList.getMoodDtoList().size()));
    }
}
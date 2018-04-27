package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Emotion;
import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.TestMood;

@RunWith(MockitoJUnitRunner.class)
public class MoodEntityAdapterTest {
    private MoodEntityAdapter adapter;
    private Mood.Builder moodBuilder;

    @Mock
    private LectureEntityAdapter mockLectureEntityAdapter;
    

    @Before
    public void Before() {
        this.adapter = new MoodEntityAdapter(this.mockLectureEntityAdapter);
        this.moodBuilder = TestMood.aValidMood();
    }

    @Test
    public void adaptsEmojiFromDomainToEntityCorrectlyAndBackAgain() {
    	String emoji = "some emoji";
        MoodEntity moodEntity = adapter.toEntity(moodBuilder.emoji(emoji).build());

        assertThat(adapter.toDomain(moodEntity).getEmoji(), is(emoji));
    }
    
    @Test
    public void adaptsEmotionFromDomainToEntityCorrectlyAndBackAgain() {
    	Emotion emotion = Emotion.CONFUSED;
    	MoodEntity moodEntity = adapter.toEntity(moodBuilder.emotion(emotion).build());
    	
    	assertThat(adapter.toDomain(moodEntity).getEmotion(), is(emotion));
    }

    @Test
    public void invokesLectureAdapterFromDomainToEntity() {
        this.adapter.toEntity(moodBuilder.build());
        verify(this.mockLectureEntityAdapter).toEntity(moodBuilder.build().getLecture());
    }
    
    @Test
    public void invokesLectureAdapterFromEntityToDomain() {
    	MoodEntity moodEntity = TestMoodEntity.aMoodEntity();
    	this.adapter.toDomain(moodEntity);
    	verify(this.mockLectureEntityAdapter).toDomain(moodEntity.getLecture());
    }
    
    @Test
    public void canAdaptListOfCommentEntitiesToCommentsObject(){
    	MoodEntity moodEntity = TestMoodEntity.aMoodEntity();
    	MoodEntity moodEntity2 = TestMoodEntity.aMoodEntity();
    	List<MoodEntity> moodEntityList = new ArrayList<MoodEntity>();
    	moodEntityList.add(moodEntity);
    	moodEntityList.add(moodEntity2);
    	assertThat(this.adapter.toDomain(moodEntityList).getMoodList().size(), is(2));
    }
}

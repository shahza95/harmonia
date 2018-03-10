package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MoodDtoListTest {
	private MoodDtoList moodDtoList;
	private MoodDto moodDto;
	
	@Before
	public void before() {
		moodDto = TestMoodDto.aValidMoodDto().build();
        moodDtoList = TestMoodDtoList.aFilledMoodDtoList(5);
	}
	
    @Test
    public void shouldBeAbleToCreateAMoodDtoListAndGetIt() {
        List<MoodDto> listMoodDto = moodDtoList.getMoodDtoList();
        
        assertThat(new MoodDtoList(listMoodDto).getMoodDtoList(), is(listMoodDto));
    }
    
    @Test
    public void shouldBeAbleToAddAMoodDtoToEmptyList() {
    	MoodDtoList moodDtoList = TestMoodDtoList.anEmptyMoodDtoList();
    	moodDtoList.addMoodDtoToList(moodDto);
    	
    	assertThat(moodDtoList.getMoodDtoList().get(0), is(moodDto));
    }
    
    @Test
    public void shouldBeAbleToAddAMoodDtoToExistingList() {
        moodDtoList.addMoodDtoToList(moodDto);

        assertThat(moodDtoList.getMoodDtoList().get(5), is(moodDto));
    }
}

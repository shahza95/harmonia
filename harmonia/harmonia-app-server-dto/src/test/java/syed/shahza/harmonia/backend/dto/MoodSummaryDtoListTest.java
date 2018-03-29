package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MoodSummaryDtoListTest {
	private MoodSummaryDtoList moodSummaryDtoList;
	private MoodSummaryDto moodSummaryDto;

	@Before
	public void before() {
		moodSummaryDto = TestMoodSummaryDto.aValidMoodSummaryDto().build();
		moodSummaryDtoList = TestMoodSummaryDtoList.aFilledMoodSummaryDtoList();
	}

	@Test
	public void shouldBeAbleToCreateAMoodDtoListAndGetIt() {
		List<MoodSummaryDto> listMoodSummaryDto = moodSummaryDtoList.getMoodSummaryDtoList();

		assertThat(new MoodSummaryDtoList(listMoodSummaryDto).getMoodSummaryDtoList(),
				is(listMoodSummaryDto));
	}

	@Test
	public void shouldBeAbleToAddAMoodDtoToEmptyList() {
		MoodSummaryDtoList moodSummaryDtoList = TestMoodSummaryDtoList.anEmptyMoodSummaryDtoList();
		moodSummaryDtoList.addMoodSummaryDtoToList(moodSummaryDto);

		assertThat(moodSummaryDtoList.getMoodSummaryDtoList().get(0), is(moodSummaryDto));
	}

	@Test
	public void shouldBeAbleToAddAMoodDtoToExistingList() {
		moodSummaryDtoList.addMoodSummaryDtoToList(moodSummaryDto);

		assertThat(moodSummaryDtoList.getMoodSummaryDtoList().get(3), is(moodSummaryDto));
	}
}

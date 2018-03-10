package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;


public class MoodsTest {

    @Test
    public void shouldBeAbleToCreateMoodsAndGetMoodListBack() {
    	List<Mood> listMood = TestMoods.aFilledMoodsList(5).getMoodList();
    	Moods moods = Moods.aMoodListBuilder().moodList(listMood).build();

        assertThat(moods.getMoodList(), is(listMood));
    }
}

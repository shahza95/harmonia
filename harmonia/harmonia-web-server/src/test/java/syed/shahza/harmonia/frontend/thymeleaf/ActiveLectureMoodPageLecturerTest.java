package syed.shahza.harmonia.frontend.thymeleaf;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDtoList;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.backend.dto.TestMoodDtoList;

import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;


public class ActiveLectureMoodPageLecturerTest extends ThymeleafTemplateTest {
    private HtmlElements tags;
    private LectureDto lectureDto;
    private MoodDtoList moodDtoList;
    
    public ActiveLectureMoodPageLecturerTest() {
        super("/lecturer/activeLectureMood");
    }
    
    @Before
    public void setUp() {
        Map<String, Object> model = new HashMap<>();
        this.lectureDto = TestLectureDto.anActiveLectureDto().build();
        this.moodDtoList = TestMoodDtoList.aFilledMoodDtoList(2);
        model.put("lectureDto", this.lectureDto);
        model.put("moodDtoList", this.moodDtoList);
        this.tags = process(model);
    }
    
    @Test
    public void dashboardShouldBeInjected() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(0).text(), is("Harmonia"));
    }
    
    @Test
    public void correctModelShouldFillTitle() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(1).text(), is(lectureDto.getTitle() + ": Mood"));
    }
    
    @Test
    public void canvasForChartShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("canvas").size(), is(1));
    }
    
    @Test
    public void shouldIncludeChartJsScript() throws NodeSelectorException {
    	assertThat(this.tags.matching("script").get(0).attr("src"), containsString("Chart.min.js"));
    }
    
    @Test
    public void shouldCallMoodChartJavascript() throws NodeSelectorException {
    	assertThat(this.tags.matching("script").get(3).attr("src"), is("/resources/javascript/MoodChart.js"));
    }
}

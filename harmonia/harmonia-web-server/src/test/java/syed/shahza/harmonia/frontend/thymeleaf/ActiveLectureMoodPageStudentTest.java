package syed.shahza.harmonia.frontend.thymeleaf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestLectureDto;

import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;


public class ActiveLectureMoodPageStudentTest extends ThymeleafTemplateTest {
    private HtmlElements tags;
    private LectureDto lectureDto;
    
    public ActiveLectureMoodPageStudentTest() {
        super("/student/activeLectureMood");
    }
    
    @Before
    public void setUp() {
        Map<String, Object> model = new HashMap<>();
        this.lectureDto = TestLectureDto.anActiveLectureDto().build();
        model.put("lectureDto", this.lectureDto);
        this.tags = process(model);
    }
    
    @Test
    public void dashboardShouldBeInjected() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(0).text(), is("Harmonia"));
    }
    
    @Test
    public void onlyOneButtonShouldExist() throws NodeSelectorException {
    	//Input for current emoji counts as an input too!
    	assertThat(this.tags.matching("input").size(), is(2));
    }
    
    @Test
    public void oneSelectDropDownMenuShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("select").size(), is(1));
    }
    
    @Test
    public void sevenOptionsShouldExistForDropDown() throws NodeSelectorException {
    	//Includes 'choose your mood' placeholder
    	assertThat(this.tags.matching("option").size(), is(7));
    }
    
    @Test
    public void scriptForButtonEnablingShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("script").size(), is(5));
    }
    
    @Test
    public void correctModelShouldFillTitle() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(1).text(), is(lectureDto.getTitle()));
    }
    
    @Test
    public void shouldCallCheckMoodEnabledJavascript() throws NodeSelectorException {
    	assertThat(this.tags.matching("script").get(3).attr("src"), is("/resources/javascript/CheckMoodEnabled.js"));
    }
}

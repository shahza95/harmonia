package syed.shahza.harmonia.frontend.thymeleaf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import syed.shahza.harmonia.backend.dto.LectureDto;

import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;


public class ViewLecturePageTest extends ThymeleafTemplateTest {
    private HtmlElements tags;
    private LectureDto lectureDto;

    public ViewLecturePageTest() {
        super("/viewLecture");
    }
    
    @Before
    public void setUp() {
        Map<String, Object> model = new HashMap<>();
        this.lectureDto = aValidLectureDto().build();
        model.put("lectureDto", this.lectureDto);
        this.tags = process(model);
    }
    
    @Test
    public void onlyFiveInputFieldsShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("input").size(), is(5));
    }
    
    @Test
    public void allInputsShouldBeReadOnly() throws NodeSelectorException {
    	this.tags.matching("input").forEach(input -> assertTrue(input.attr("readonly").equals("readonly")));
    }
    
    @Test
    public void titleInputShouldDisplayTitle() throws NodeSelectorException {
    	assertThat(this.tags.matching("input").get(0).attr("value"), is(lectureDto.getTitle()));
    }
    
    @Test
    public void titleInputShouldDisplayPassword() throws NodeSelectorException {
    	assertThat(this.tags.matching("input").get(1).attr("value"), is(lectureDto.getPassword()));
    }
    
    @Test
    public void titleInputShouldDisplayDate() throws NodeSelectorException {
    	assertThat(this.tags.matching("input").get(2).attr("value"), is(lectureDto.getDate().toString()));
    }
    
    @Test
    public void titleInputShouldDisplayStartTime() throws NodeSelectorException {
    	assertThat(this.tags.matching("input").get(3).attr("value"), is(lectureDto.getStartTime().toString()));
    }
    
    @Test
    public void titleInputShouldDisplayEndTime() throws NodeSelectorException {
    	assertThat(this.tags.matching("input").get(4).attr("value"), is(lectureDto.getEndTime().toString()));
    }
}
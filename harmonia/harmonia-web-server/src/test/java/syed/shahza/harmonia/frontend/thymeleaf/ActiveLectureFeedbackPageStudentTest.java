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


public class ActiveLectureFeedbackPageStudentTest extends ThymeleafTemplateTest {
    private HtmlElements tags;
    private LectureDto lectureDto;
    
    public ActiveLectureFeedbackPageStudentTest() {
        super("/student/activeLectureFeedback");
    }
    
    @Before
    public void setUp() {
        Map<String, Object> model = new HashMap<>();
        this.lectureDto = TestLectureDto.anActiveLectureDto().build();
        model.put("lectureDto", this.lectureDto);
        this.tags = process(model);
    }
    
    @Test
    public void onlySubmitButtonAndFiveStarRadioButtonsShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("input").size(), is(6));
    }
    
    @Test
    public void correctModelShouldFillTitle() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(0).text(), is(this.lectureDto.getTitle()));
    }
    
    @Test
    public void textboxForCommentMessageShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("textarea").size(), is(1));
    }

}

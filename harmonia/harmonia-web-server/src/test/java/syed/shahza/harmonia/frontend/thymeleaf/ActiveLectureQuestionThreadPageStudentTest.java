package syed.shahza.harmonia.frontend.thymeleaf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.backend.dto.TestQuestionDto;

import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;


public class ActiveLectureQuestionThreadPageStudentTest extends ThymeleafTemplateTest {
    private HtmlElements tags;
    private LectureDto lectureDto;
    private QuestionDto questionDto;
    private Map<String, Object> model;
    
    public ActiveLectureQuestionThreadPageStudentTest() {
        super("/student/activeLectureQuestionThread");
    }
    
    @Before
    public void setUp() {
        this.model = new HashMap<>();
        this.lectureDto = TestLectureDto.anActiveLectureDto().build();
        this.questionDto = TestQuestionDto.aValidQuestionDto().build();
        this.model.put("lectureDto", this.lectureDto);
        this.model.put("questionDto", this.questionDto);
        this.tags = process(model);
    }
    
    @Test
    public void dashboardShouldBeInjected() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(0).text(), is("Harmonia"));
    }
    
    @Test
    public void correctModelShouldFillTitle() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(1).text(), is(lectureDto.getTitle()));
    }

    @Test
    public void correctModelShouldFillQuestion() throws NodeSelectorException {
    	assertThat(this.tags.matching("p").get(0).text(), is(questionDto.getQuestion()));
    }
    
    @Test
    public void correctModelShouldFillAnswer() throws NodeSelectorException {
    	assertThat(this.tags.matching("p").get(1).text(), is(questionDto.getAnswer()));
    }
    
    @Test
    public void noAnswerDivIfThereIsNoAnswer() throws NodeSelectorException {
    	questionDto.setAnswer(null);
    	this.model.put("questionDto", questionDto);
    	this.tags = process(model);
    	assertThat(this.tags.matching("p").size(), is(1));
    }
}

package syed.shahza.harmonia.frontend.thymeleaf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.QuestionDtoList;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.backend.dto.TestQuestionDtoList;

import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;


public class ActiveLectureQuestionsPageStudentTest extends ThymeleafTemplateTest {
    private HtmlElements tags;
    private LectureDto lectureDto;
    private QuestionDtoList questionDtoList;
    
    public ActiveLectureQuestionsPageStudentTest() {
        super("/student/activeLectureQuestions");
    }
    
    @Before
    public void setUp() {
        Map<String, Object> model = new HashMap<>();
        this.lectureDto = TestLectureDto.anActiveLectureDto().build();
        this.questionDtoList = TestQuestionDtoList.aFilledQuestionDtoList(2);
        model.put("lectureDto", this.lectureDto);
        model.put("questionDtoList", this.questionDtoList);
        this.tags = process(model);
    }
    
    @Test
    public void dashboardShouldBeInjected() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(0).text(), is("Harmonia"));
    }
    
    @Test
    public void only1ButtonShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("input").size(), is(1));
    }
    
    @Test
    public void only1TextareaShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("textarea").size(), is(1));
    }
    
    @Test
    public void correctModelShouldFillTitle() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(1).text(), is(lectureDto.getTitle()));
    }
    
    @Test
    public void oneTableForQuestionsShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("table").size(), is(1));
    }
    
    @Test
    public void tableShouldHaveCorrectNumberOfCells() throws NodeSelectorException {
    	assertThat(this.tags.matching("td").size(), is(questionDtoList.getQuestionDtoList().size()));
    }
    
    @Test
    public void shouldCallCheckQuestionsEnabledJavascript() throws NodeSelectorException {
    	assertThat(this.tags.matching("script").get(3).attr("src"), is("/resources/javascript/CheckQuestionsEnabled.js"));
    }
}

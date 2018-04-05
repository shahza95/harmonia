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


public class ActiveLectureQuestionsPageLecturerTest extends ThymeleafTemplateTest {
    private HtmlElements tags;
    private LectureDto lectureDto;
    private QuestionDtoList questionDtoList;
    
    public ActiveLectureQuestionsPageLecturerTest() {
        super("/lecturer/activeLectureQuestions");
    }
    
    @Before
    public void setUp() {
        Map<String, Object> model = new HashMap<>();
        this.questionDtoList = TestQuestionDtoList.aFilledQuestionDtoList(2);
        this.lectureDto = TestLectureDto.anActiveLectureDto().build();
        model.put("lectureDto", this.lectureDto);
        model.put("questionDtoList", this.questionDtoList);
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
    public void oneTableForQuestionsShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("table").size(), is(1));
    }
    
    @Test
    public void tableShouldHaveCorrectNumberOfCells() throws NodeSelectorException {
    	assertThat(this.tags.matching("td").size(), is(questionDtoList.getQuestionDtoList().size()));
    }
    
    @Test
    public void shouldHaveAsManyLinksAsThereAreQuestions() throws NodeSelectorException {
    	// Have to add on dashboard's shared links
    	assertThat(this.tags.matching("a").size(), is(questionDtoList.getQuestionDtoList().size() + 3));
    }
}

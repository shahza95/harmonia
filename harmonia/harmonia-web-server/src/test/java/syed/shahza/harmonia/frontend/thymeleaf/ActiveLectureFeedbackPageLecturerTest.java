package syed.shahza.harmonia.frontend.thymeleaf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.FeedbackDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestFeedbackDtoList;
import syed.shahza.harmonia.backend.dto.TestLectureDto;

import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;


public class ActiveLectureFeedbackPageLecturerTest extends ThymeleafTemplateTest {
    private HtmlElements tags;
    private LectureDto lectureDto;
    private FeedbackDtoList feedbackDtoList;
    private double averageRating;
    private Map<String, Object> model;
    
    public ActiveLectureFeedbackPageLecturerTest() {
        super("/lecturer/activeLectureFeedback");
    }
    
    @Before
    public void setUp() {
        this.model = new HashMap<>();
        this.lectureDto = TestLectureDto.anActiveLectureDto().build();
        this.feedbackDtoList = TestFeedbackDtoList.aFilledFeedbackDtoList(2);
        this.averageRating = this.feedbackDtoList.getFeedbackDtoList().stream().mapToDouble(FeedbackDto::getRating).average().getAsDouble();
        this.model.put("lectureDto", this.lectureDto);
        this.model.put("feedbackDtoList", this.feedbackDtoList);
        this.model.put("averageRating", averageRating);
        this.tags = process(this.model);
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
    public void correctModelShouldFillRating() throws NodeSelectorException {
    	assertThat(this.tags.matching("h3").get(0).text(), is(String.valueOf(averageRating)));
    }
    
    @Test
    public void oneButtonForFeatureTogglingShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("input").size(), is(1));
    }
    
    @Test
    public void buttonShouldDisplayDisableIfFeedbackEnabledIsTrue() throws NodeSelectorException {
    	assertThat(this.lectureDto.getFeedbackEnabled(), is(true));
    	assertThat(this.tags.matching("input").get(0).attr("value"), is("Disable"));
    }
    
    @Test
    public void buttonShouldDisplayEnableIfFeedbackEnabledIsFalse() throws NodeSelectorException {
    	this.lectureDto.setFeedbackEnabled(false);
    	this.model.put("lectureDto", this.lectureDto);
    	this.tags = process(this.model);
    	
    	assertThat(this.tags.matching("input").get(0).attr("value"), is("Enable"));
    }
    
    @Test
    public void oneTableForCommentsShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("table").size(), is(1));
    }
    
    @Test
    public void tableShouldHaveCorrectNumberOfRows() throws NodeSelectorException {
    	assertThat(this.tags.matching("tr").size(), is(feedbackDtoList.getFeedbackDtoList().size()));
    }
    
    @Test
    public void correctModelShouldFillTable() throws NodeSelectorException {
    	for(int i=0; i<feedbackDtoList.getFeedbackDtoList().size(); i++) {
    		assertThat(this.tags.matching("td").get(i).text(), is(feedbackDtoList.getFeedbackDtoList().get(i).getMessage()));
    	}
    }
}

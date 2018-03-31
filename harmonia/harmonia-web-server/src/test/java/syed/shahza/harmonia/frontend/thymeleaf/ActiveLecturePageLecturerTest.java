package syed.shahza.harmonia.frontend.thymeleaf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestCommentDtoList;
import syed.shahza.harmonia.backend.dto.TestLectureDto;

import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;


public class ActiveLecturePageLecturerTest extends ThymeleafTemplateTest {
    private HtmlElements tags;
    private LectureDto lectureDto;
    private CommentDtoList commentDtoList;
    private Map<String, Object> model;
    
    public ActiveLecturePageLecturerTest() {
        super("/lecturer/activeLecture");
    }
    
    @Before
    public void setUp() {
        this.model = new HashMap<>();
        this.lectureDto = TestLectureDto.anActiveLectureDto().build();
        this.commentDtoList = TestCommentDtoList.aFilledCommentDtoList(2);
        this.model.put("lectureDto", this.lectureDto);
        this.model.put("commentDtoList", this.commentDtoList);
        this.tags = process(this.model);
    }
    
    @Test
    public void dashboardShouldBeInjected() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(0).text(), is("Harmonia"));
    }
    
    @Test
    public void oneTableForCommentsShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("table").size(), is(1));
    }
    
    @Test
    public void correctModelShouldFillTitle() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(1).text(), is(lectureDto.getTitle()));
    }
    
    @Test
    public void tableShouldHaveCorrectNumberOfRows() throws NodeSelectorException {
    	assertThat(this.tags.matching("tr").size(), is(commentDtoList.getCommentDtoList().size()));
    }
    
    @Test
    public void correctModelShouldFillTable() throws NodeSelectorException {
    	for(int i=0; i<commentDtoList.getCommentDtoList().size(); i++) {
    		assertThat(this.tags.matching("td").get(i).text(), is(commentDtoList.getCommentDtoList().get(i).getMessage()));
    	}
    }
    
    @Test
    public void shouldCallUpdatecommentsJavascript() throws NodeSelectorException {
    	assertThat(this.tags.matching("script").get(3).attr("src"), is("/resources/javascript/UpdateComments.js"));
    }
    
    @Test
    public void oneButtonForFeatureTogglingShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("input").size(), is(1));
    }
    
    @Test
    public void buttonShouldDisplayDisableIfCommentsEnabledIsTrue() throws NodeSelectorException {
    	assertThat(this.lectureDto.getCommentsEnabled(), is(true));
    	assertThat(this.tags.matching("input").get(0).attr("value"), is("Disable"));
    }
    
    @Test
    public void buttonShouldDisplayEnableIfCommentsEnabledIsFalse() throws NodeSelectorException {
    	this.lectureDto.setCommentsEnabled(false);
    	this.model.put("lectureDto", this.lectureDto);
    	this.tags = process(this.model);
    	
    	assertThat(this.tags.matching("input").get(0).attr("value"), is("Enable"));
    }
}

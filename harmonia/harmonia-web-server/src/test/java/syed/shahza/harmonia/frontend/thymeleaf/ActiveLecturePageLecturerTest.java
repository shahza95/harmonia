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
    
    public ActiveLecturePageLecturerTest() {
        super("/lecturer/activeLecture");
    }
    
    @Before
    public void setUp() {
        Map<String, Object> model = new HashMap<>();
        this.lectureDto = TestLectureDto.anActiveLectureDto().build();
        this.commentDtoList = TestCommentDtoList.aFilledCommentDtoList(2);
        model.put("lectureDto", this.lectureDto);
        model.put("commentDtoList", this.commentDtoList);
        this.tags = process(model);
    }
    
    @Test
    public void only3ButtonsShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("input").size(), is(3));
    }
    
    @Test
    public void oneTableForCommentsShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("table").size(), is(1));
    }
    
    @Test
    public void correctModelShouldFillTitle() throws NodeSelectorException {
    	assertThat(this.tags.matching("h1").get(0).text(), is(lectureDto.getTitle()));
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
    public void shouldRefreshEvery5Seconds() throws NodeSelectorException {
    	assertThat(this.tags.matching("meta").get(0).attr("http-equiv"), is("refresh"));
    	assertThat(this.tags.matching("meta").get(0).attr("content"), is("5"));
    }
}

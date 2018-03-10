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


public class ActiveLecturePageStudentTest extends ThymeleafTemplateTest {
    private HtmlElements tags;
    private LectureDto lectureDto;
    private CommentDtoList commentDtoList;
    
    public ActiveLecturePageStudentTest() {
        super("/student/activeLecture");
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
    public void only5InputsShouldExist() throws NodeSelectorException {
    	//4 buttons + 1 text input
    	assertThat(this.tags.matching("input").size(), is(5));
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
}

package syed.shahza.harmonia.frontend.thymeleaf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;


public class JoinLecturePageTest extends ThymeleafTemplateTest {
    private HtmlElements tags;

    public JoinLecturePageTest() {
        super("/student/joinLecture");
    }
    
    @Before
    public void setUp() {
        this.tags = process();
    }
    
    @Test
    public void onlyTwoInputFieldsShouldExist() throws NodeSelectorException {
    	//1 input field + button (counts as input)
    	assertThat(this.tags.matching("input").size(), is(2));
    }
    
    @Test
    public void passwordLabelShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("label").get(0).text(), is("Password"));
    }

    @Test
    public void joinButtonShouldExist() throws NodeSelectorException {
        assertThat(this.tags.matching("input").get(1).attr("value"), is("Join"));
    }

    @Test
    public void joinButtonShouldUsePostRequest() throws NodeSelectorException {
        assertThat(this.tags.matching("form").get(0).attr("method"), is("post"));
    }
}

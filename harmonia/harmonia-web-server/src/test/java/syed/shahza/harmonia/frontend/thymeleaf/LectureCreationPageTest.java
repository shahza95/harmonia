package syed.shahza.harmonia.frontend.thymeleaf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;


public class LectureCreationPageTest extends ThymeleafTemplateTest {
    private HtmlElements tags;

    public LectureCreationPageTest() {
        super("/lectureCreation");
    }
    
    @Before
    public void setUp() {
        this.tags = process();
    }
    
    @Test
    public void onlySixInputFieldsShouldExist() throws NodeSelectorException {
    	//5 input fields + button (counts as input)
    	assertThat(this.tags.matching("input").size(), is(6));
    }
    
    @Test
    public void titleLabelShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("label").get(0).text(), is("Title"));
    }
    
    @Test
    public void passwordLabelShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("label").get(1).text(), is("Password"));
    }
    
    @Test
    public void dateLabelShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("label").get(2).text(), is("Date"));
    }
    
    @Test
    public void startTimeLabelShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("label").get(3).text(), is("Start Time"));
    }
    
    @Test
    public void endTimeLabelShouldExist() throws NodeSelectorException {
    	assertThat(this.tags.matching("label").get(4).text(), is("End Time"));
    }

    @Test
    public void createButtonShouldExist() throws NodeSelectorException {
        assertThat(this.tags.matching("input").get(5).attr("value"), is("Create"));
    }

    @Test
    public void loginButtonShouldUsePostRequest() throws NodeSelectorException {
        assertThat(this.tags.matching("form").get(0).attr("method"), is("post"));
    }
}
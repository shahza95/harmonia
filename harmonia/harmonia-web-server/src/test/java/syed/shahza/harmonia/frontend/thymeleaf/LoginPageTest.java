package syed.shahza.harmonia.frontend.thymeleaf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;


public class LoginPageTest extends ThymeleafTemplateTest {
    private HtmlElements tags;

    public LoginPageTest() {
        super("/login");
    }
    
    @Before
    public void setUp() {
        this.tags = process();
    }

    @Test
    public void loginButtonShouldExist() throws NodeSelectorException {
        assertThat(this.tags.matching("input").get(2).attr("value"), is("Login"));
    }

    @Test
    public void loginButtonShouldUsePostRequest() throws NodeSelectorException {
        assertThat(this.tags.matching("form").get(0).attr("method"), is("post"));
    }
}

package syed.shahza.harmonia.frontend.thymeleaf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;

public class DashboardFragmentTest extends ThymeleafTemplateTest {
	private HtmlElements tags;

	public DashboardFragmentTest() {
		super("/shared/fragment/dashboard");
	}

	@Before
	public void setUp() {
		this.tags = process();
	}

	@Test
	public void only3ButtonsShouldExist() throws NodeSelectorException {
		assertThat(this.tags.matching("a").size(), is(3));
	}

	@Test
	public void moodButtonShouldRedirectToActiveLectureMoodPage() throws NodeSelectorException {
		assertThat(this.tags.matching("a").get(2).attr("href"), is("mood"));
	}
	
	@Test
	public void commentsButtonShouldRedirectToActiveLecturePage() throws NodeSelectorException {
		assertThat(this.tags.matching("a").get(0).attr("href"), is("comments"));
	}
	
	@Test
	public void projectNameShouldAppear() throws NodeSelectorException {
		assertThat(this.tags.matching("h1").get(0).text(), is("Harmonia"));
	}
	
	@Test
	public void burgerIconShouldExistReadyToAppear() throws NodeSelectorException {
		assertThat(this.tags.matching("button").size(), is(1));
	}
}
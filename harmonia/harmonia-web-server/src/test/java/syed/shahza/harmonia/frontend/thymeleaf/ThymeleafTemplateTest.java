package syed.shahza.harmonia.frontend.thymeleaf;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import syed.shahza.harmonia.frontend.configuration.ThymeleafTestConfiguration;

import com.connect_group.thymeleaf.testing.ThymeleafTestEngine;
import com.connect_group.thymesheet.css.selectors.NodeSelectorException;
import com.connect_group.thymesheet.query.HtmlElements;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { ThymeleafTestConfiguration.class })
@DirtiesContext
public abstract class ThymeleafTemplateTest {

    private final String templateUrl;

    @Resource
    private ThymeleafTestEngine testEngine;

    public ThymeleafTemplateTest(String template) {
        this.templateUrl = template;
    }

    private String getTemplateUrl() {
        return templateUrl;
    }

    public HtmlElements process(Map<String, Object> model) {
        return testEngine.process(getTemplateUrl(), model);
    }
    
    public HtmlElements process() {
        return testEngine.process(getTemplateUrl());
    }

    public HtmlElements matching(HtmlElements elements, String selector) {
        try {
            return elements.matching(selector);
        } catch (NodeSelectorException e) {
            throw new RuntimeException();
        }
    }
}
package syed.shahza.harmonia.frontend.configuration;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.connect_group.thymeleaf.testing.ThymeleafTestEngine;

@Configuration
@Import({ ThymeleafConfiguration.class })
public class ThymeleafTestConfiguration {
    @Resource
    private ITemplateResolver templateResolver;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ServletContext servletContext;

    @Bean
    public ThymeleafTestEngine testEngine() {
        ThymeleafTestEngine testEngine = new ThymeleafTestEngine();

        testEngine.setTemplateResolver(this.templateResolver);

        testEngine.setServletContext(this.servletContext);
        testEngine.setApplicationContext(this.applicationContext);
        testEngine.setDialect(new SpringStandardDialect());
        return testEngine;
    }
}
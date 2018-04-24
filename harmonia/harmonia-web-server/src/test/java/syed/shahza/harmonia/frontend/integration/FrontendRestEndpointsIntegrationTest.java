package syed.shahza.harmonia.frontend.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import syed.shahza.harmonia.frontend.configuration.ThymeleafConfiguration;
import syed.shahza.harmonia.frontend.configuration.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { ThymeleafConfiguration.class, WebAppConfiguration.class})
@EnableAutoConfiguration
public class FrontendRestEndpointsIntegrationTest {
	
    @LocalServerPort
    private int randomServerPort;

    // NOTE: cannot test any endpoints- they depend on backend, spinning up both would be e2e test
    @Test
    public void emptyTestToSpinUpSpringBootApplicationTestingConfigurationOfBeansWorks() {
    }
}

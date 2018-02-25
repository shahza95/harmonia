package syed.shahza.harmonia.endtoend.test;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "syed.shahza.harmonia.endtoend.test.step", features = "classpath:features")
public class RunEndToEndTests {

    private static WebDriver webDriver;

    public static void setWebDriver(WebDriver webDriver) {
        RunEndToEndTests.webDriver = webDriver;
    }

    @AfterClass
    public static void afterClass() {
        if (webDriver != null) {
            webDriver.close();
        }
    }
}

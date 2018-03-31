package syed.shahza.harmonia.endtoend.test.page.webdriver;

import org.openqa.selenium.WebDriver;

import syed.shahza.harmonia.endtoend.test.page.ActiveLectureLecturerPage;

public class WebDriverActiveLectureLecturerPage extends WebDriverPage implements ActiveLectureLecturerPage {

    public WebDriverActiveLectureLecturerPage(WebDriver webDriver, String baseUrl) {
        super(webDriver, baseUrl);
    }

    @Override
    public void clickDisableButton() {
        this.findButtonByName("commentsToggle").ifPresent(button -> button.submit());
    }  
}
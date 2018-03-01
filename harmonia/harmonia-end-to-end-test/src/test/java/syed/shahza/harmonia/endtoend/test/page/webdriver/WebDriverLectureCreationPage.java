package syed.shahza.harmonia.endtoend.test.page.webdriver;

import org.openqa.selenium.WebDriver;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.LectureCreationPage;

public class WebDriverLectureCreationPage extends WebDriverPage implements LectureCreationPage {
    private final static String LECTURE_CREATION_URL = "lecturer/lecture/create";

    public WebDriverLectureCreationPage(WebDriver webDriver, String baseUrl) {
        super(webDriver, baseUrl);
    }

    @Override
    public void navigateTo() {
        this.navigateTo(LECTURE_CREATION_URL);
    }
    
    @Override
    public void fillOutLectureForm(LectureDto lectureDto) {
    	this.findInputByName("title").ifPresent(input -> input.fill(lectureDto.getTitle()));
    	this.findInputByName("password").ifPresent(input -> input.fill(lectureDto.getPassword()));
    	this.findInputByName("lectureDate").ifPresent(input -> input.fill(lectureDto.getDate().toString()));
    	this.findInputByName("lectureStartTime").ifPresent(input -> input.fill(lectureDto.getStartTime().toString()));
    	this.findInputByName("lectureEndTime").ifPresent(input -> input.fill(lectureDto.getEndTime().toString()));
    }

    @Override
    public Result clickCreateButton() {
        this.findButtonByClass("button").ifPresent(button -> button.submit());
        return findTextByString("Active").isPresent() ? Result.SUCCESS : Result.FAILURE;
    }
}
package syed.shahza.harmonia.endtoend.test.component;

import java.util.Optional;

import org.openqa.selenium.WebElement;

public class Text {
    private final WebElement webElement;

    private Text(WebElement webElement) {
        this.webElement = webElement;
    }

    public static Optional<Text> wrap(Optional<WebElement> webElement) {
        return webElement.isPresent() ? Optional.of(new Text(webElement.get())) : Optional.empty();
    }

    public boolean isPresent() {
        return this.webElement.isDisplayed();
    }

    public void click() {
        this.webElement.click();
    }
}

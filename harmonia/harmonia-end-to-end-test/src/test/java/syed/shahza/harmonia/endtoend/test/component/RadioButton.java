package syed.shahza.harmonia.endtoend.test.component;

import java.util.Optional;
import org.openqa.selenium.WebElement;

public class RadioButton {
    private final WebElement webElement;

    private RadioButton(WebElement webElement) {
        this.webElement = webElement;
    }

    public static Optional<RadioButton> wrap(Optional<WebElement> webElement) {
        return webElement.isPresent() ? Optional.of(new RadioButton(webElement.get())) : Optional.empty();
    }

    public void click() {
        this.webElement.click();
    }
}

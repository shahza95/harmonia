package syed.shahza.harmonia.endtoend.test.component;

import java.util.Optional;

import org.openqa.selenium.WebElement;

public class Input {
    private final WebElement webElement;

    private Input(WebElement webElement) {
        this.webElement = webElement;
    }

    public static Optional<Input> wrap(Optional<WebElement> webElement) {
        return webElement.isPresent() ? Optional.of(new Input(webElement.get())) : Optional.empty();
    }

    public boolean isEmpty() {
        if (this.webElement.getAttribute("value") == "") {
            return true;
        }
        return false;
    }

    public void fill(String filler) {
        this.webElement.sendKeys(filler);
    }

	public boolean checkEnabled() {
		return this.webElement.isEnabled();
	}
}

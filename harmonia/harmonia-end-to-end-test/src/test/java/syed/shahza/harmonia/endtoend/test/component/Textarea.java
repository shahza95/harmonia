package syed.shahza.harmonia.endtoend.test.component;

import java.util.Optional;

import org.openqa.selenium.WebElement;

public class Textarea {
    private final WebElement webElement;

    private Textarea(WebElement webElement) {
        this.webElement = webElement;
    }

    public static Optional<Textarea> wrap(Optional<WebElement> webElement) {
        return webElement.isPresent() ? Optional.of(new Textarea(webElement.get())) : Optional.empty();
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
}

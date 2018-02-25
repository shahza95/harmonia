package syed.shahza.harmonia.endtoend.test.component;

import java.util.Optional;
import org.openqa.selenium.WebElement;

public class Button {
    private final WebElement webElement;

    private Button(WebElement webElement) {
        this.webElement = webElement;
    }

    public static Optional<Button> wrap(Optional<WebElement> webElement) {
        return webElement.isPresent() ? Optional.of(new Button(webElement.get())) : Optional.empty();
    }

    public void submit() {
        this.webElement.submit();
    }
}

package syed.shahza.harmonia.endtoend.test.component;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Select {
    private final WebElement webElement;

    private Select(WebElement webElement) {
        this.webElement = webElement;
    }

    public static Optional<Select> wrap(Optional<WebElement> webElement) {
        return webElement.isPresent() ? Optional.of(new Select(webElement.get())) : Optional.empty();
    }

	public void choose(String emoji) {
		List<WebElement> list = this.webElement.findElements(By.cssSelector("option"));//.get(1).click();
		for(WebElement element: list) {
			if(element.getAttribute("text").contains("Happy")){
				element.click();
			};
		}
	}
}

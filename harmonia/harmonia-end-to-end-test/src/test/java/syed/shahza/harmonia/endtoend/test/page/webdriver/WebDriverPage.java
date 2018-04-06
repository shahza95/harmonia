package syed.shahza.harmonia.endtoend.test.page.webdriver;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import syed.shahza.harmonia.endtoend.test.component.Button;
import syed.shahza.harmonia.endtoend.test.component.Input;
import syed.shahza.harmonia.endtoend.test.component.RadioButton;
import syed.shahza.harmonia.endtoend.test.component.Select;
import syed.shahza.harmonia.endtoend.test.component.Text;
import syed.shahza.harmonia.endtoend.test.component.Textarea;
import syed.shahza.harmonia.endtoend.test.service.page.Page;

import com.google.common.base.Predicate;

public class WebDriverPage implements Page {
    private final WebDriver webDriver;
    private final String baseUrl;
    private final static long WEB_ELEMENT_WAIT_TIME_SECONDS = 40;

    public WebDriverPage(WebDriver webDriver, String baseUrl) {
        this.webDriver = webDriver;
        this.baseUrl = baseUrl;
    }
    
    public WebDriver getWebDriver() {
    	return this.webDriver;
    }

    @Override
    public void navigateTo(String url) {
        String fullUrl = this.fullUrl(url);
        this.webDriver.get(fullUrl);

        WebDriverWait wait = new WebDriverWait(this.webDriver, WEB_ELEMENT_WAIT_TIME_SECONDS);
        Predicate<WebDriver> predicate = new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver arg0) {
                return ((JavascriptExecutor) WebDriverPage.this.webDriver).executeScript("return document.readyState").equals("complete");
            }
        };
        wait.until(predicate);
    }

    @Override
    public Optional<Button> findButtonByClass(String clazz) {
        return Button.wrap(find(By.className(clazz)));
    }
    
    @Override
    public Optional<Button> findButtonByName(String name) {
    	return Button.wrap(find(By.name(name)));
    }

    @Override
    public Optional<Input> findInputByName(String name) {
        return Input.wrap(this.find(By.name(name)));
    }
    
    @Override
    public Optional<Textarea> findTextareaByName(String name) {
    	return Textarea.wrap(this.find(By.name(name)));
    }
    
    @Override
    public Optional<Select> findSelectByName(String name) {
    	return Select.wrap(this.find(By.name(name)));
    }
    
    @Override
    public Optional<RadioButton> findRadioButtonById(String id) {
    	return RadioButton.wrap(this.find(By.id(id)));
    }
    
    @Override
    public Optional<Text> findTextByString(String string) {
    	return Text.wrap(this.find(By.xpath("//*[contains(text()," + "'" + string + "')]")));
    }

    private String fullUrl(String url) {
        return this.baseUrl + url;
    }

    private Optional<WebElement> find(By by) {
        try {
            return Optional.of(this.webDriver.findElement(by));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
}

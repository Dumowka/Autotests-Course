package Tasks.Task1.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class CommonPage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;

    public CommonPage(WebDriver webDriver, WebDriverWait wait){
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void clickOnElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public String getValueFromElement(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element)).getAttribute("value");
    }

    public void changeValueInSelect(String value, WebElement element){
        Select select = new Select(element);
        select.selectByValue(value);
    }
}

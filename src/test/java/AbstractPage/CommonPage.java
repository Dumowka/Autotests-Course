package AbstractPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;


public abstract class CommonPage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    protected JavascriptExecutor javascriptExecutor;

    public CommonPage(WebDriver webDriver, WebDriverWait wait, JavascriptExecutor javascriptExecutor) {
        this.webDriver = webDriver;
        this.wait = wait;
        this.javascriptExecutor = javascriptExecutor;
    }

    public void clickOnElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public String getValueFromElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getAttribute("value");
    }

    public void changeValueInSelect(String value, WebElement element) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void clickElementWithJSByCss(String locator) {
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.cssSelector(locator))));
        javascriptExecutor.executeScript("document.querySelector('" + locator + "').click();");
    }

    public void setAttributeWithJSByCss(String locator, String name, String value) {
        javascriptExecutor.executeScript("document.querySelector('" + locator + "').setAttribute('" + name + "', '" + value + "');");
    }

    public static void switchingToNewTab(WebDriver driver, String oldTab) {
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
    }

    public static void comingBackToOldTab(WebDriver driver, String oldTab) {
        driver.close();
        driver.switchTo().window(oldTab);
    }
}

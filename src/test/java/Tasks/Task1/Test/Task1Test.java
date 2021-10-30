package Tasks.Task1.Test;

import Tasks.Task1.Pages.PasswordsGeneratorPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task1Test {
    WebDriver webDriver;
    WebDriverWait wait;
    PasswordsGeneratorPage passwordsGeneratorPage;

    @Test
    public void randomPasswordTest(){
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://passwordsgenerator.net/ru/");
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        passwordsGeneratorPage = new PasswordsGeneratorPage(webDriver, wait);

        passwordsGeneratorPage.clickGeneratePasswordsBtn();
        String firstPassword = passwordsGeneratorPage.getFinalPassword();
        Assert.assertNotEquals("", firstPassword);

        passwordsGeneratorPage.clickGeneratePasswordsBtn();
        String secondPassword = passwordsGeneratorPage.getFinalPassword();
        Assert.assertNotEquals(firstPassword, secondPassword);

        String length = "6";
        passwordsGeneratorPage.clickEnabledSymbolsCheckBox();
        passwordsGeneratorPage.changePasswordLengthSelect(length);
        passwordsGeneratorPage.clickGeneratePasswordsBtn();
        String thirdPassword = passwordsGeneratorPage.getFinalPassword();
        Assert.assertEquals(length,String.valueOf(thirdPassword.length()));
    }

    @After
    public void afterAll(){
        webDriver.quit();
    }
}

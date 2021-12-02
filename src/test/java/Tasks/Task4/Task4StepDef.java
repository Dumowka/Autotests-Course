package Tasks.Task4;

import Tasks.Task1.Pages.PasswordsGeneratorPage;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task4StepDef {
    WebDriver webDriver;
    WebDriverWait wait;
    PasswordsGeneratorPage passwordsGeneratorPage;


    String firstPassword;
    String secondPassword;
    String thirdPassword;

    @Given("Установка браузера, инициализация всех переменных и открытие сайта")
    public void initAll(){
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://passwordsgenerator.net/ru/");
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        passwordsGeneratorPage = new PasswordsGeneratorPage(webDriver, wait, javascriptExecutor);
    }

    @And("Нажать на кнопку 'Генерировать пароль'")
    public void clickGeneratePasswordsBtn() {
        passwordsGeneratorPage.clickGeneratePasswordsBtn();
    }

    @And("Проверить сгенерированный пароль на неравенство {string}")
    public void checkFirstPasswordForNotEquals(String anotherPassword) {
        firstPassword = passwordsGeneratorPage.getFinalPassword();
        Assert.assertNotEquals(firstPassword, anotherPassword);
    }

    @And("Проверить сгенерированный пароль на неравенство предыдущему")
    public void checkSecondPasswordForNotEqualsFirstPassword() {
        secondPassword = passwordsGeneratorPage.getFinalPassword();
        Assert.assertNotEquals(firstPassword, secondPassword);
    }

    @And("Убрать галочку у 'Включить символы'")
    public void removeEnabledSymbolsCheckBox() {
        passwordsGeneratorPage.clickEnabledSymbolsCheckBox();
    }

    @And("Поменять значение 'Длина пароля' на {string}")
    public void changePasswordLengthSelect(String length) {
        passwordsGeneratorPage.changePasswordLengthSelect(length);
    }

    @And("Проверить, что длина сгенерированного пароля равна {string}")
    public void checkThirdPasswordLengthForEquals(String length) {
        thirdPassword = passwordsGeneratorPage.getFinalPassword();
        Assert.assertEquals(length, String.valueOf(thirdPassword.length()));
    }


    public void afterAll() {
        webDriver.quit();
    }
}

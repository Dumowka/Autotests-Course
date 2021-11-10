package Tasks.Task2.Test;

import AbstractPage.CommonPage;
import Tasks.Task2.Pages.Task2Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Task2 {
    WebDriver webDriver;
    WebDriverWait wait;
    Actions actions;
    Task2Page task2Page;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("http://2017.makemepulse.com/");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        actions = new Actions(webDriver);
        task2Page = new Task2Page(webDriver, wait, javascriptExecutor, actions);
    }

    @Test
    public void Task2Test() {
        String oldTab = webDriver.getWindowHandle();

        // Шаг 1
        task2Page.clickLogoLink();
        sleep(3_000);

        // Шаг 2
        CommonPage.switchingToNewTab(webDriver, oldTab);
        sleep(3_000);
        CommonPage.comingBackToOldTab(webDriver, oldTab);
        sleep(3_000);

        // Шаг 3
        task2Page.disabledLogoDisplay();
        sleep(3_000);

        // Шаг 4
        task2Page.moveDraggerCursor();
        sleep(3_000);

        // Шаг 5
        task2Page.moveMouseCursor(-200, -350);
        sleep(3_000);
        task2Page.holdAndMoveMouseCursor(-300, 0);
        sleep(3_000);

        // Шаг 6
        task2Page.moveDraggerCursor();
        sleep(3_000);

        // Шаг 7
        task2Page.moveMouseCursor(-200, -350).mouseCursorClick();
        sleep(3_000);

        // Шаг 8
        task2Page.moveDraggerCursor();
        sleep(3_000);

        // Шаг 9
        task2Page.moveMouseCursor(-200, -350).mouseClickAndHold(7_000);
        sleep(3_000);

        // Шаг 10
        task2Page.moveDraggerCursor();
        sleep(3_000);

        // Шаг 11
        task2Page.moveMouseCursor(-200, -350).mouseClickAndHold(2_000).moveMouseCursor(200, 0);
        sleep(3_000);

        // Шаг 12
        task2Page.moveDraggerCursor();
        sleep(3_000);

        // Шаг 13
        task2Page.moveMouseCursor(-200, -350).mouseClickAndHold(2_000);
        sleep(3_000);

        // Шаг 14
        task2Page.moveDraggerCursor();
        sleep(3_000);
    }

    @After
    public void afterAll() {
        webDriver.quit();
    }

    public void sleep(int timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

package Tasks.Task3;

import Tasks.Task2.Pages.Task2Page;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Task3 {
    WebDriver webDriver;
    WebDriverWait wait;
    Actions actions;
    Task2Page task2Page;

    @Before
    public void init() {
        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\geckodriver.exe");
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.dns-shop.ru/");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        actions = new Actions(webDriver);
        task2Page = new Task2Page(webDriver, wait, javascriptExecutor, actions);
    }

    @Test
    public void Task3() throws InterruptedException {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String pc = "//a[text()='Компьютеры']";
        String motherBoards = "//a[text()='Материнские платы']";
        String cardProduct = "//div[@class='catalog-product ui-button-widget']";
        String showMoreBtn = "//button[@data-role='show-more-btn']";
        String cart = "//a[@data-commerce-target='CART']";
        String catrItemProduct = "//div[@class='cart-items__product']";
        // Навести на "Компьютеры"
        actions.pause(2_000).moveToElement(
                wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(pc))))
        ).perform();

        // Выбрать "Материнские платы" в разделе "Комплектующие для ПК"
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(motherBoards)))).click();

        // Посчитать количество записей на странице
        int size1 = webDriver.findElements(By.xpath(cardProduct)).size();

        // Нажать на кнопку "Показать еще"
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(showMoreBtn)))).click();

        // Проверить, что количество записей увеличилось в 2 раза
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(cardProduct), size1));
        List<WebElement> cardList = webDriver.findElements(By.xpath(cardProduct));
        int size2 = cardList.size();
        Assert.assertEquals(size1, size2/2);

        // Сгенерировать случайное число
        int randomNumber = 1 + (int) (Math.random() * (10 - 1) + 1);

        // Нажать на кнопку "Купить" количество раз равное случайному числу из шага 6 (примечание: кнопку "Купить" нужно получить как дочерний элемент от записи с товаром)
        for (int i = 0; i < randomNumber; i++){
            Thread.sleep(1000);
            cardList.get(i).findElement(By.xpath("//button[text()='Купить']")).click();
        }

        // Нажать на "Корзина"
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(cart)))).click();

        // Сравнить число записей со случайным числом
        int count = webDriver.findElements(By.xpath(catrItemProduct)).size();
        System.out.println(count);
        Assert.assertEquals(count, randomNumber);
    }

    @After
    public void afterAll() {
        webDriver.quit();
    }
}

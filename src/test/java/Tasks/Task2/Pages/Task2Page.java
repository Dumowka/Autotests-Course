package Tasks.Task2.Pages;

import AbstractPage.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

public class Task2Page extends CommonPage {
    Actions actions;
    private static final String LOGO_CSS = ".logo";
    private static final String DRAG_CURSOR_CSS = ".dragger-cursor";
    private static final String MOUSE_CURSOR_CSS = ".mouse-cursor";
    private static final String SITE_BODY_CSS = "#gl-canvas";

    public Task2Page(WebDriver webDriver, WebDriverWait wait, JavascriptExecutor javascriptExecutor, Actions actions) {
        super(webDriver, wait, javascriptExecutor);
        this.actions = actions;
    }

    public void clickLogoLink() {
        clickElementWithJSByCss(LOGO_CSS);
    }

    public void moveDraggerCursor() {
        WebElement draggerCursor = webDriver.findElement(By.cssSelector(DRAG_CURSOR_CSS));
        actions.dragAndDropBy(draggerCursor, 280, 0).perform();
    }

    public void holdAndMoveMouseCursor(int x, int y) {
        WebElement mouseCursor = webDriver.findElement(By.cssSelector(MOUSE_CURSOR_CSS));
        actions.dragAndDropBy(mouseCursor, x, y).pause(1_000).moveByOffset(-x, -y).dragAndDropBy(mouseCursor,  -x, -y).perform();
    }

    public Task2Page moveMouseCursor(int x, int y) {
        actions.moveByOffset(x, y).perform();
        return this;
    }

    public void disabledLogoDisplay() {
        setAttributeWithJSByCss(LOGO_CSS, "class", "display:none");
    }

    public Task2Page mouseCursorClick() {
        actions.click(webDriver.findElement(By.cssSelector(SITE_BODY_CSS)));
        return this;
    }


    public Task2Page mouseClickAndHold(int time) {
        actions.clickAndHold(webDriver.findElement(By.cssSelector(SITE_BODY_CSS))).pause(time).perform();
        return this;
    }

}

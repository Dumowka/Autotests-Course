package Tasks.Task1.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordsGeneratorPage extends CommonPage{
    private static final String GENERATE_PASSWORD_BTN_XPATH = "//div[@class = 'button GenerateBtn']";
    private static final String FINAL_PASS_FIELD_ID = "final_pass";
    private static final String ENABLED_SYMBOLS_CHECKBOX_ID = "Symbols";
    private static final String PASSWORD_LENGTH_SELECT_ID = "pgLength";


    public PasswordsGeneratorPage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void clickGeneratePasswordsBtn(){
        clickOnElement(webDriver.findElement(By.xpath(GENERATE_PASSWORD_BTN_XPATH)));
    }

    public void clickEnabledSymbolsCheckBox(){
        clickOnElement(webDriver.findElement(By.id(ENABLED_SYMBOLS_CHECKBOX_ID)));
    }

    public void changePasswordLengthSelect(String value){
        changeValueInSelect(value, webDriver.findElement(By.id(PASSWORD_LENGTH_SELECT_ID)));
    }

    public String getFinalPassword(){
        return getValueFromElement(webDriver.findElement(By.id(FINAL_PASS_FIELD_ID)));
    }
}

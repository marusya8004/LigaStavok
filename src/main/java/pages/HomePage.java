package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.ByteArrayInputStream;

public class HomePage extends PageObjectCreator {

    @FindBy(xpath = "//div[@role='tablist']/child::div")
    WebElement categories;

    @FindBy(xpath = "//div[@data-apiary-widget-name='@MarketNode/HeaderLogo']")
    WebElement logo;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the main page")
    public HomePage openHome() {
        driver.get("https://market.yandex.ru/");
        Assert.assertTrue(this.driver.getCurrentUrl().contains("https://market.yandex"));
        wait.until(ExpectedConditions.visibilityOf(logo)).isDisplayed();
        Allure.addAttachment("OpenHomePage", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
        return this;
    }

    @Step("Select a category")
    public void selectTypeMenu(String menu) {
        wait.until(ExpectedConditions.elementToBeClickable(categories.findElement
                (By.xpath(".//*[contains(text(),'" + menu + "')]")))).click();
        Allure.addAttachment("SelectCategory", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
    }
}

package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.ByteArrayInputStream;
@Slf4j

public class CategoryPage extends PageObjectCreator {

    @FindBy(xpath = "//input[@name='Цена от']")
    WebElement priceFrom;

    @FindBy(xpath = "//*[text()='Производитель']/parent::*//ul")
    WebElement menuBrand;

    @FindBy(xpath = "//*[@data-zone-name='title']/a")
    WebElement titleList;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select a brand")
    public CategoryPage selectBrand(String brand) {
        wait.until(ExpectedConditions.visibilityOf(menuBrand)).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//span[text()='" + brand + "']//.."))).click();
        Allure.addAttachment("SelectBrand", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
        return this;
    }

    @Step("Set a minimum price")
    public CategoryPage setPriceFrom(String price) {
        wait.until(ExpectedConditions.visibilityOf(priceFrom)).click();
        priceFrom.sendKeys(price);
        Allure.addAttachment("SetPrice", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
        return this;
    }

    @Step("Switch to open the product tab")
    public void clickToSnippetTitle() {
        Actions action = new Actions(driver);
        action.moveToElement(titleList).build().perform();
        titleList.click();
        driver.switchTo().window(String.valueOf(driver.getWindowHandles()));
        Allure.addAttachment("SetPrice", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
    }
}
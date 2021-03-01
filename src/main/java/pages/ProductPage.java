package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.ByteArrayInputStream;

public class ProductPage extends PageObjectCreator {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ul[@itemscope]/following-sibling::div")
    WebElement productName;

    @FindBy(xpath = "//*[contains(@data-zone-data,'default-offer')]//span")
    WebElement productPrice;

    @Step("The product`s name displayed")
    public String getTextProductName() {
        Allure.addAttachment("ProductBrand", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    @Step("The product`s price displayed")
    public String getTextProductPrice() {
        Allure.addAttachment("ProductPrice", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
        return wait.until(ExpectedConditions.visibilityOf(productPrice)).getText();
    }
}



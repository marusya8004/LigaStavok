package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.ByteArrayInputStream;

public class ProductCategoryPage extends PageObjectCreator {

    @FindBy(xpath = "//div[@data-apiary-widget-name='@MarketNode/NavigationTree']")
    WebElement categoriesMenu;

    public ProductCategoryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select a subcategory")
    public void selectInnerCategory(String name) {
        wait.until(ExpectedConditions.visibilityOf(categoriesMenu)).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[text()='" + name + "']"))).click();
        Allure.addAttachment("SelectSubCategory", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));

    }
}

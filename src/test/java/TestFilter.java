import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;
import pages.ProductPage;
import pages.ProductCategoryPage;

import java.util.Collections;
import java.util.List;


public class TestFilter extends BaseTest{
    HomePage homePage;
    ProductCategoryPage productCategoryPage;
    CategoryPage categoryPage;
    ProductPage productPage;

    @BeforeMethod
    public void openSite() {
        setUp().get("https://market.yandex.ru/");
    }

    @BeforeTest()
    public void setup(){
        homePage = new HomePage(driver);
        productCategoryPage = new ProductCategoryPage(driver);
        categoryPage = new CategoryPage(driver);
        productPage = new ProductPage(driver);
    }

    @AfterMethod
    public void driverDestroy() {
        tearDown();
    }

    @Test
    @Description("Select the first product on the filter and check the compliance by name")
    public void selectProductByName()  {
        homePage.openHome().selectTypeMenu("Электроника");
        productCategoryPage.selectInnerCategory("Телевизоры");
        categoryPage.setPriceFrom("20000").selectBrand("Samsung").selectBrand("LG").clickToSnippetTitle();
        List<String> names = List.of(productPage.getTextProductName());
        Assert.assertTrue(names.stream().anyMatch(name -> name.contains("LG") || name.contains("Samsung")));
    }

    @Test
    @Description("Select the first product by filter and check the price match")
    public void checkByNameAndPrice()  {
        homePage.openHome().selectTypeMenu("Электроника");
        productCategoryPage.selectInnerCategory("Телевизоры");
        categoryPage.setPriceFrom("20000").selectBrand("Samsung").selectBrand("LG").clickToSnippetTitle();
        List<Integer> prices = List.of(Integer.parseInt(productPage.getTextProductPrice().replaceAll(" ","").substring(5)));
        Assert.assertTrue(prices.stream().allMatch(price -> price > 20000 || price == 20000));
    }
}

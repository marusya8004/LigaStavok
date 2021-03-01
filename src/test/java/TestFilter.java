import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CategoryPage;
import pages.HomePage;
import pages.ProductCategoryPage;
import pages.ProductPage;

import java.util.List;


public class TestFilter extends BaseTest {
    private HomePage homePage;
    private ProductCategoryPage productCategoryPage;
    private CategoryPage categoryPage;
    private ProductPage productPage;

    @BeforeClass
    public void openSite() {
        setUp().get("https://market.yandex.ru/");
    }

    @BeforeTest()
    public void setup() {
        homePage = new HomePage(driver);
        productCategoryPage = new ProductCategoryPage(driver);
        categoryPage = new CategoryPage(driver);
        productPage = new ProductPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void driverDestroy() {
        tearDown();
    }

    @Test
    @Description("Select the first product by filter and check the price match")
    public void checkByNameAndPrice() {
        homePage.openHome().selectTypeMenu("Электроника");
        productCategoryPage.selectInnerCategory("Телевизоры");
        categoryPage.setPriceFrom("20000").selectBrand("Samsung").selectBrand("LG").clickToSnippetTitle();
        List<String> names = List.of(productPage.getTextProductName());
        Assert.assertTrue(names.stream().anyMatch(name -> name.contains("LG") || name.contains("Samsung")));
        List<Integer> prices = List.of(Integer.parseInt(productPage.getTextProductPrice().replaceAll(" ", "").substring(0, 5)));
        Assert.assertTrue(prices.stream().allMatch(price -> price > 20000 || price == 20000));
    }
}

package framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultPage extends BasePage {

    @FindBy(xpath = "//a[@class='catalog-product__name ui-link ui-link_black']")
    private List<WebElement> products;

    @FindBy(xpath = "//div[@class='catalog-products view-simple']")
    private WebElement productBlock;


    //Выбор продутка в результатах поиска
    public ProductPage selectProduct(String productName) {
        waitUtilElementToBeVisible(productBlock);
        for (WebElement product : products) {
            if (product.getText().equalsIgnoreCase(productName)) {
                scrollToElementJs(product);
                js.executeScript("arguments[0].click();", product);
                return pageManager.getProductPage();
            }
        }
        Assert.fail("Продукт с именем \"" + productName + "\" не найден");
        return pageManager.getProductPage();
    }



}

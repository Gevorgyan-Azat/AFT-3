package framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement productName;

    @FindBy(xpath = "//div[contains(@class, 'top')]//div[contains(@class, 'price_active')]")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='product-card-description-text']")
    private WebElement productDescription;

    @FindBy(xpath = "//div[@class='product-card-top product-card-top_full']")
    private WebElement productBlock;

    @FindBy(xpath = "//div[@class = 'additional-sales-tabs__title' and contains(text(), 'Гарантия')]")
    private WebElement menuWarranty;

    @FindBy(xpath = "//span[@class='product-warranty__period']")
    private List<WebElement> btnWarranty;

    @FindBy(xpath = "//div[contains(@class, 'top__buy')]//button[contains(@class, 'buy-btn')]")
    private WebElement btnBuy;

    @FindBy(xpath = "//input[contains(@class, 'ui-input-search') and not(@id)]")
    private WebElement searchBar;

    @FindBy(xpath = "//nav//span[contains(@class, 'ui-input-search__icon_search')]")
    private WebElement searchBtn;

    @FindBy(xpath = "//span[@class='cart-link__price']")
    private WebElement basketPrice;

    @FindBy(xpath = "//div[@class='cart-modal' and not(@display)]")
    private WebElement basketBlockDisplay;


    public ProductPage setPS5Param() {
        ps5.setName(productName.getText());
        ps5.setPrice(getIntProductPrice(productPrice));
        ps5.setDescription(productDescription.getText());
        return this;
    }

    public ProductPage setFC6Param() {
        fc6.setName(productName.getText());
        fc6.setPrice(getIntProductPrice(productPrice));
        fc6.setDescription(productDescription.getText());
        return this;
    }

    //Выбор дополнительной гарантии
    public ProductPage selectWarranty(String value) {
        waitUtilElementToBeVisible(productBlock);
        waitUtilElementToBeClickable(menuWarranty).click();
        for (WebElement setWarranty:btnWarranty) {
            if (setWarranty.getText().equalsIgnoreCase(value)) {
                waitUtilElementToBeClickable(setWarranty).click();
                ps5.setWarranty(value);
                if (ps5.getPrice()> ps5.getOptionsPrice()) {
                    ps5.setOptionsPrice(getIntProductPrice(productPrice));
                }
                return this;
            }
        }
        Assert.fail("Гарантия со значением " + value + " не найдена");
        return this;
    }

    //Добавление товара в корзину
    public ProductPage buyProduct() {
        waitUtilElementToBeClickable(btnBuy).click();
        waitUtilElementToBeVisible(basketBlockDisplay);
        basket.setTotalPrice(ps5.getOptionsPrice() + fc6.getPrice());
        basket.setQuantity(basket.getQuantity()+1);
        return this;
    }

    //Проверка суммы корзины
    public ProductPage checkBasket() {
        Assert.assertEquals("Сумма покупок не совпадает со значением суммы в корзине",
                basket.getTotalPrice(), getIntBasketPrice(basketPrice));
        return this;
    }

    //Переход в корзину
    public BasketPage selectBasket() {
        waitUtilElementToBeClickable(basketPrice).click();
        return pageManager.getBasketPage();
    }

    //Поиск нового продукта
    public ProductPage searching(String searchValue) {
        waitUtilElementToBeClickable(searchBar).click();
        searchBar.sendKeys(searchValue);
        Assert.assertEquals("Поисковое поле заполнено некорректно",
                searchValue, searchBar.getAttribute("value"));
        searchBtn.click();
        return this;
    }

}

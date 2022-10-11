package framework.pages;

import framework.Product;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BasketPage extends BasePage {

    @FindBy(xpath = "//div[@class='cart-items__content-container']")
    private List<WebElement> productBlock;

    @FindBy(xpath = ".//button[contains(@class, 'remove-button')]")
    private WebElement deleteBtnProduct;

    @FindBy(xpath = "//span[contains(@class,'cart-link__price')]")
    private WebElement totalPrice;

    @FindBy(xpath = "total-amount__summary-section")
    private WebElement amountBlock;

    @FindBy(xpath = "//input[@class='count-buttons__input']")
    private WebElement addProduct;

    @FindBy(xpath = "//div[@class='cart-tab-total-amount']//span[@class='restore-last-removed']")
    private WebElement restoreBtnProduct;


    private final String checkBtnWarranty = ".//div[contains(@class, 'base-ui-radio-button__checked')]";
    private final String deleteProduct = ".//button[contains(@class, 'remove-button')]";

    //Проверка выбора доп. гарантии
    public BasketPage checkWarranty(String productName) {
        for (Product product : productList){
            if(product.getWarranty() != null){
                for (WebElement block: productBlock){
                    if(block.getAttribute("innerText").contains(productName)) {
                        Assert.assertEquals("Неверное значение гарантии у продукта '" + productName + "'",
                                product.getWarranty(), block.findElement(By.xpath(checkBtnWarranty)).getAttribute("innerText"));
                    }
                }
            }
        }
        return this;
    }

    //Проверка суммы в корзине
    public BasketPage checkPrice() {
        Assert.assertEquals("Сумма покупок не совпадает со значением суммы в корзине",
                basket.getTotalPrice(), getIntBasketPrice(totalPrice));
        return this;
    }

    //Удаление товара
    public BasketPage deleteProduct(String productName) {
        for (WebElement block: productBlock) {
            if(block.getAttribute("innerText").contains(productName)) {
                waitUtilElementToBeClickable(block.findElement(By.xpath(deleteProduct)));
                js.executeScript("arguments[0].click();", block.findElement(By.xpath(deleteProduct)));
                basket.setTotalPrice(basket.getTotalPrice() -
                        (Integer.parseInt(block.findElement(By.xpath(".//span[@class='price__current']")).getAttribute("innerText").replaceAll("\\D", ""))));
                basket.setQuantity(basket.getQuantity()-1);
                basket.setDeleteProdName(productName);
            }
        }
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='cart-items__content-container']"), basket.getQuantity()));
        return this;
    }

    //Увелиение количества выбранного продукта
    public BasketPage addQuantityProduct(String productName, String value) {
        for (Product product: productList) {
            for (WebElement setPlus:productBlock) {
                if (setPlus.getAttribute("innerText").contains(productName)) {
                    scrollToElementJs(addProduct);
                    js.executeScript("arguments[0].click();", addProduct);
                    addProduct.clear();
                    addProduct.sendKeys(value);
                    addProduct.sendKeys(Keys.ENTER);
                    if (setPlus.getAttribute("innerText").contains(product.getName())) {
                        basket.setTotalPrice(basket.getTotalPrice() - product.getPrice());
                        product.setPrice(product.getPrice() * Integer.parseInt(value));
                        basket.setTotalPrice(basket.getTotalPrice() + product.getPrice());
                        return this;
                    }
                }
            }
        }
        Assert.assertEquals("Значение общей суммы в корзине неверно",
                basket.getTotalPrice(), getIntBasketPrice(totalPrice));
        return this;
    }

    //Вернуть удаленный товар
    public BasketPage restoreProduct() {
        waitUtilElementToBeVisible(restoreBtnProduct);
        if (restoreBtnProduct.isDisplayed()) {
            waitUtilElementToBeClickable(restoreBtnProduct).click();
            basket.setQuantity(basket.getQuantity() + 1);
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='cart-items__content-container']"), basket.getQuantity()));

            for (Product product : productList) {
                String a = product.getName();
                if (product.getName().contains(basket.getDeleteProdName())) {
                    basket.setTotalPrice(basket.getTotalPrice() + product.getPrice());
                    return this;
                }
            }
            Assert.assertEquals("Удаленный товар не был возвращен в корзину",
                    basket.getTotalPrice(), getIntBasketPrice(totalPrice));
            return this;
        }
        Assert.fail("Кнопка 'Вернуть удалённый товар' отсутствует");
        return this;
    }

}

package framework.pages;

import framework.Basket;
import framework.Product;
import framework.managers.DriverManager;
import framework.managers.PageManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected DriverManager driverManager = DriverManager.getINSTANCE();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 500);
    protected PageManager pageManager = PageManager.getINSTANCE();
    protected JavascriptExecutor js = (JavascriptExecutor)driverManager.getDriver();

    protected static Product ps5 = new Product();
    protected static Product fc6 = new Product();
    protected static Basket basket = new Basket();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected int getIntProductPrice (WebElement productPrice) {
        waitUtilElementToBeVisible(productPrice);
        return Integer.parseInt(productPrice.getText().split("₽")[0].replaceAll("\\D", ""));
    }

    protected int getIntBasketPrice (WebElement basketPrice) {
        waitUtilElementToBeVisible(basketPrice);
        return Integer.parseInt(basketPrice.getAttribute("innerText").replaceAll("\\D", ""));
    }


    protected void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}

package framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {

    @FindBy(xpath = "//div[@class='v-confirm-city__buttons']//span[text()='Всё верно']")
    private WebElement selectBtnCity;

    @FindBy(xpath = "//input[contains(@class, 'ui-input-search') and not(@id)]")
    private WebElement searchBar;

    @FindBy(xpath = "//nav//span[contains(@class, 'ui-input-search__icon_search')]")
    private WebElement searchBtn;


    //Всплывающее окно выбора города
    public StartPage selectCity() {
//        if (selectBtnCity.isDisplayed()) {
//            waitUtilElementToBeClickable(selectBtnCity).click();
//        }
        return this;
    }

    //Поиск продукта
    public ResultPage searching(String searchValue) {
        waitUtilElementToBeClickable(searchBar).click();
        searchBar.sendKeys(searchValue);
        Assert.assertEquals("Поисковое поле заполнено некорректно", searchValue, searchBar.getAttribute("value"));
        searchBtn.click();
        return pageManager.getResultPage();
    }
}

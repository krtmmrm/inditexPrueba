package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleHomePage {

    @FindBy( id= "APjFqb")
    private WebElement searchBar;

    @FindBy( id= "W0wltc")
    private WebElement rejectCookiesBtn;

    @FindBy(xpath = "(//input[@class='gNO89b'])[2]")
    private WebElement googleSearchBtn;

    public GoogleHomePage writeOnSearchBar(String text) {
        if(rejectCookiesBtn.isDisplayed()){ rejectCookiesBtn.click();}
        searchBar.clear();
        searchBar.sendKeys(text);
        return this;
    }

    public GoogleHomePage clickGoogleSearch() {
        googleSearchBtn.click();
        return this;
    }
}

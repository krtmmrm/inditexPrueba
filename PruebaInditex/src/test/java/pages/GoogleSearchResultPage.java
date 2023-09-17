package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchResultPage {

    @FindBy(xpath = "(//h3[contains(text(), 'Wikipedia')])[1]")
    WebElement wikipediaFirstResultHeader;

    public GoogleSearchResultPage() {
    }

    public GoogleSearchResultPage clickWikipediaFirstResult(){
        wikipediaFirstResultHeader.click();
        return this;
    }
}

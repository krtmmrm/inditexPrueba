package pages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class WikipediaArticlePage {

    @FindBy(id = "firstHeading")
    WebElement firstHeading;

    @FindBy(id = "mw-content-text")
    WebElement articleContentTxt;

    @FindAll(
            @FindBy(tagName = "p")
    )
    List<WebElement> allParagraphs;

    public WikipediaArticlePage() {
    }

    public void waitPageLoading(Integer seconds) throws InterruptedException {
        while (!firstHeading.isDisplayed() && seconds != 0) {
            wait(1000);
        }
    }

    private String getArticleContent() {
        return articleContentTxt.getText();
    }

    private String getParagraphContent(Integer num) {
        return allParagraphs.get(num).getText();
    }

    public Boolean findKeyWordsInArticle(String keywords) {
        return getArticleContent().contains(keywords);
    }

    public Boolean areKeywordsInParagraph(String keywords, Integer paragraphNum) {
        return getParagraphContent(paragraphNum).contains(keywords);
    }

    public Integer getParagraphNumberForContent(String content) {
        for (int index=0; index<allParagraphs.size(); index++){
            if(areKeywordsInParagraph(content, index)){
                return index;
            }
        }
        return 0;
    }

    public void takeScreenshot(WebDriver driver) throws IOException {
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);

        String screenshotPath = "resources/wikiScreenshot.png";
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));
    }
}

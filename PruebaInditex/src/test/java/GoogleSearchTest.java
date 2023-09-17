import static org.junit.Assert.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.GoogleHomePage;
import pages.GoogleSearchResultPage;
import pages.WikipediaArticlePage;

public class GoogleSearchTest {

    private GoogleHomePage googleHomePage;
    private GoogleSearchResultPage googleSearchResultPage;
    private WikipediaArticlePage wikipediaArticlePage;
    private WebDriver driver;

    public GoogleSearchTest() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        googleHomePage = PageFactory.initElements(driver, GoogleHomePage.class);
        googleSearchResultPage = PageFactory.initElements(driver, GoogleSearchResultPage.class);
        wikipediaArticlePage = PageFactory.initElements(driver, WikipediaArticlePage.class);
    }

    @Test
    public void wikipediaSearchTest() throws InterruptedException, IOException {

        // Buscar en Google la palabra “automatización”
        driver.get("https://www.google.com/");
        googleHomePage.writeOnSearchBar("automatización")
                .clickGoogleSearch();

        //Buscar el link de la Wikipedia resultante
        googleSearchResultPage.clickWikipediaFirstResult();
        wikipediaArticlePage.waitPageLoading(5);

        //Comprobar en qué año se hizo el primer proceso automático
        Integer paragraphNum = wikipediaArticlePage.getParagraphNumberForContent("primer proceso");
        assertEquals(true, wikipediaArticlePage.areKeywordsInParagraph("1785", paragraphNum));


        //Realizar un screenshot de la página de la Wikipedia
        wikipediaArticlePage.takeScreenshot(driver);

        driver.quit();
    }
}

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class GoogleTests {
    static WebDriver driver = null;
    static Logger logger;


    @BeforeClass
    public static void setupDriver() {
        System.setProperty("webdriver.chrome.driver",
                "C://Users/user/IdeaProjects/GoogleTest/src/test/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        logger = Logger.getLogger("new logger");
    }

    GooglePage page = new GooglePage(driver);
    @Test
    public void clickFirstLinkCheckTitle() {
        logger.info("start test clickFirstLinkCheckTitle");
        page.pageOpen("https://google.com/ncr");
        page.search("automation");

        logger.info("saerch in google");
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
        page.firstResult.click();

        logger.info("goto first result page");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("automation"));
    }
    @Test
    public void ExpectedDomainInResultPages() {
        page.pageOpen("https://google.com/ncr");
        page.search("automation");

        Assert.assertTrue(page.checkExpectedDomainInResultPages(
                "www.bmcsoftware.uk/it-solutions/it-automation.html", 5));
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

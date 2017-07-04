import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by user on 04.07.2017.
 */
public class GooglePage {
    static WebDriver driver = null;
    @FindBy(name = "q")
    WebElement searchField;

    @FindBy(css = "._Rm")
    List<WebElement> result;

    @FindBy(css = ".r>a")
    WebElement firstResult;

    @FindBy(css = "#pnnext")
    WebElement nextPage;


    public GooglePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void pageOpen(String url) {
        driver.get(url);
    }
    public void search(String textSearch) {
        searchField.sendKeys(textSearch, Keys.ENTER);
    }
    public boolean checkExpectedDomainInResultPages(String url, Integer pages){
        boolean expectedDomain = false;
        for (int i = 0; i < pages; i++) {
            (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
            for (WebElement elem : result) {
                expectedDomain = elem.getText().contains(url);
                if (expectedDomain == true) {
                    return expectedDomain;
                }
            }
            nextPage.click();
            driver.navigate().refresh();
        }
        return expectedDomain;
    }
}

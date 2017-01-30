import browser.ChromeBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeTest {

    private RemoteWebDriver driver;
    private ChromeBrowser chromeBrowser;

    @Before
    public void setup() {
        chromeBrowser = new ChromeBrowser();
        driver = chromeBrowser.getDriver();
    }

    @Test
    public void test() {
        driver.get("https://www.ebay.com");
        driver.findElement(By.id("gh-ac")).sendKeys("Batman");
        driver.findElement(By.id("gh-btn")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("Results")));
    }

    @After
    public void teardown() {
        chromeBrowser.killDriver();
    }
}

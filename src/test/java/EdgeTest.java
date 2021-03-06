import browser.EdgeBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EdgeTest {

    private EdgeBrowser edgeBrowser;
    private RemoteWebDriver driver;

    @Before
    public void setup() {
        edgeBrowser = new EdgeBrowser();
        driver = edgeBrowser.getDriver();
    }

    @Test
    public void edgeTest() {
        driver.get("https://www.ebay.com");
        driver.findElement(By.id("gh-ac")).sendKeys("Batman");
        driver.findElement(By.id("gh-btn")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("Results")));
    }

    @After
    public void teardown() {
        edgeBrowser.killDriver();
    }
}

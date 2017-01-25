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
    public void chromeTest() {
        driver.get("http://www.tweakers.net");
        driver.findElement(By.linkText("Pricewatch")).click();
        new WebDriverWait(driver, 15).until(ExpectedConditions.titleIs("Pricewatch - Vergelijk elektronicaprijzen"));
    }

    @After
    public void teardown() {
        chromeBrowser.killDriver();
    }
}

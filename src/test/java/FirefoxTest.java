import browser.FirefoxBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirefoxTest {

    private FirefoxBrowser geckoDriverService;
    private RemoteWebDriver driver;

    @Before
    public void setup() {
        geckoDriverService = new FirefoxBrowser();
        driver = geckoDriverService.getDriver();
    }

    @Test
    public void firefoxTest() {
        driver.get("http://www.tweakers.net");
        driver.findElement(By.linkText("Pricewatch")).click();
        new WebDriverWait(driver, 15).until(ExpectedConditions.titleIs("Pricewatch - Vergelijk elektronicaprijzen"));
    }

    @After
    public void teardown() {
        geckoDriverService.killDriver();
    }
}

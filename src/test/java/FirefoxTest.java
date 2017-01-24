import browser.FirefoxBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxTest {

    private FirefoxBrowser geckoDriverService;
    private RemoteWebDriver driver;

    @Before
    public void setup() {
        geckoDriverService = new FirefoxBrowser();
        driver = geckoDriverService.getDriver();
    }

    @Test
    public void firefox() {
        driver.get("http://www.vdab.be");
    }

    @After
    public void teardown() {
        geckoDriverService.killDriver();
    }
}

import browser.ChromeBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

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
        driver.get("http://www.vdab.be");
    }

    @After
    public void teardown() {
        chromeBrowser.killDriver();
    }
}

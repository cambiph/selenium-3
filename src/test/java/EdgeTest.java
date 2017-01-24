import browser.EdgeBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

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
        driver.get("http://www.vdab.be");
    }

    @After
    public void teardown() {
        edgeBrowser.killDriver();
    }
}

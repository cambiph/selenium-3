import browser.ChromeBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.SeleniumScreenRecorder;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class ChromeTest {

    private RemoteWebDriver driver;
    private ChromeBrowser chromeBrowser;
    private SeleniumScreenRecorder seleniumScreenRecorder;

    @Before
    public void setup() throws IOException, AWTException, URISyntaxException {
        chromeBrowser = new ChromeBrowser();
        seleniumScreenRecorder = new SeleniumScreenRecorder();
        seleniumScreenRecorder.startRecording();
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
    public void teardown() throws IOException {
        seleniumScreenRecorder.stopRecording();
        chromeBrowser.killDriver();
    }
}

package browser;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ProxyManager;

import java.io.File;
import java.io.IOException;

public class ChromeBrowser implements Browser {

    private RemoteWebDriver remoteWebDriver;
    private ChromeDriverService chromeDriverService;
    private ProxyManager proxyManager;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ChromeBrowser() {
        proxyManager = new ProxyManager(true);
        setDriver();
    }

    private void buildChromeDriverService() {
        if (null == chromeDriverService) {
            LOGGER.info("Building ChromeDriver service ...");
            chromeDriverService = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File("src/test/resources/chromedriver.exe"))
                    .build();
        } else {
            LOGGER.info("ChromeDriver service already set!");
        }
    }

    private void startChromeDriverService() {
        if (null != chromeDriverService && !chromeDriverService.isRunning()) {
            try {
                LOGGER.info("Starting ChromeDriver service ...");
                chromeDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDriver() {
        if (null == chromeDriverService) {
            LOGGER.info("RemoteWebDriver not set yet, I'll build and start it for you.");
            buildChromeDriverService();
            startChromeDriverService();
        }
        if (null == remoteWebDriver) {
            remoteWebDriver = new RemoteWebDriver(chromeDriverService.getUrl(), buildDesiredCapabilities());
            maximizeBrowserWindow(remoteWebDriver);
        }
    }

    public RemoteWebDriver getDriver() {
        if (null == remoteWebDriver) {
            setDriver();
        }
        return remoteWebDriver;
    }

    public void killDriver() {
        if (null != remoteWebDriver) {
            remoteWebDriver.quit();
        }
        if (null != chromeDriverService && chromeDriverService.isRunning()) {
            chromeDriverService.stop();
        }
    }

    private DesiredCapabilities buildDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if(null != System.getProperty("browsermob")) {
            desiredCapabilities = proxyManager.getDesiredCapabilities();
        }
        desiredCapabilities.setBrowserName("chrome");
        return desiredCapabilities;
    }

    private void maximizeBrowserWindow(RemoteWebDriver driver) {
        driver.manage().window().maximize();
    }
}

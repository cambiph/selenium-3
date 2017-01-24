package browser;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChromeBrowser implements Browser {

    private RemoteWebDriver remoteWebDriver;
    private ChromeDriverService chromeDriverService;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public ChromeBrowser() {
        setDriver();
    }

    private ChromeDriverService buildChromeDriverService() {
        if (null == chromeDriverService) {
            logger.log(Level.INFO, "Building ChromeDriver service ...");
            chromeDriverService = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File("src/test/resources/chromedriver.exe"))
                    .build();
        } else {
            logger.log(Level.INFO, "ChromeDriver service already set!");
        }
        return chromeDriverService;
    }

    private void startChromeDriverService() {
        if (null != chromeDriverService || !chromeDriverService.isRunning()) {
            try {
                logger.log(Level.INFO, "Starting ChromeDriver service ...");
                chromeDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDriver() {
        if (null == chromeDriverService) {
            logger.log(Level.INFO, "RemoteWebDriver not set yet, I'll build and start it for you.");
            buildChromeDriverService();
            startChromeDriverService();
        }
        if (null == remoteWebDriver) {
            remoteWebDriver = new RemoteWebDriver(chromeDriverService.getUrl(), DesiredCapabilities.chrome());
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
        if (null != chromeDriverService || chromeDriverService.isRunning()) {
            chromeDriverService.stop();
        }
    }
}

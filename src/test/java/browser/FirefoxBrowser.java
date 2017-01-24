package browser;

import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirefoxBrowser implements Browser {

    private RemoteWebDriver remoteWebDriver;
    private GeckoDriverService geckoDriverService;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public FirefoxBrowser() {
        setDriver();
    }

    private GeckoDriverService buildGeckoDriverService() {
        if (null == geckoDriverService) {
            logger.log(Level.INFO, "Building GeckoDriver service ...");
            geckoDriverService = new GeckoDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File("src/test/resources/geckodriver.exe"))
                    .build();
        } else {
            logger.log(Level.INFO, "GeckoDriver service already set!");
        }
        return geckoDriverService;
    }

    private void startGeckoDriverService() {
        if (null != geckoDriverService || !geckoDriverService.isRunning()) {
            try {
                logger.log(Level.INFO, "Starting GeckoDriver service ...");
                geckoDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDriver() {
        if (null == geckoDriverService) {
            logger.log(Level.INFO, "RemoteWebDriver not set yet, I'll build and start it for you.");
            buildGeckoDriverService();
            startGeckoDriverService();
        }
        if (null == remoteWebDriver) {
            remoteWebDriver = new RemoteWebDriver(geckoDriverService.getUrl(), DesiredCapabilities.firefox());
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
        if (null != geckoDriverService || geckoDriverService.isRunning()) {
            geckoDriverService.stop();
        }
    }
}

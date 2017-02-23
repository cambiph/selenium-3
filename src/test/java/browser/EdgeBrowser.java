package browser;

import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ProxyManager;

import java.io.File;
import java.io.IOException;

public class EdgeBrowser implements Browser {

    private RemoteWebDriver remoteWebDriver;
    private EdgeDriverService edgeDriverService;
    private ProxyManager proxyManager;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public EdgeBrowser() {
        proxyManager = new ProxyManager();
        setDriver();
    }

    private void buildEdgeDriverService() {
        if (null == edgeDriverService) {
            LOGGER.info("Building EdgeDriver service ...");
            edgeDriverService = new EdgeDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File("src/test/resources/MicrosoftWebDriver.exe"))
                    .build();
        } else {
            LOGGER.info("EdgeDriver service already set!");
        }
    }

    private void startEdgeDriverService() {
        if (null != edgeDriverService && !edgeDriverService.isRunning()) {
            try {
                LOGGER.info("Starting EdgeDriver service ...");
                edgeDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDriver() {
        if (null == edgeDriverService) {
            LOGGER.info("RemoteWebDriver not set yet, I'll build and start it for you.");
            buildEdgeDriverService();
            startEdgeDriverService();
        }
        if (null == remoteWebDriver) {
            remoteWebDriver = new RemoteWebDriver(edgeDriverService.getUrl(), buildDesiredCapabilities());
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
        if (null != edgeDriverService && edgeDriverService.isRunning()) {
            edgeDriverService.stop();
        }
    }

    private DesiredCapabilities buildDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if(null != System.getProperty("browsermob")) {
            desiredCapabilities = proxyManager.getDesiredCapabilities();
        }
        desiredCapabilities.setBrowserName("edge");
        return desiredCapabilities;
    }

    private void maximizeBrowserWindow(RemoteWebDriver driver) {
        driver.manage().window().maximize();
    }
}

package browser;

import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EdgeBrowser implements Browser {

    private RemoteWebDriver remoteWebDriver;
    private EdgeDriverService edgeDriverService;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public EdgeBrowser() {
        setDriver();
    }

    private EdgeDriverService buildEdgeDriverService() {
        if (null == edgeDriverService) {
            logger.log(Level.INFO, "Building EdgeDriver service ...");
            edgeDriverService = new EdgeDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File("src/test/resources/MicrosoftWebDriver.exe"))
                    .build();
        } else {
            logger.log(Level.INFO, "EdgeDriver service already set!");
        }
        return edgeDriverService;
    }

    private void startEdgeDriverService() {
        if (null != edgeDriverService || !edgeDriverService.isRunning()) {
            try {
                logger.log(Level.INFO, "Starting EdgeDriver service ...");
                edgeDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDriver() {
        if (null == edgeDriverService) {
            logger.log(Level.INFO, "RemoteWebDriver not set yet, I'll build and start it for you.");
            buildEdgeDriverService();
            startEdgeDriverService();
        }
        if (null == remoteWebDriver) {
            remoteWebDriver = new RemoteWebDriver(edgeDriverService.getUrl(), DesiredCapabilities.chrome());
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
        if (null != edgeDriverService || edgeDriverService.isRunning()) {
            edgeDriverService.stop();
        }
    }
}

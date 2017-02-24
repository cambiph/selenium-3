package be.vdab.chromedriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class ChromeDriverUpdater {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public void updateChromeDriverToLatest() throws IOException, URISyntaxException {
        if(null != System.getProperty("useLatestChromeDriver")) {
            LOGGER.info("Downloading latest chromedriver ...");
            new ChromeDriverDownloader().downloadBinary();
            LOGGER.info("Download successfull. Unzipping ...");
            new ChromeDriverUnzipper().unzipChromedriver();
            LOGGER.info("Chromedriver.exe updated to latest version");
        }
    }
}

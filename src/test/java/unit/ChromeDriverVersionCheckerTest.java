package unit;

import be.vdab.chromedriver.ChromeDriverVersionChecker;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ChromeDriverVersionCheckerTest {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Test
    public void responseShouldNotBeNull() throws IOException {
        LOGGER.info("Checking version number ...");
        String version = new ChromeDriverVersionChecker().getVersion();
        LOGGER.info("Version number equals: " + version);
        assertNotNull("Version could not be retrieved!", version);
    }
}

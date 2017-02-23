package unit;

import be.vdab.chromedriver.ChromeDriverDownloader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ChromeDriverDownloaderTest {

    @Test
    public void downloadBinary() throws Exception {
        File binary = new File("src/test/resources/chromedriver.zip");
        new ChromeDriverDownloader().downloadBinary();
        Assert.assertNotNull(binary.exists());
    }

}
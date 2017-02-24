package unit;

import be.vdab.geckodriver.GeckoDriverVersionChecker;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class GeckoDriverVersionCheckerTest {

    @Test
    public void getVersion() throws IOException {
        GeckoDriverVersionChecker geckoDriverVersionChecker = new GeckoDriverVersionChecker();
        Assert.assertNotNull(geckoDriverVersionChecker.getVersion());
    }
}

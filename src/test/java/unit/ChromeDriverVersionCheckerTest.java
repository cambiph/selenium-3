package unit;

import be.vdab.chromedriver.ChromeDriverVersionChecker;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ChromeDriverVersionCheckerTest {

    @Test
    public void responseShouldNotBeNull() throws IOException {
        assertNotNull(new ChromeDriverVersionChecker().getVersion());
    }
}

package be.vdab.chromedriver;

import be.vdab.utilities.DownloadHandler;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class ChromeDriverDownloader {

    private final String BASE_URL = "https://chromedriver.storage.googleapis.com/";
    private final String FILENAME = "chromedriver_win32.zip";
    private final String SAVE_PATH = "src/test/resources/";

    public void downloadBinary() throws IOException, URISyntaxException {
        DownloadHandler downloadHandler = new DownloadHandler(BASE_URL + new ChromeDriverVersionChecker().getVersion() + "/" + FILENAME);
        InputStream content = downloadHandler.getEntity().getContent();
        File chromeDriverZip = new File(SAVE_PATH + FILENAME);
        if(chromeDriverZip.exists()) {
            FileUtils.forceDelete(chromeDriverZip);
        }
        FileUtils.copyInputStreamToFile(content, new File(SAVE_PATH + FILENAME));
    }

}

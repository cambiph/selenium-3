package be.vdab.chromedriver;

import be.vdab.utilities.DownloadHandler;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class ChromeDriverDownloader {

    private final String  BASE_URL = "https://chromedriver.storage.googleapis.com/index.html?path=";

    public void downloadBinary() throws IOException, URISyntaxException {
        DownloadHandler downloadHandler = new DownloadHandler(BASE_URL + new ChromeDriverVersionChecker().getVersion());
        InputStream content = downloadHandler.getEntity().getContent();
        FileUtils.copyInputStreamToFile(content, new File("src/test/resources/chromedriver.zip"));
    }

}

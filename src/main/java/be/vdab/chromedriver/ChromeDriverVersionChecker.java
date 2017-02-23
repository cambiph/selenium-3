package be.vdab.chromedriver;

import be.vdab.utilities.DownloadHandler;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ChromeDriverVersionChecker  {

    private final String URL = "https://chromedriver.storage.googleapis.com/LATEST_RELEASE";
    private DownloadHandler downloadHandler;

    public ChromeDriverVersionChecker() throws IOException {
        this.downloadHandler = new DownloadHandler(URL);
    }

    public String getVersion() throws IOException {
        return IOUtils.toString(getContentFromResponse(), Charset.defaultCharset()).trim();
    }

    private InputStream getContentFromResponse() throws IOException {
        return downloadHandler.getEntity().getContent();
    }
}

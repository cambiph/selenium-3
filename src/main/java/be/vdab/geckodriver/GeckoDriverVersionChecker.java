package be.vdab.geckodriver;

import be.vdab.utilities.DownloadHandler;
import be.vdab.utilities.PropertiesLoader;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class GeckoDriverVersionChecker {

    private final PropertiesLoader LOADER = PropertiesLoader.getInstance();
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private DownloadHandler downloadHandler;

    public GeckoDriverVersionChecker() throws IOException {
        this.downloadHandler = new DownloadHandler(LOADER.getGeckoDriverReleaseUrl());
    }

    private String getResponseAsString() throws IOException {
        return IOUtils.toString(getContentFromResponse(), Charset.defaultCharset());
    }

    private InputStream getContentFromResponse() throws IOException {
        return downloadHandler.getEntity().getContent();
    }

    private JSONObject parseJson() throws IOException {
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(getResponseAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getVersion() {
        try {
            String version = parseJson().get("tag_name").toString();
            LOGGER.info("Latest version of Geckodriver is " + version);
            return version;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

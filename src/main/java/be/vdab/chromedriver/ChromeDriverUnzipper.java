package be.vdab.chromedriver;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ChromeDriverUnzipper {

    private final String SOURCE = "src/test/resources/chromedriver.zip";
    private final String DESTINATION = "src/test/resources/";

    public void unzip(){
        try {
            ZipFile zipFile = new ZipFile(SOURCE);
            zipFile.extractAll(DESTINATION);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}

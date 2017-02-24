package be.vdab.utilities;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Unzipper {

    public void unzip(String fileName, String source, String destination){
        try {
            ZipFile zipFile = new ZipFile(source);
            checkZipExistence(zipFile);
            zipFile.extractFile(fileName, destination);
            removeZipFileAfterExtraction(fileName, source, destination);
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeZipFileAfterExtraction(String fileName, String source, String destination) throws IOException {
        if (new File(destination + fileName).exists()){
            FileUtils.forceDelete(new File(source));
        }
    }

    private void checkZipExistence(ZipFile zipFile) {
        if (!zipFile.getFile().exists()) {
            throw new RuntimeException("Zipfile " + zipFile.getFile().getAbsolutePath() + " does not exist!");
        }
    }
}

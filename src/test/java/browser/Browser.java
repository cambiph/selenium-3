package browser;

import org.openqa.selenium.WebDriver;

public interface Browser {

    void setDriver();

    WebDriver getDriver();

    void killDriver();

}

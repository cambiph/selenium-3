package browser;

import org.openqa.selenium.WebDriver;

interface Browser {

    void setDriver();

    WebDriver getDriver();

    void killDriver();

}

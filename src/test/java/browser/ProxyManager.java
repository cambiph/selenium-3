package browser;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.InetSocketAddress;

public class ProxyManager {

    private BrowserMobProxy browserMobProxy;
    private Proxy seleniumProxy;
    private DesiredCapabilities desiredCapabilities;

    private static final String VDAB_PROXY_HOST = "vdabprdproxy.vdab.be";
    private static final int VDAB_PROXY_PORT = 8080;


    public ProxyManager(boolean setCorporateProxy) {
        browserMobProxy = new BrowserMobProxyServer();
        desiredCapabilities = new DesiredCapabilities();

        if (setCorporateProxy) {
            setCorporateProxy(browserMobProxy);
        }

        startBrowserMobProxy();
        seleniumProxy = createSeleniumProxy(browserMobProxy);
        setProxyCapability(desiredCapabilities, seleniumProxy);
    }

    private void setCorporateProxy(BrowserMobProxy browserMobProxy) {
        InetSocketAddress corporateProxyAddress = new InetSocketAddress(VDAB_PROXY_HOST, VDAB_PROXY_PORT);
        browserMobProxy.setChainedProxy(corporateProxyAddress);
    }

    private BrowserMobProxy startBrowserMobProxy() {
        filterRequestsWithExtensions("png", "jpg", "jpeg", "gif");
        browserMobProxy.start();
        return browserMobProxy;
    }

    private Proxy createSeleniumProxy(BrowserMobProxy browserMobProxy) {
        if (null == browserMobProxy) {
            startBrowserMobProxy();
        } else {
            seleniumProxy = ClientUtil.createSeleniumProxy(browserMobProxy);
        }
        return seleniumProxy;
    }

    private DesiredCapabilities setProxyCapability(DesiredCapabilities capabilities, Proxy seleniumProxy) {
        if (null == capabilities) {
            throw new RuntimeException("Capabilities have not been set yet!");
        }
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        return capabilities;
    }


    public DesiredCapabilities getDesiredCapabilities() {
        return desiredCapabilities;
    }

    private void filterRequestsWithExtensions(String... extensions) {
        for (String extension : extensions) {
            browserMobProxy.blacklistRequests("^http.*" + extension + "$", 200);
        }
    }
}

package be.vdab.utilities;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class DownloadHandler {

    private final PropertiesLoader LOADER = PropertiesLoader.getInstance();

    private HttpHost proxy;
    private HttpGet request;
    private HttpClient client;
    private HttpResponse response;

    public DownloadHandler(String url) throws IOException {
        if (LOADER.isCorporateProxyEnabled()) {
            setProxy();
        }
        setClient();
        setRequest(url);
        executeRequest();
    }

    public HttpEntity getEntity() {
        return response.getEntity();
    }

    private void setProxy() {
        this.proxy = new HttpHost("vdabprdproxy.vdab.be", 8080, "http");
    }

    private void setClient() {
        if (LOADER.isCorporateProxyEnabled()) {
            this.client = HttpClientBuilder
                    .create()
                    .setProxy(proxy)
                    .build();

        } else {
            this.client = HttpClientBuilder
                    .create()
                    .build();
        }
    }

    private void setRequest(String url) {
        this.request = new HttpGet(url);
    }

    private void executeRequest() throws IOException {
        if(null != client && null != request) {
            this.response = client.execute(request);
        } else {
            throw new RuntimeException("Client of Request is niet geinitialiseerd.");
        }
    }

}

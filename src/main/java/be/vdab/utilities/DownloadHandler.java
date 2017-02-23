package be.vdab.utilities;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DownloadHandler {

    private final PropertiesLoader LOADER = PropertiesLoader.getInstance();

    private HttpHost proxy;
    private HttpGet request;
    private HttpClient client;
    private HttpResponse response;

    public DownloadHandler(String url) throws IOException {
        if (null != url && !url.isEmpty()) {
            if (LOADER.isCorporateProxyEnabled()) {
                setProxy();
            }
            setClient();
            setRequest(url);
            executeRequest();

        } else {
            throw new RuntimeException("Target-url for downloadhandler is not set!");
        }
    }

    public HttpEntity getEntity() {
        return response.getEntity();
    }

    private void setProxy() {
        this.proxy = new HttpHost(LOADER.getCorporateProxyHost(), LOADER.getCorporateProxyPort(), "http");
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

    private void setRequest(String url) throws UnsupportedEncodingException {
        if (null != url && !url.isEmpty()) {
            this.request = new HttpGet(url);
        } else {
            throw new RuntimeException("URL is not set. Cannot continue to execute the request!");
        }
    }

    private void executeRequest() throws IOException {
        if (null != client && null != request) {
            this.response = client.execute(request);
        } else {
            throw new RuntimeException("Client or Request is not initialized!");
        }
    }

}

package browser;

import io.netty.handler.codec.http.*;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSource;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

public class ProxyManager {

    private static final int PORT = 8100;

    public ProxyManager() {
        HttpFiltersSource filtersSource = getFiltersSource();

        DefaultHttpProxyServer.bootstrap()
                .withPort(PORT)
                .withAllowLocalOnly(false)
                .withFiltersSource(filtersSource)
                .withName("BlockingFilterProxy")
                .start();
    }

    private static HttpFiltersSource getFiltersSource() {
        return new HttpFiltersSourceAdapter() {

            @Override
            public HttpFilters filterRequest(HttpRequest originalRequest) {

                return new HttpFiltersAdapter(originalRequest) {

                    @Override
                    public HttpResponse clientToProxyRequest(HttpObject httpObject) {

                        if (httpObject instanceof HttpRequest) {
                            HttpRequest request = (HttpRequest) httpObject;

                            System.out.println("Method URI : " + request.getMethod() + " " + request.getUri());

                            if (request.getUri().endsWith("png") || request.getUri().endsWith("jpeg")) {
                                return new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
                            }
                        }
                        return null;
                    }
                };
            }
        };
    }
}
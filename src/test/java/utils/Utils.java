package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URISyntaxException;


public class Utils {

    public static HttpResponse getResponse(String schema, String host, String path, String... query) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(schema);
        builder.setHost(host);
        builder.setPath(path);

        if(query != null) {
            for(int i=0; i<query.length; i++) {
                builder.setQuery(query[i]);
            }
        }

        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(httpGet);
        return response;
    }
}

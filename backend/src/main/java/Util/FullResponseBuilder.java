package Util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class FullResponseBuilder {

    public static String getFullResponse(String urlStr) throws IOException {

        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        StringBuilder fullResponseBuilder = new StringBuilder();

        // read status and message
        fullResponseBuilder.append(con.getResponseCode())
        .append(" ")
        .append(con.getResponseMessage())
        .append("\n");

        // read headers
        con.getHeaderFields().entrySet().stream()
        .filter(entry -> entry.getKey() != null)
        .forEach(entry -> {
            fullResponseBuilder.append(entry.getKey()).append(": ");
            List headerValues = entry.getValue();
            Iterator it = headerValues.iterator();
            if (it.hasNext()) {
                fullResponseBuilder.append(it.next());
                while (it.hasNext()) {
                    fullResponseBuilder.append(", ").append(it.next());
                }
            }
            fullResponseBuilder.append("\n");
        // read response content
            
        
    });
    con.disconnect();
    return fullResponseBuilder.toString();
}
}

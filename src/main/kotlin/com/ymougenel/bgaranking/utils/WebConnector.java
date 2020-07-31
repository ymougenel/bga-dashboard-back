package com.ymougenel.bgaranking.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class WebConnector {

    private static String GBA_URL = "https://boardgamearena.com/gamepanel/gamepanel/getRanking.html" + "?game=1127&start=0&mode=arena";
    private static DefaultHttpClient httpClient = new DefaultHttpClient();

    public static String getRanks(String gameId, long from) throws IOException, URISyntaxException {
        String url = buildUrl(gameId, from);
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        HttpEntity httpEntity = response.getEntity();
        return EntityUtils.toString(httpEntity);
    }

    private static String buildUrl(String gameid, long start) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(GBA_URL);
        builder.setParameter("game", gameid)
                .setParameter("start", String.valueOf(start));
        return builder.toString();
    }
}

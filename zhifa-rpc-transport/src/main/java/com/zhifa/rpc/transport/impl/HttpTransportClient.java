package com.zhifa.rpc.transport.impl;

import com.zhifa.rpc.Peer;
import com.zhifa.rpc.transport.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 11:27
 */
public class HttpTransportClient implements TransportClient {

    private String url;

    @Override
    public void connet(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            IOUtils.copy(data, httpURLConnection.getOutputStream());
            int resultCode = httpURLConnection.getResponseCode();
            if (resultCode == HttpURLConnection.HTTP_OK) {
                return httpURLConnection.getInputStream();
            } else {
                return httpURLConnection.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}

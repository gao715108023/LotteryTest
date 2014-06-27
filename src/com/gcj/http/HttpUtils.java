package com.gcj.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gaochuanjun on 14-4-17.
 */
public class HttpUtils {

    private URL url;
    private HttpURLConnection httpURLConnection;

    public void doGet() {
        try {
            url = new URL("http://127.0.0.1:9089/sys/observation?@=stock.pop");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setIfModifiedSince(999999999);
            httpURLConnection.setRequestProperty("userid", "test");
            //httpURLConnection.setRequestProperty("key", "{%22GroupingID%22:3}");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                System.out.println(temp);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }
    }

    public static void main(String[] args) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.doGet();
    }
}

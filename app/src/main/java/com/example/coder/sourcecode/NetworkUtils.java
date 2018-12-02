package com.example.coder.sourcecode;

import android.net.Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    static String getUrlResponse(String queryUrl){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String urlResponse = null;

        try {

            Uri uriBuilder = Uri.parse(queryUrl)
                    .buildUpon().build();

            URL requestURL = new URL(uriBuilder.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

            if(builder.length() == 0)
                return null;

            urlResponse = builder.toString();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return urlResponse;
    }
}

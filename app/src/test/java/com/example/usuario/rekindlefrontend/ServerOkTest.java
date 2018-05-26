package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ServerOkTest {
    @Test
    public void ContactServer() throws Exception {

        String url = "http://10.4.41.147:8080/test";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder sb = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();

        String jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        assertEquals(responseCode, 200);
    }
}
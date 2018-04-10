package com.example.usuario.rekindlefrontend;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import android.util.Log;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ComunicacionUsuarios {

    public static boolean test2(String url) throws Exception{
        //Conexion
        url += "/test2";
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
        if (responseCode == 200) return true;
        else return false;
    }

    public static boolean registrarVoluntario(String url, ArrayList<String> param) throws
            Exception {

        boolean b = false;

        //Conexion
        url += "/registrarVoluntario";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");


        //Parametros
        /*String data = URLEncoder.encode("nombre", "UTF-8")
                + "=" + URLEncoder.encode(param.get(0), "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(param.get(1), "UTF-8");

        data += "&" + URLEncoder.encode("password", "UTF-8")
                + "=" + URLEncoder.encode(param.get(2), "UTF-8");

        data += "&" + URLEncoder.encode("apellido1", "UTF-8")
                + "=" + URLEncoder.encode(param.get(3), "UTF-8");

        data += "&" + URLEncoder.encode("apellido2", "UTF-8")
                + "=" + URLEncoder.encode(param.get(4), "UTF-8");*/

        JSONObject json = new JSONObject();
        json.put("nombre", param.get(0));
        json.put("email", param.get(1));
        json.put("password", param.get(2));
        json.put("apellido1", param.get(3));
        json.put("apellido2", param.get(4));

        String s = json.toString();
        byte[] outputBytes = s.getBytes("UTF-8");


        // Send post request
        con.setDoOutput(true);
        OutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.write(outputBytes);
        wr.flush();
        wr.close();
        try {
            String resp = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                resp += inputLine;
            }
            in.close();


            System.out.println("resp: " + resp);

            //Tratar Response Code
            int responseCode = con.getResponseCode();
            if (responseCode == 200) b = true;
            else b = false;

        } catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return b;
    }

    public static boolean registrarRefugiado(String url, ArrayList<String> param) throws Exception{
        //Conexion
        url += "/registrarRefugiado";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");

        //Parametros

        String data = URLEncoder.encode("nombre", "UTF-8")
                + "=" + URLEncoder.encode(param.get(0), "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(param.get(1), "UTF-8");

        data += "&" + URLEncoder.encode("password", "UTF-8")
                + "=" + URLEncoder.encode(param.get(2), "UTF-8");

        data += "&" + URLEncoder.encode("apellido1", "UTF-8")
                + "=" + URLEncoder.encode(param.get(3), "UTF-8");

        data += "&" + URLEncoder.encode("apellido2", "UTF-8")
                + "=" + URLEncoder.encode(param.get(4), "UTF-8");

        data += "&" + URLEncoder.encode("telefono", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("nacimiento", "UTF-8")
                + "=" + URLEncoder.encode(param.get(6), "UTF-8");

        data += "&" + URLEncoder.encode("sexo", "UTF-8")
                + "=" + URLEncoder.encode(param.get(7), "UTF-8");

        data += "&" + URLEncoder.encode("pais", "UTF-8")
                + "=" + URLEncoder.encode(param.get(8), "UTF-8");

        data += "&" + URLEncoder.encode("pueblo", "UTF-8")
                + "=" + URLEncoder.encode(param.get(9), "UTF-8");

        data += "&" + URLEncoder.encode("etnia", "UTF-8")
                + "=" + URLEncoder.encode(param.get(10), "UTF-8");

        data += "&" + URLEncoder.encode("gs", "UTF-8")
                + "=" + URLEncoder.encode(param.get(11), "UTF-8");

        data += "&" + URLEncoder.encode("color_ojos", "UTF-8")
                + "=" + URLEncoder.encode(param.get(12), "UTF-8");

        System.out.println("DATA: " + data);

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

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
        if (responseCode == 200) return true;
        else return false;

    }

    public static boolean modificarPerfil(String url, ArrayList<String> param) throws Exception{
        //Conexion
        url += "/modificarPerfil";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("nombre", param.get(0));
        json.put("apellido1", param.get(1));
        json.put("apellido2", param.get(2));
        json.put("email", param.get(3));
        json.put("telefono", param.get(4));
        json.put("nacimiento", param.get(5));
        json.put("sexo", param.get(6));
        json.put("pais", param.get(7));
        json.put("pueblo", param.get(8));
        json.put("etnia", param.get(9));
        json.put("sangre", param.get(10));
        json.put("ojos", param.get(11));

        String s = json.toString();
        byte[] outputBytes = s.getBytes("UTF-8");


        // Send post request
        con.setDoOutput(true);
        OutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.write(outputBytes);
        wr.flush();
        wr.close();

        //leer respuesta
        String resp = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder sb = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            resp += inputLine;
        }
        in.close();

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        if (responseCode == 200) return true;
        else return false;
    }

}

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
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Comunicacion {

    public static boolean registrarVoluntario(String url, ArrayList<String> param) throws
            Exception {

        //Conexion
        url += "/test2/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-type", "application/json");

        /*//Parametros
        String data = URLEncoder.encode("nombre", "UTF-8")
                + "=" + URLEncoder.encode(param.get(0), "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(param.get(1), "UTF-8");

        data += "&" + URLEncoder.encode("password", "UTF-8")
                + "=" + URLEncoder.encode(param.get(2), "UTF-8");

        data += "&" + URLEncoder.encode("apellido1", "UTF-8")
                + "=" + URLEncoder.encode(param.get(3), "UTF-8");

        data += "&" + URLEncoder.encode("apellido2", "UTF-8")
                + "=" + URLEncoder.encode(param.get(4), "UTF-8");*/

        // Send post request
        /*con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();*/

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

    public static boolean registrarRefugiado(String url, ArrayList<String> param) throws Exception{
        //Conexion
        //url += "/registrarRefugiado/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/json");

        //Parametros
        String data = "/registrarRefugiado/";

        data += URLEncoder.encode("nombre", "UTF-8")
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

    public static boolean crearAlojamiento(String url, ArrayList<String> param) throws Exception{
        //Conexion
        url += "/crearAlojamiento/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/json");

        //Parametros
        String data = URLEncoder.encode("nombre", "UTF-8")
                + "=" + URLEncoder.encode(param.get(0), "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(param.get(1), "UTF-8");

        data += "&" + URLEncoder.encode("telefono", "UTF-8")
                + "=" + URLEncoder.encode(param.get(2), "UTF-8");

        data += "&" + URLEncoder.encode("direccion", "UTF-8")
                + "=" + URLEncoder.encode(param.get(3), "UTF-8");

        data += "&" + URLEncoder.encode("limite-peticiones", "UTF-8")
                + "=" + URLEncoder.encode(param.get(4), "UTF-8");

        data += "&" + URLEncoder.encode("fecha-limite", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("descripcion", "UTF-8")
                + "=" + URLEncoder.encode(param.get(6), "UTF-8");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        if (responseCode == 200) return true;
        else return false;
    }

    public static boolean crearDonacion(String url, ArrayList<String> param) throws Exception{
        //Conexion
        url += "/crearDonacion/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/json");

        //Parametros
        String data = URLEncoder.encode("nombre", "UTF-8")
                + "=" + URLEncoder.encode(param.get(0), "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(param.get(1), "UTF-8");

        data += "&" + URLEncoder.encode("telefono", "UTF-8")
                + "=" + URLEncoder.encode(param.get(2), "UTF-8");

        data += "&" + URLEncoder.encode("direccion", "UTF-8")
                + "=" + URLEncoder.encode(param.get(3), "UTF-8");

        data += "&" + URLEncoder.encode("limite-peticiones", "UTF-8")
                + "=" + URLEncoder.encode(param.get(4), "UTF-8");

        data += "&" + URLEncoder.encode("hora-inicio", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("hora-fin", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("descripcion", "UTF-8")
                + "=" + URLEncoder.encode(param.get(6), "UTF-8");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        if (responseCode == 200) return true;
        else return false;
    }

    public static boolean crearCursoEducativo(String url, ArrayList<String> param) throws Exception{
        //Conexion
        url += "/crearCursoEducativo/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/json");

        //Parametros
        String data = URLEncoder.encode("nombre", "UTF-8")
                + "=" + URLEncoder.encode(param.get(0), "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(param.get(1), "UTF-8");

        data += "&" + URLEncoder.encode("telefono", "UTF-8")
                + "=" + URLEncoder.encode(param.get(2), "UTF-8");

        data += "&" + URLEncoder.encode("direccion", "UTF-8")
                + "=" + URLEncoder.encode(param.get(3), "UTF-8");

        data += "&" + URLEncoder.encode("ambito", "UTF-8")
                + "=" + URLEncoder.encode(param.get(4), "UTF-8");

        data += "&" + URLEncoder.encode("requisitos-previos", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("horario", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("plazas-disponibles", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("precio", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("descripcion", "UTF-8")
                + "=" + URLEncoder.encode(param.get(6), "UTF-8");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        if (responseCode == 200) return true;
        else return false;
    }

    public static boolean crearOfertaEmpleo(String url, ArrayList<String> param) throws Exception{
        //Conexion
        url += "/crearOfertaEmpleo/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/json");

        //Parametros
        String data = URLEncoder.encode("nombre", "UTF-8")
                + "=" + URLEncoder.encode(param.get(0), "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(param.get(1), "UTF-8");

        data += "&" + URLEncoder.encode("telefono", "UTF-8")
                + "=" + URLEncoder.encode(param.get(2), "UTF-8");

        data += "&" + URLEncoder.encode("direccion", "UTF-8")
                + "=" + URLEncoder.encode(param.get(3), "UTF-8");

        data += "&" + URLEncoder.encode("puesto", "UTF-8")
                + "=" + URLEncoder.encode(param.get(4), "UTF-8");

        data += "&" + URLEncoder.encode("requisitos-necesarios", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("jornada", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("horas-semanales", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("duracion", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("plazas-disponibles", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("sueldo", "UTF-8")
                + "=" + URLEncoder.encode(param.get(5), "UTF-8");

        data += "&" + URLEncoder.encode("descripcion", "UTF-8")
                + "=" + URLEncoder.encode(param.get(6), "UTF-8");

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



}

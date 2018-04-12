package com.example.usuario.rekindlefrontend;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ComunicacionServicios {

    public static boolean crearAlojamiento(String url, ArrayList<String> param) throws Exception{
        //Conexion
        url += "/crearAlojamiento";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("name", param.get(0));
        json.put("volunteer", param.get(1));
        json.put("phoneNumber", param.get(2));
        json.put("adress", param.get(3));
        json.put("places", param.get(4));
        json.put("dateLimit", param.get(5));
        json.put("description", param.get(6));

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

        System.out.println("resp: " + resp);

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        System.out.println("Response code = "+responseCode);
        if (responseCode == 200) return true;
        else return false;
    }

    public static boolean crearDonacion(String url, ArrayList<String> param) throws Exception{
        //Conexion
        url += "/crearDonacion";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("name", param.get(0));
        json.put("volunteer", param.get(1));
        json.put("phoneNumber", param.get(2));
        json.put("adress", param.get(3));
        json.put("places", param.get(4));
        json.put("startTime", param.get(5));
        json.put("endTime", param.get(6));
        json.put("description", param.get(7));

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

        System.out.println("resp: " + resp);

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        System.out.println("Response code = "+responseCode);
        if (responseCode == 200) return true;
        else return false;
    }

    public static boolean crearCursoEducativo(String url, ArrayList<String> param) throws Exception{
        //Conexion
        url += "/crearCursoEducativo";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("name", param.get(0));
        json.put("volunteer", param.get(1));
        json.put("phoneNumber", param.get(2));
        json.put("adress", param.get(3));
        json.put("ambit", param.get(4));
        json.put("requirements", param.get(5));
        json.put("schedule", param.get(6));
        json.put("places", param.get(7));
        json.put("price", param.get(8));
        json.put("description", param.get(9));

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

        System.out.println("resp: " + resp);

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        System.out.println("Response code = "+responseCode);
        if (responseCode == 200) return true;
        else return false;
    }

    public static boolean crearOfertaEmpleo(String url, ArrayList<String> param) throws Exception{
        //Conexion
        url += "/crearOfertaEmpleo";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("name", param.get(0));
        json.put("volunteer", param.get(1));
        json.put("phoneNumber", param.get(2));
        json.put("adress", param.get(3));
        json.put("charge", param.get(4));
        json.put("requirements", param.get(5));
        json.put("hoursDay", param.get(6));
        json.put("hoursWeek", param.get(7));
        json.put("contractDuration", param.get(8));
        json.put("salary", param.get(9));
        json.put("places", param.get(10));
        json.put("description", param.get(11));

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

        System.out.println("resp: " + resp);

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        System.out.println("Response code = "+responseCode);
        if (responseCode == 200) return true;
        else return false;
    }

    /*public ArrayList<Servicio> listarServicios(String url) throws Exception{

        ArrayList<Servicio> result;
        //Conexion
        url += "/servicios";
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
        /*int responseCode = con.getResponseCode();
        if (responseCode == 200) return true;
        else return false;

        return result;
    }*/
}

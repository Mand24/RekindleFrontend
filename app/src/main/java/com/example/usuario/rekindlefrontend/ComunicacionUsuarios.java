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


        JSONObject json = new JSONObject();
        json.put("mail", param.get(0));
        json.put("password", param.get(1));
        json.put("name", param.get(2));
        json.put("surname1", param.get(3));
        json.put("surname2", param.get(4));

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
        if (responseCode == 200) b = true;
        else b = false;


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

        JSONObject json = new JSONObject();
        json.put("mail", param.get(0));
        json.put("password", param.get(1));
        json.put("name", param.get(2));
        json.put("surname1", param.get(3));
        json.put("surname2", param.get(4));
        json.put("phoneNumber", param.get(5));
        json.put("birthdate", param.get(6));
        json.put("sex", param.get(7));
        json.put("country", param.get(8));
        json.put("town", param.get(9));
        json.put("ethnic", param.get(10));
        json.put("bloodType", param.get(11));
        json.put("eyeColor", param.get(12));

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

    public static boolean iniciarSesion(String url, String email, String password) throws
            Exception{
        //Conexion
        url += "/inicioSesion";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("mail", email);
        json.put("password", password);

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

        while ((inputLine = in.readLine()) != null) {
            resp += inputLine;
        }
        in.close();

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        if (responseCode == 200) return true;
        else return false;
    }

    public static Refugiado verPerfil(String url, String param) throws Exception {

        //Conexion
        url += "/verPerfilRefugiado";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("mail", param);

        String s = json.toString();
        byte[] outputBytes = s.getBytes("UTF-8");

        // Send post request
        con.setDoOutput(true);
        OutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.write(outputBytes);
        wr.flush();
        wr.close();

        //leer respuesta
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder sb = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();

        String jsonString = sb.toString();

        System.out.println("resp: "+sb.toString());

        /*

        String[] arr = jsonArray.toString().replace("},{", " ,").split(" ");
        String s = "{\"mail\":\"rogerio@gmail.com\",\"password\":\"12345\",\"name\":\"rogerio\","
                + "\"surname1\":\"poch\",\"surname2\":null,\"phoneNumber\":null,\"birthdate\":null,\"sex\":null,\"country\":null,\"town\":null,\"ethnic\":null,\"bloodType\":null,\"eyeColor\":null}";
        String[] arr = sb.replace("},{", " ,").split(" ");
        {"mail":"rogerio@gmail.com","password":"12345","name":"rogerio","surname1":"poch",
                "surname2":null,"phoneNumber":null,"birthdate":null,"sex":null,"country":null,"town":null,"ethnic":null,"bloodType":null,"eyeColor":null}

        System.out.println("JSON: " + jsonString);*/

        Refugiado nuevo = new Refugiado();

        JSONObject myjson = new JSONObject(jsonString);

        if (con.getResponseCode() == 200) {
            JSONArray nameArray = myjson.names();
            JSONArray valArray = myjson.toJSONArray(nameArray);
            nuevo.setMail(valArray.getString(0));
            nuevo.setPassword(valArray.getString(1));
            nuevo.setName(valArray.getString(2));
            nuevo.setSurname1(valArray.getString(3));
            nuevo.setSurname2(valArray.getString(4));
            nuevo.setPhoneNumber(valArray.getString(5));
            nuevo.setBirthDate(valArray.getString(6));
            nuevo.setSex(valArray.getString(7));
            nuevo.setCountry(valArray.getString(8));
            nuevo.setTown(valArray.getString(9));
            nuevo.setEthnic(valArray.getString(10));
            nuevo.setBloodType(valArray.getString(11));
            nuevo.setEyeColor(valArray.getString(12));
            return nuevo;
        }else {
            return null;
        }
    }


    public static boolean modificarPerfil(String url, Refugiado refugiado) throws Exception{
        //Conexion
        url += "/modificarPerfil";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("mail", refugiado.getMail());
        json.put("name", refugiado.getName());
        json.put("surname1", refugiado.getSurname1());
        json.put("surname2", refugiado.getSurname2());
        json.put("phoneNumber", refugiado.getPhoneNumber());
        json.put("birthdate", refugiado.getBirthDate());
        json.put("sex", refugiado.getSex());
        json.put("country", refugiado.getCountry());
        json.put("town", refugiado.getTown());
        json.put("ethnic", refugiado.getEthnic());
        json.put("bloodType", refugiado.getBloodType());
        json.put("eyeColor", refugiado.getEyeColor());

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

        while ((inputLine = in.readLine()) != null) {
            resp += inputLine;
        }
        in.close();

        System.out.println("resp: "+resp);

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        System.out.println("codigo = "+responseCode);
        if (resp.equals("True")) return true;
        else return false;
    }

    public static boolean cambiarPassword(String url, ArrayList<String> param) throws Exception{
        //Conexion
        url += "/cambiarPasswordRefugiado";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("mail", param.get(0));
        json.put("password", param.get(1));
        json.put("newPassword", param.get(2));

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

        while ((inputLine = in.readLine()) != null) {
            resp += inputLine;
        }
        in.close();

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        System.out.println("codigo = "+responseCode);
        if (responseCode == 200) return true;
        else return false;
    }

}

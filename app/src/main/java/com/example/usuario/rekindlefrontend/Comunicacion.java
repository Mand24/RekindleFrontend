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
import java.util.ArrayList;

public class Comunicacion {

    public static boolean registrar_voluntario(String url, String nombre, String email, String
            password, String apellido1, String apellido2) throws Exception {

        //String url = "http://selfsolve.apple.com/wcResults.do";
        url += "/registrarVoluntario";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        //con.setRequestProperty("User-Agent", USER_AGENT);
        //host???
        //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-type", "application/json");

        //String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
        String urlparametros = "nombre="+nombre+"&email="+email+"&password="+password+"&apellido1"
                + "="+apellido1+"&apellido2="+apellido2;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlparametros);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        if (responseCode == 200) return true;
        else return false;

    }

    public static boolean registrar_refugiado(String url, ArrayList<String> param)throws Exception{
        //Conexion
        url += "/registrarRefugiado";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/json");

        //Parametros
        String urlparametros = "nombre="+param
                .get(0)+"&email="+param.get(1)+"&password="+param.get(2)+"&apellido1"
                + "="+param.get(3)+"&apellido2="+param.get(4)+"&tlf="+param.get(5)
                +"&nacimiento="+param.get(6)+"&sexo="+param.get(7)+"&pais="+param.get(8)
                +"&pueblo="+param.get(9)+"&etnica="+param.get(10)+"&gs="+param.get(11)
                +"&eye="+param.get(12);

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlparametros);
        wr.flush();
        wr.close();

        //Tratar Response Code
        int responseCode = con.getResponseCode();
        if (responseCode == 200) return true;
        else return false;

    }

}

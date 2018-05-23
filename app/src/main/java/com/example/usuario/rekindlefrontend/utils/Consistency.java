package com.example.usuario.rekindlefrontend.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.google.gson.Gson;

/**
 * Created by ORION on 15/05/2018.
 */

public class Consistency {

    public static void saveUser(User user, Context ctx){

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor miEditor = datos.edit();
        if(user == null){
            miEditor.putString("usuario", "");
        }
        else {
            Gson gson = new Gson();
            String json = gson.toJson(user);
            miEditor.putString("usuario", json);
        }
        miEditor.apply();
    }

    public static User getUser(Context ctx){

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(ctx);
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        User user = gson.fromJson(json, User.class);
        return user;
    }
}

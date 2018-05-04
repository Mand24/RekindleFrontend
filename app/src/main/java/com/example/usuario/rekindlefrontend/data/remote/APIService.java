package com.example.usuario.rekindlefrontend.data.remote;

import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Voluntario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    //LLAMADAS RELACIONADAS CON EL LOGIN
    @POST("/login")
    @FormUrlEncoded
    Call<Usuario> login(@Field("mail") String mail, @Field("password") String password);


    //LLAMADAS RELACIONADAS CON USUARIOS
    @PUT("/cambiarPassword/{mail}")
    @FormUrlEncoded
    Call<Void> cambiarPassword(@Path("mail") String mail, @Field("passwordOld") String passwordOld, @Field
            ("passwordNew") String passwordNew);

    @PUT("/recuperarPassword/{mail}")
    @FormUrlEncoded
    Call<Void> recuperarPassword(@Path("mail") String mail, @Field("passwordOld") String
            password);

    //LLAMADAS RELACIONADAS CON USUARIOS REFUGIADOS
    @GET("/refugiados")
    Call<ArrayList<Refugiado>> buscarRefugiados(
            @Query("name") String name,
            @Query("surname1") String surname1,
            @Query("surname2") String surname2,
            @Query("birthdate") String birthdate,
            @Query("sex") String sex,
            @Query("country") String country,
            @Query("town") String town,
            @Query("ethnic") String ethnic,
            @Query("blood") String blood,
            @Query("eye") String eye);

    @POST("/refugiados")
    Call<Refugiado> createRefugiado(@Body Refugiado refugiado);

    @GET("/refugiados/{mail}")
    Call<Refugiado> obtenerRefugiado(@Path("mail") String mail);

    @PUT("/refugiados/{mail}")
    Call<Void> actualizarRefugiado(@Path("mail") String mail, @Body Refugiado refugiado);


    //LLAMADAS RELACIONADAS CON USUARIOS VOLUNTARIOS
    @POST("/voluntarios")
    Call<Voluntario> createVoluntario(@Body Voluntario voluntario);

    @GET("/voluntarios/{mail}")
    Call<Voluntario> obtenerVoluntario(@Path("mail") String mail);

    @PUT("/voluntarios/{mail}")
    Call<Void> actualizarVoluntario(@Path("mail") String mail, @Body Voluntario voluntario);


    //LLAMADAS RELACIONADAS CON SERVICIOS
/*
    @GET("/refugiados/{mail}")
    Call<> obtenerRefugiado(@Path("mail") String mail);
*/


    @GET("/test2")
    Call<Refugiado> prueba();
}

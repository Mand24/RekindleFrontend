package com.example.usuario.rekindlefrontend.data.remote;

import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Voluntario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    /*@POST("/refugiados")
    @FormUrlEncoded
    Call<Refugiado> createRefugiado(@Field("mail") String mail,
            @Field("password") String password,
            @Field("name") String name,
            @Field("surname1") String surname1,
            @Field("surname2") String surname2,
            @Field("phoneNumber") String phoneNumber,
            @Field("birthDate") String birthDate,
            @Field("sex") String sex,
            @Field("country") String country,
            @Field("town") String town,
            @Field("ethnic") String ethnic,
            @Field("bloodType") String bloodType,
            @Field("eyeColor") String eyeColor);*/

    //LLAMADAS RELACIONADAS CON EL LOGIN
    @POST("/login")
    Call<Usuario> login(@Field("mail") String mail, @Field("password") String password);

    //LLAMADAS RELACIONADAS CON USUARIOS REFUGIADOS
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
//    @GET("/refugiados/{mail}")
//    Call<> obtenerRefugiado(@Path("mail") String mail);


    @GET("/test2")
    Call<Refugiado> prueba();
}

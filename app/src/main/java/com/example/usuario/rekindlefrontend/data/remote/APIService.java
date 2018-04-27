package com.example.usuario.rekindlefrontend.data.remote;

import com.example.usuario.rekindlefrontend.data.entity.Refugiado;
import com.example.usuario.rekindlefrontend.data.entity.Voluntario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    /*Call<Refugiado> createRefugiado(@Field("mail") String mail,
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

    @POST("/refugiados")
    @FormUrlEncoded
    Call<Refugiado> createRefugiado(@Body Refugiado refugiado);

    @POST("/voluntarios")
    @FormUrlEncoded
    Call<Voluntario> createVoluntario(@Body Voluntario voluntario);
}

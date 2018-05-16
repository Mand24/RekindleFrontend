package com.example.usuario.rekindlefrontend.data.remote;

import android.util.Pair;

import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Alojamiento;
import com.example.usuario.rekindlefrontend.data.entity.servicio.CursoEducativo;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Donacion;
import com.example.usuario.rekindlefrontend.data.entity.servicio.OfertaEmpleo;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Servicio;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Voluntario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
    Call<Void> recuperarPassword(@Path("mail") String mail, @Field("passwordNew") String
                    password);

    @GET("/usuarios/{mail}/chats")
    Call<ArrayList<Chat>> getChats(@Path("mail") String mail);

    @GET("/usuarios/{mail}/chats")
    Call<Chat> getChat(@Path("mail") String mail, @Query("mail1") String mail1, @Query("mail2")
            String
            mail2);
    @POST("/usuarios/{mail}/chats")
    Call<Void> newChat(@Path("mail") String mail, @Body Chat chat);

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
    @GET("/servicios")
    Call<ArrayList<Servicio>> obtenerServicios();

    @GET("/servicios/{mail}/{tipo}")
    Call<ArrayList<Servicio>> obtenerMisServicios(@Path("mail") String mail, @Path
            ("tipo") int tipo);

    @GET("/alojamientos/{id}")
    Call<Alojamiento> getAlojamiento(@Path("id") int id);

    @GET("/cursos/{id}")
    Call<CursoEducativo> getCurso(@Path("id") int id);

    @GET("/donaciones/{id}")
    Call<Donacion> getDonacion(@Path("id") int id);

    @GET("/empleos/{id}")
    Call<OfertaEmpleo> getEmpleo(@Path("id") int id);

    @PUT("/alojamientos/{id}")
    Call<Void> editarAlojamiento(@Path("id") int id, @Body Alojamiento alojamiento);

    @PUT("/cursos/{id}")
    Call<Void> editarCurso(@Path("id") int id, @Body CursoEducativo curso);

    @PUT("/donaciones/{id}")
    Call<Void> editarDonacion(@Path("id") int id, @Body Donacion donacion);

    @PUT("/empleos/{id}")
    Call<Void> editarEmpleo(@Path("id") int id, @Body OfertaEmpleo empleo);

    @POST("/alojamientos")
    Call<Void> crearAlojamiento(@Body Alojamiento alojamiento);

    @POST("/donaciones")
    Call<Void> crearDonacion(@Body Donacion donacion);

    @POST("/empleos")
    Call<Void> crearOferta(@Body OfertaEmpleo empleo);

    @POST("/cursos")
    Call<Void> crearEducacion(@Body CursoEducativo educacion);

    @DELETE("/servicios/{id}/{tipo}")
    Call<Void> eliminarServicio(@Path("id") int id, @Path("tipo") char tipo);

    @GET("/test2")
    Call<Refugiado> prueba();
}

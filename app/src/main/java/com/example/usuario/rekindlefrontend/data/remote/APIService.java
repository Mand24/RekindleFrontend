package com.example.usuario.rekindlefrontend.data.remote;

import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.chat.Message;
import com.example.usuario.rekindlefrontend.data.entity.reports.Report;
import com.example.usuario.rekindlefrontend.data.entity.service.Donation;
import com.example.usuario.rekindlefrontend.data.entity.service.Education;
import com.example.usuario.rekindlefrontend.data.entity.service.Job;
import com.example.usuario.rekindlefrontend.data.entity.service.Lodge;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;

import java.util.ArrayList;

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
    Call<User> login(@Field("mail") String mail, @Field("password") String password);


    //LLAMADAS RELACIONADAS CON USUARIOS
    @PUT("/cambiarPassword/{mail}")
    @FormUrlEncoded
    Call<Void> cambiarPassword(@Path("mail") String mail, @Field("passwordOld") String passwordOld,
            @Field
                    ("passwordNew") String passwordNew);

    @PUT("/recuperarPassword/{mail}")
    @FormUrlEncoded
    Call<Void> recuperarPassword(@Path("mail") String mail, @Field("passwordNew") String
            password);

    @GET("/usuarios/{mail}/chats")
    Call<ArrayList<Chat>> getChats(@Path("mail") String mail);

    /*@GET("/usuarios/{mail}/chats/{idchat}")
    Call<Chat> getChat(@Path("mail") String mail, @Query("mail1") String mail1, @Query("mail2")
            String
            mail2);*/

    @POST("/usuarios/{mail}/chats")
    Call<Chat> newChat(@Path("mail") String mail, @Body Chat chat);//back se encarga de mirar si existe!!!

    //LLAMADAS RELACIONADAS CON USUARIOS REFUGIADOS
    @GET("/refugiados")
    Call<ArrayList<Refugee>> buscarRefugiados(
            @Query("name") String name,
            @Query("surname1") String surname1,
            @Query("surname2") String surname2,
            @Query("birthdate") String birthdate,
            @Query("sex") String sex,
            @Query("country") String country,
            @Query("town") String town,
            @Query("ethnic") String ethnic,
            @Query("blood") String blood,
            @Query("eye") String eye,
            @Query("mail") String mail);

    @POST("/refugiados")
    Call<Void> createRefugiado(@Body Refugee refugee);

    @GET("/refugiados/{mail}")
    Call<Refugee> obtenerRefugiado(@Path("mail") String mail);

    @PUT("/refugiados/{mail}")
    Call<Void> actualizarRefugiado(@Path("mail") String mail, @Body Refugee refugee);


    //LLAMADAS RELACIONADAS CON USUARIOS VOLUNTARIOS
    @POST("/voluntarios")
    Call<Void> createVoluntario(@Body Volunteer volunteer);

    @GET("/voluntarios/{mail}")
    Call<Volunteer> obtenerVoluntario(@Path("mail") String mail);

    @PUT("/voluntarios/{mail}")
    Call<Void> actualizarVoluntario(@Path("mail") String mail, @Body Volunteer volunteer);

    //LLAMADAS RELACIONADAS CON SERVICIOS
/*
    @GET("/mRefugees/{mail}")
    Call<> obtenerRefugiado(@Path("mail") String mail);
*/
    @GET("/servicios")
    Call<ArrayList<Service>> obtenerServicios();

    @GET("/servicios/{mail}/{tipo}")
    Call<ArrayList<Service>> obtenerMisServicios(@Path("mail") String mail, @Path
            ("tipo") String tipo);

    @GET("/alojamientos/{id}")
    Call<Lodge> getAlojamiento(@Path("id") int id);

    @GET("/cursos/{id}")
    Call<Education> getCurso(@Path("id") int id);

    @GET("/donaciones/{id}")
    Call<Donation> getDonacion(@Path("id") int id);

    @GET("/empleos/{id}")
    Call<Job> getEmpleo(@Path("id") int id);

    @PUT("/alojamientos/{id}")
    Call<Void> editarAlojamiento(@Path("id") int id, @Body Lodge lodge);

    @PUT("/cursos/{id}")
    Call<Void> editarCurso(@Path("id") int id, @Body Education curso);

    @PUT("/donaciones/{id}")
    Call<Void> editarDonacion(@Path("id") int id, @Body Donation donation);

    @PUT("/empleos/{id}")
    Call<Void> editarEmpleo(@Path("id") int id, @Body Job empleo);

    @POST("/alojamientos")
    Call<Void> crearAlojamiento(@Body Lodge lodge);

    @POST("/donaciones")
    Call<Void> crearDonacion(@Body Donation donation);

    @POST("/empleos")
    Call<Void> crearOferta(@Body Job empleo);

    @POST("/cursos")
    Call<Void> crearEducacion(@Body Education educacion);

    @DELETE("/servicios/{id}/{tipo}")
    Call<Void> eliminarServicio(@Path("id") int id, @Path("tipo") String tipo);

    @GET("/refugiados/{mail}/inscripciones/{id}/{tipo}")
    Call<Boolean> isUserSubscribed(@Path("mail") String mail, @Path("id") int id, @Path("tipo")
            String tipo);

    @POST("/usuarios/{mail}/inscripciones/{id}/{tipo}")
    Call<Void> subscribeService(@Path("mail") String mail, @Path("id") int id, @Path("tipo")
            String tipo);

    @DELETE("/refugiados/{mail}/inscripciones/{id}/{tipo}")
    Call<Void> unsubscribeService(@Path("mail") String mail, @Path("id") int id, @Path("tipo")
            String tipo);

    @GET("/test2")
    Call<Refugee> prueba();

    @GET("/usuarios/{mail}/chats/{idChat}/messages")
    Call<ArrayList<Message>> getMessagesChat(@Path("mail") String mail, @Path("idChat") int idChat);

    @POST("/usuarios/{mail}/chats/{idChat}/messages")
    Call<Void> sendMessage(@Path("mail") String mail, @Path("idChat") int idChat, @Body Message
            message);


    //LLAMADAS RELACIONADAS CON REPORTES

    @POST("/reportes")
    Call<Void> createReport(@Body Report report);

    @GET("/reportes")
    Call<ArrayList<Report>> getReports();

    @GET("/reportes/{id}")
    Call<Report> getReport(@Path("id") int id);

}

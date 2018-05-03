package com.example.usuario.rekindlefrontend.data.remote;

public class APIUtils {
    private APIUtils() {}

    public static final String BASE_URL = "http://10.4.41.147:8080";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}

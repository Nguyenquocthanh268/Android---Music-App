package com.example.musicapp.Service_API;

public class APIService {
    private static String base_url = "https://music-appp.000webhostapp.com/Server/";
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}

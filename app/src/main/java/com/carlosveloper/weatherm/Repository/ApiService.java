package com.carlosveloper.weatherm.Repository;


import com.carlosveloper.weatherm.Model.Response.ResponseClimaDias;
import com.carlosveloper.weatherm.Model.Response.ResponseForecast;
import com.carlosveloper.weatherm.Model.Response.ResponseWeather;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("weather")
    Call<ResponseWeather> getClima(@Query("id") String idCiudad,
                                  @Query("appid") String apiKey);



    @GET("forecast/daily")
    Call<ResponseForecast> getClimaForescast(@Query("id") String idCiudad,
                                    @Query("appid") String apiKey);


    @GET("onecall/timemachine")
    Call<ResponseClimaDias> getClimaDay(@Query("lat") String latitud,
                                        @Query("lon") String longitud,
                                        @Query("dt") String diaTiempo,
                                        @Query("appid") String apiKey);

}

package com.carlosveloper.weatherm.Repository;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

   /* @GET("usuarios")
    Call<ResponseLogin> doLogin(@Query("correo") String correo,
                                @Query("contrasenia") String idTienda);


    @POST("usuarios")
    Call<DatosLogin> crearUsuario(@Body JsonObject object);

    @POST("facturas")
    Call<DatosFactura> crearFactura(@Body JsonObject object);




    //Todo planes de usuario
    @GET("usuarios/{id}?embed=planes?embed=plan")
    Call<ResponsePlanesUsuario> misPlanes(@Path(value = "id", encoded = true) String idUsuario);

    @GET("planes")
    Call<ResponsePlanes> getPlanes();

    @POST("planesusuario")
    Call<DatosPlanUsuario> agregarPlan(@Body JsonObject object);



    ///Todo facturas de usuario

    //Todo planes de usuario
    @GET("usuarios/{id}?embed=facturas")
    Call<ResponseFactura> misFacturas(@Path(value = "id", encoded = true) String idUsuario);

*/
}

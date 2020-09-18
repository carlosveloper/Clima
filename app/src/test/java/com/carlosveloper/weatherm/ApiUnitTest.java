package com.carlosveloper.weatherm;

import com.carlosveloper.weatherm.Common.Constantes;
import com.carlosveloper.weatherm.Model.Response.ResponseClimaDias;
import com.carlosveloper.weatherm.Model.Response.ResponseForecast;
import com.carlosveloper.weatherm.Model.Response.ResponseWeather;
import com.carlosveloper.weatherm.Repository.ApiService;
import com.carlosveloper.weatherm.Repository.RetrofitClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ApiUnitTest {
    /*
    {
        "id": 3658192,
        "name": "Eloy Alfaro",
        "state": "",
        "country": "EC",
        "coord": {
            "lon": -79.833328,
            "lat": -2.2
        }
    },
    */

    private RetrofitClient ClimaClient;
    private ApiService CLimaService;

    @Before
    public  void setup() {
        ClimaClient = RetrofitClient.getInstance();
        CLimaService = ClimaClient.getService();
    }

    @Test
    public void testGetConsultaClima() throws IOException {
        //Duran Eloy Alfaro id
        Call<ResponseWeather> call = CLimaService.getClima("3658192", Constantes.ApiKey);
        Response<ResponseWeather> climaResponse = call.execute();
        assertEquals(climaResponse.code(), 200);
        assertEquals(true, climaResponse.isSuccessful());

    }


    @Test
    public void testGetForescast() throws  IOException{
        Call<ResponseForecast> call = CLimaService.getClimaForescast("3658192", Constantes.ApiKey);
        Response<ResponseForecast> forescastResponse = call.execute();
        assertEquals(forescastResponse.code(), 200);
        assertEquals(true, forescastResponse.isSuccessful());
    }


    @Test
    public void testGetDiaAnteriores() throws  IOException{
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);  //-1 , -2 ,-3 dia anteriores
        Date diaExacto = c.getTime();
        int dia = (int) (diaExacto.getTime() / 1000);
        Call<ResponseClimaDias> call = CLimaService.getClimaDay("-2.2","-79.833328",""+dia, Constantes.ApiKey);
        Response<ResponseClimaDias> diasResponse = call.execute();
        assertEquals(diasResponse.code(), 200);
        assertEquals(true, diasResponse.isSuccessful());
    }




}
package com.carlosveloper.weatherm.ViewModel;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carlosveloper.weatherm.Common.Constantes;
import com.carlosveloper.weatherm.Common.MyApp;
import com.carlosveloper.weatherm.Model.CityJson;
import com.carlosveloper.weatherm.Model.Response.ResponseClimaDias;
import com.carlosveloper.weatherm.Model.Response.ResponseForecast;
import com.carlosveloper.weatherm.Model.Response.ResponseWeather;
import com.carlosveloper.weatherm.Repository.ApiService;
import com.carlosveloper.weatherm.Repository.RetrofitClient;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelClima extends ViewModel {


    RetrofitClient ClimaClient;
    ApiService CLimaService;

    private MutableLiveData<Boolean> isViewLoading;
    private MutableLiveData<ResponseWeather> consultaClima;
    private MutableLiveData<ResponseForecast> consultaClimaForescast;
    private MutableLiveData<ResponseClimaDias> consultaClimaDias;

    private MutableLiveData<Boolean> intentHome;

    public ViewModelClima() {
        this.isViewLoading = new MutableLiveData<>();
        this.intentHome = new MutableLiveData<>();
        this.consultaClima = new MutableLiveData<>();
        this.consultaClimaForescast = new MutableLiveData<>();
        this.consultaClimaDias = new MutableLiveData<>();

        RetrofitInit();

    }

    public LiveData<Boolean> getIsViewLoading(){
        return  isViewLoading;
    }
    public LiveData<Boolean> getintentHome(){
        return  intentHome;
    }
    public LiveData<ResponseWeather> getConsultaClima(){
        return  consultaClima;
    }
    public LiveData<ResponseClimaDias> getConsultaClimaDias(){
        return  consultaClimaDias;
    }





    public LiveData<ResponseForecast> getConsultaClimaForescast(){
        return  consultaClimaForescast;
    }

    public  void  onClickLogin(String email,String pass){
       /* isViewLoading.setValue(true);
        if(validaEmail(email) &&     validaPassword(pass)  ){
            isViewLoading.setValue(false);
        }else {
            peticionLogin(email,pass);
        }*/
     //  resultado.setValue(Validation.validarEmail(data));
    }



    private void RetrofitInit(){
        ClimaClient = RetrofitClient.getInstance();
        CLimaService = ClimaClient.getMiniTwitterService();
    }


    public void peticionClima(String idCiudad){
        isViewLoading.setValue(true);
        Call<ResponseWeather> call = CLimaService.getClima(idCiudad, Constantes.ApiKey);
        call.enqueue(new Callback<ResponseWeather>() {
            @Override
            public void onResponse(Call<ResponseWeather> call, Response<ResponseWeather> response) {
                if(response.isSuccessful()) {
                    quitarLoading();
                    consultaClima.setValue(response.body());
                } else {
                    isViewLoading.setValue(false);
                    Toast.makeText(MyApp.getContext(), "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseWeather> call, Throwable t) {
                isViewLoading.setValue(false);
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void quitarLoading(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Write whatever to want to do after delay specified (1 sec)
                Log.d("Handler", "Running Handler");
                isViewLoading.setValue(false);

            }
        }, 300);
    }



    public void peticionClimaForescast(String idCiudad){
        Call<ResponseForecast> call = CLimaService.getClimaForescast(idCiudad, Constantes.ApiKey);
        call.enqueue(new Callback<ResponseForecast>() {
            @Override
            public void onResponse(Call<ResponseForecast> call, Response<ResponseForecast> response) {
                if(response.isSuccessful()) {
                    consultaClimaForescast.setValue(response.body());
                } else {
                    Toast.makeText(MyApp.getContext(), "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseForecast> call, Throwable t) {
                Log.e("error",t.toString());
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void peticionClimaDias(String latitud,String longitud,String dia,int numeroDia){
        Call<ResponseClimaDias> call = CLimaService.getClimaDay(latitud,longitud,dia, Constantes.ApiKey);
        call.enqueue(new Callback<ResponseClimaDias>() {
            @Override
            public void onResponse(Call<ResponseClimaDias> call, Response<ResponseClimaDias> response) {
                if(response.isSuccessful()) {
                    response.body().setNumeroDia(numeroDia);
                    consultaClimaDias.setValue(response.body());
                } else {
                    Toast.makeText(MyApp.getContext(), "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseClimaDias> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }



}

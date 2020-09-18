package com.carlosveloper.weatherm.ViewModel;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carlosveloper.weatherm.Common.Constantes;
import com.carlosveloper.weatherm.Common.Global;
import com.carlosveloper.weatherm.Common.MyApp;
import com.carlosveloper.weatherm.Model.CityJson;
import com.carlosveloper.weatherm.Model.Response.ResponseClimaDias;
import com.carlosveloper.weatherm.Model.Response.ResponseForecast;
import com.carlosveloper.weatherm.Model.Response.ResponseWeather;
import com.carlosveloper.weatherm.Repository.ApiService;
import com.carlosveloper.weatherm.Repository.RetrofitClient;
import com.carlosveloper.weatherm.Utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelMain extends ViewModel {

;

    private MutableLiveData<Boolean> isViewLoading;

    public ViewModelMain() {
        this.isViewLoading = new MutableLiveData<>();

    }

    public LiveData<Boolean> getIsViewLoading(){
        return  isViewLoading;
    }





    public void llenarCiudad(){
        isViewLoading.setValue(true);
        Global.llenarImagenesCLima();
        Gson gson = new Gson();
        List<CityJson> listCiudades = gson.fromJson(Utils.loadJSONFromAsset(MyApp.getContext()), new TypeToken<List<CityJson>>(){}.getType());
        Global.misCiudades=listCiudades;
        quitarLoading();

    }

    private void quitarLoading(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isViewLoading.setValue(false);
            }
        }, 3000);
    }





}

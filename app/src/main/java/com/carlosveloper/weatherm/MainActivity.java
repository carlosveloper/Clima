package com.carlosveloper.weatherm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.carlosveloper.weatherm.Common.Global;
import com.carlosveloper.weatherm.Model.CityJson;
import com.carlosveloper.weatherm.View.Fragments.Ciudades;
import com.carlosveloper.weatherm.View.Fragments.Clima;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        List<CityJson> listCiudades = gson.fromJson(loadJSONFromAsset(this), new TypeToken<List<CityJson>>(){}.getType());
        Global.misCiudades=listCiudades;
        Global.llenarImagenesCLima();
        Log.e("nombre",listCiudades.get(0).getName());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Contenedor_Fragments, new Ciudades()).commit();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("city.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }


}
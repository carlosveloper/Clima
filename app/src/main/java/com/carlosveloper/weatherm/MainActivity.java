package com.carlosveloper.weatherm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.carlosveloper.weatherm.View.Fragments.Ciudades;
import com.carlosveloper.weatherm.ViewModel.ViewModelMain;


public class MainActivity extends AppCompatActivity {


    private ViewModelMain viewModel;
    private LinearLayout ContainerCity;
    private View layoutLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UI();
        Observer();
        viewModel.llenarCiudad();

    }

    private void UI() {
        layoutLoading = findViewById(R.id.layoutLoading);
        ContainerCity = findViewById(R.id.ContainerCity);
        viewModel = ViewModelProviders.of(this).get(ViewModelMain.class);

    }

    private void Observer() {
        final Observer<Boolean> observerLoading = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {

                if (result) {
                    layoutLoading.setVisibility(View.VISIBLE);
                    ContainerCity.setVisibility(View.GONE);
                } else {
                    layoutLoading.setVisibility(View.GONE);
                    ContainerCity.setVisibility(View.VISIBLE);
                    IniciarFragment();
                }

            }
        };
        viewModel.getIsViewLoading().observe(this, observerLoading);

    }

    private void IniciarFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Contenedor_Fragments, new Ciudades()).commit();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


}
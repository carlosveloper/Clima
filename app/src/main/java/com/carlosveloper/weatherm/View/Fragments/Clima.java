package com.carlosveloper.weatherm.View.Fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.carlosveloper.weatherm.Adapter.VistaDiasClimaAnteriores;
import com.carlosveloper.weatherm.Adapter.VistaDiasClimaPosteriores;
import com.carlosveloper.weatherm.Common.Constantes;
import com.carlosveloper.weatherm.Common.Global;
import com.carlosveloper.weatherm.Model.CityJson;
import com.carlosveloper.weatherm.Model.Response.ResponseClimaDias;
import com.carlosveloper.weatherm.Model.Response.ResponseForecast;
import com.carlosveloper.weatherm.Model.Response.ResponseWeather;
import com.carlosveloper.weatherm.R;
import com.carlosveloper.weatherm.Utils.Utils;
import com.carlosveloper.weatherm.ViewModel.ViewModelClima;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Clima extends Fragment {

    private ViewModelClima viewModel;
    public CityJson ciudad;
    View vista;

    List<ResponseClimaDias> listadiasAnteriores = new ArrayList<>();
    List<com.carlosveloper.weatherm.Model.Response.List> listdiasPosterior = new ArrayList<>();


    ////UI
    private TextView tv_addres, tv_date, tv_temp,
            tv_pressure, tv_humidity, tv_wind,
            tv_description;
    private ImageView img_small_[] = new ImageView[5];
    MaterialSpinner spinner;
    private ImageView img_weather;

    private LinearLayout lyt_main;
    private LinearLayout ContainerClima;
    private View layoutLoading;


    RecyclerView recyclerView;
    VistaDiasClimaAnteriores adapter;
    VistaDiasClimaPosteriores adapter2;

    ///


    public Clima() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.peticionClima("" + ciudad.getId());
        viewModel.peticionClimaForescast("" + ciudad.getId());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_clima, container, false);
        UI();
        iniciar_recyclerAnterior();
        iniciar_recyclerPosterior();

        if (ciudad == null) {
            ciudad = Global.ciudadSeleccionada;
        }

        Observer();
        ObtenerCLimaDias(-5);
        ObtenerCLimaDias(-4);
        ObtenerCLimaDias(-3);
        ObtenerCLimaDias(-2);
        ObtenerCLimaDias(-1);

        return vista;
    }

    private void UI() {
        recyclerView = vista.findViewById(R.id.Recycler_misdiaClima);
        tv_addres = vista.findViewById(R.id.tv_addres);
        tv_date = vista.findViewById(R.id.tv_date);
        tv_temp = vista.findViewById(R.id.tv_temp);
        tv_pressure = vista.findViewById(R.id.tv_pressure);
        tv_humidity = vista.findViewById(R.id.tv_humidity);
        tv_wind = vista.findViewById(R.id.tv_wind);
        img_weather = vista.findViewById(R.id.img_weather);
        lyt_main = vista.findViewById(R.id.lyt_main);
        tv_description = vista.findViewById(R.id.tv_description);
        ContainerClima = vista.findViewById(R.id.ContainerClima);
        layoutLoading = vista.findViewById(R.id.layoutLoading);
        spinner = vista.findViewById(R.id.spinner);
        spinner.setItems("5 DÍAS ANTERIORES", "1 SEMANA DE PRONOSTICO");
        spinner.setSelectedIndex(1);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Log.e("hey", "" + position);
                if (position == 0) {
                    iniciar_recyclerAnterior();
                } else {
                    iniciar_recyclerPosterior();
                }
            }
        });


        viewModel = ViewModelProviders.of(this).get(ViewModelClima.class);

    }

    private void ObtenerCLimaDias(int sumaRestaDias) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, sumaRestaDias);
        Date diaExacto = c.getTime();
        int dia = (int) (diaExacto.getTime() / 1000);
        viewModel.peticionClimaDias("" + ciudad.getCoord().getLat(), "" + ciudad.getCoord().getLon(), "" + dia, sumaRestaDias);
    }

    private void Observer() {
        final Observer<ResponseWeather> observerClima = new Observer<ResponseWeather>() {
            @Override
            public void onChanged(ResponseWeather result) {
                llenarDatos(result);
            }
        };

        final Observer<ResponseForecast> observerClimaForescast = new Observer<ResponseForecast>() {
            @Override
            public void onChanged(ResponseForecast result) {
                adapter2.updateList(result.getList());
            }
        };

        final Observer<ResponseClimaDias> observerDias = new Observer<ResponseClimaDias>() {
            @Override
            public void onChanged(ResponseClimaDias result) {

                listadiasAnteriores.add(result);
                Collections.sort(listadiasAnteriores, new ResponseClimaDias());
                adapter.notifyDataSetChanged();
            }
        };
        final Observer<Boolean> observerLoading = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {

                if (result) {
                    layoutLoading.setVisibility(View.VISIBLE);
                    ContainerClima.setVisibility(View.GONE);
                } else {
                    layoutLoading.setVisibility(View.GONE);
                    ContainerClima.setVisibility(View.VISIBLE);

                }

            }
        };
        viewModel.getIsViewLoading().observe(this, observerLoading);

        viewModel.getConsultaClima().observe(this, observerClima);
        viewModel.getConsultaClimaForescast().observe(this, observerClimaForescast);
        viewModel.getConsultaClimaDias().observe(this, observerDias);

    }


    private void llenarDatos(ResponseWeather result) {


        tv_addres.setText(result.getName() + ", " + result.getSys().getCountry());
        tv_date.setText(Utils.getLastUpdate(Long.valueOf(result.getDt())));
        tv_temp.setText(Utils.getTemp(result.getMain().getTemp()));
        tv_pressure.setText(Utils.sSpiltter(Double.parseDouble("" + result.getMain().getPressure())) + " hpa");
        tv_humidity.setText(Utils.sSpiltter(Double.parseDouble("" + result.getMain().getHumidity())) + " %");
        tv_wind.setText(Utils.sSpiltter(Double.parseDouble("" + result.getWind().getSpeed())) + " m/s");
        tv_description.setText(result.getWeather().get(0).getDescription().toUpperCase());

        Log.e("iconP", Constantes.UrlImage(result.getWeather().get(0).getIcon()));

        Glide.with(this).load(Utils.getFondoClima(result.getWeather().get(0).getIcon()).trim())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            lyt_main.setBackground(resource);
                        }
                    }
                });

        Glide
                .with(this)
                .load(Constantes.UrlImage(result.getWeather().get(0).getIcon()))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img_weather);


    }


    private void iniciar_recyclerAnterior() {

        adapter = new VistaDiasClimaAnteriores(listadiasAnteriores, getFragmentManager(), getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void iniciar_recyclerPosterior() {
        adapter2 = new VistaDiasClimaPosteriores(listdiasPosterior, getFragmentManager(), getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter2);
    }


}
package com.carlosveloper.weatherm.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.carlosveloper.weatherm.Adapter.VistaCiudades;
import com.carlosveloper.weatherm.Common.Global;
import com.carlosveloper.weatherm.Model.CityJson;
import com.carlosveloper.weatherm.R;

import java.util.ArrayList;
import java.util.List;

public class Ciudades extends Fragment {



    RecyclerView recyclerView;
    List<CityJson> lst_ciudades=new ArrayList<>();
    VistaCiudades adapter;

    View vista;
    EditText buscar;
    public Ciudades() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e("resume",""+Global.misCiudades.size());
       // lst_ciudades.addAll(Global.misCiudades);
        adapter.updateList(Global.misCiudades);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista =inflater.inflate(R.layout.fragment_ciudades, container, false);
        UI();
        iniciar_recycler();
        return vista;
    }

    private void UI(){
        recyclerView= vista.findViewById(R.id.Recycler_misCiudades);
        buscar=vista.findViewById(R.id.escribir_busqueda);
        buscar.clearFocus();
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filtro(editable.toString());
            }
        });


    }

    private void  iniciar_recycler(){
        adapter=new VistaCiudades(lst_ciudades,getFragmentManager());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void filtro(String S){
        if(adapter!=null)
            adapter.getFilter().filter(S);
    }

}
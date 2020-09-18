package com.carlosveloper.weatherm.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.carlosveloper.weatherm.Common.Global;
import com.carlosveloper.weatherm.Model.CityJson;

import com.carlosveloper.weatherm.Model.Response.ResponseClimaDias;
import com.carlosveloper.weatherm.R;
import com.carlosveloper.weatherm.Utils.Utils;
import com.carlosveloper.weatherm.View.Fragments.Clima;

import java.util.ArrayList;
import java.util.List;

public class VistaCiudades extends RecyclerView.Adapter<VistaCiudades.Holder> implements Filterable {

     List<CityJson> lst_normal;
     List<CityJson> list_full;
    FragmentManager fragmentManager;
    Context context;
    String id_del_fragment;

    ;

    public VistaCiudades(List<CityJson> lst_normal, FragmentManager fragmentManager) {
        this.lst_normal = lst_normal;
        list_full = new ArrayList<>(lst_normal);
        this.fragmentManager = fragmentManager;
    }


    @NonNull
    @Override
    public VistaCiudades.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ciudades,
                parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VistaCiudades.Holder holder, final int position) {

        holder.NombreCiudad.setText(lst_normal.get(position).getName());
        holder.Pais.setText(lst_normal.get(position).getCountry());
        holder.bg_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clima climaCiudad = new Clima();
                climaCiudad.ciudad = lst_normal.get(position);
                Global.misCiudades=list_full;
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Contenedor_Fragments, climaCiudad).addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return lst_normal.size();
    }

    @Override
    public Filter getFilter() {
        return city_filter;
    }


    public class Holder extends RecyclerView.ViewHolder {

        TextView NombreCiudad, Pais;
        LinearLayout bg_item;

        public Holder(@NonNull View itemView) {
            super(itemView);
            NombreCiudad = itemView.findViewById(R.id.TVNombreCiudad);

            Pais = itemView.findViewById(R.id.TVPais);
            bg_item = itemView.findViewById(R.id.bg_item);


        }
    }

    private Filter city_filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.e("lista full",""+list_full.size());
            Log.e("lista normal",""+lst_normal.size());

            if(list_full.size()<=0){
                list_full.addAll(Global.misCiudades);
            }

            //("adapter","filtro llegar" +constraint);
            List<CityJson> filtro = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                //("adapter","filtro sin cambios");

                filtro.addAll(list_full);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                //("adapter","probar-->" + filterPattern);
                //("adapter","tamaño lista -->" + list_full.size());

                for (CityJson item : list_full) {
                    //("adapter","recorro" + item.getNombre());


                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filtro.add(item);
                    }
                }
            }


            FilterResults results = new FilterResults();
            results.values = filtro;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            lst_normal.clear();
            lst_normal.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };




    public void updateList(List<CityJson> newList) {
        lst_normal.clear();
        list_full.clear();
        this.lst_normal = newList;
        list_full = new ArrayList<>(lst_normal);
        Log.e("pase",""+newList.size());
        this.notifyDataSetChanged();
    }



}

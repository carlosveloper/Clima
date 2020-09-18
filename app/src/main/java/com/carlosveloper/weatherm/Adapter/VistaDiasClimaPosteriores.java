package com.carlosveloper.weatherm.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.carlosveloper.weatherm.Common.Constantes;
import com.carlosveloper.weatherm.Model.Response.ResponseClimaDias;
import com.carlosveloper.weatherm.R;
import com.carlosveloper.weatherm.Utils.Utils;

import java.util.List;

public class VistaDiasClimaPosteriores extends RecyclerView.Adapter<VistaDiasClimaPosteriores.Holder> {

    List<com.carlosveloper.weatherm.Model.Response.List> lst_normal;
    List<com.carlosveloper.weatherm.Model.Response.List> list_full;
    FragmentManager fragmentManager;
    Context context;
    String id_del_fragment;

    public VistaDiasClimaPosteriores(List<com.carlosveloper.weatherm.Model.Response.List> lst_normal, FragmentManager fragmentManager, Context context) {
        this.lst_normal = lst_normal;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }


    @NonNull
    @Override
    public VistaDiasClimaPosteriores.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dias_clima,
                parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VistaDiasClimaPosteriores.Holder holder, final int position) {


        holder.dia.setText(Utils.getDay(Long.valueOf(lst_normal.get(position).getDt()))+"\n"+Utils.DiaFecha(Long.valueOf(lst_normal.get(position).getDt())));


        Glide.with(holder.fondoDia.getContext()).load(Utils.getFondoClima(lst_normal.get(position).getWeather().get(0).getIcon()).trim())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            holder.fondoDia.setBackground(resource);
                        }
                    }
                });


        Glide
                .with(holder.fotoDia.getContext())
                .load(Constantes.UrlImage(lst_normal.get(position).getWeather().get(0).getIcon()))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.fotoDia);

        holder.temp.setText(Utils.getTemp(lst_normal.get(position).getTemp().getDay()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","click");
            }
        });

    }

    @Override
    public int getItemCount() {
        return lst_normal.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        TextView dia, temp;
        ImageView fotoDia;
        LinearLayout fondoDia;

        public Holder(@NonNull View itemView) {
            super(itemView);
            dia = itemView.findViewById(R.id.tv_day);
            fondoDia = itemView.findViewById(R.id.fondoDia);

            fotoDia = itemView.findViewById(R.id.img_small);
            temp = itemView.findViewById(R.id.tv_temp);
        }
    }


    public void updateList(List<com.carlosveloper.weatherm.Model.Response.List> newlist) {
        lst_normal.clear();
        lst_normal.addAll(newlist);
        this.notifyDataSetChanged();
    }

}

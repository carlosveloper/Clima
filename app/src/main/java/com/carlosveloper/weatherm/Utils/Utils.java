package com.carlosveloper.weatherm.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;

import com.carlosveloper.weatherm.Common.Global;
import com.carlosveloper.weatherm.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static String getLastUpdate(Long l) {
        Date curDate = new Date(l * 1000);
        //Wed, 4 Jul 2001 12:08:56 -0700
        SimpleDateFormat format = new SimpleDateFormat("EEE d MMM yyyy HH:mm");
        String dateToStr = format.format(curDate);
        return   dateToStr.toUpperCase();
    }


    public static String DiaFecha(Long l) {
        Date curDate = new Date(l * 1000);
        SimpleDateFormat format = new SimpleDateFormat("d MMM yyyy");
        String dateToStr = format.format(curDate);
        return dateToStr.toUpperCase();
    }

    private static boolean isLollipopOrHigher() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }

    public static void systemBarLollipop(Activity act, @ColorInt int colorInt) {
        if (isLollipopOrHigher()) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(colorDarker(colorInt));
        }
    }
    private static int colorDarker(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f; // value component
        return Color.HSVToColor(hsv);
    }




    public static String getTemp(Double degrees) {
       /* if (getIntPref(Constant.I_KEY_UNIT, 0, context) == 0) { // for celcius
            return sSpiltter(d) + " \u00b0C";

        } else { // for farhenheit
            Double F = (d * (9 / 5)) + 32;
            return sSpiltter(F) + " \u00b0F";
        }*/

       /* Double F = (d * (9 / 5)) + 32;
        return sSpiltter(F) + " \u00b0F";*/


       double celcius= degrees- 273.15;

       float fah=celsiusAFahrenheit((float)celcius);
        double result=0;
        result=fah-32;
        result*=5;
        result/=9;
        return sSpiltter(result)+ " \u00b0C";


    }

    public static float celsiusAFahrenheit(float celsius) {
        return (celsius * 1.8f) + 32;
    }



    public static String sSpiltter(Double d) {
        //Double d=Double.valueOf(s);
        String s = String.valueOf(d);
        if (s.contains(",")) {
            s = s.split("\\,")[0];
        } else if (s.contains(".")) {
            s = s.split("\\.")[0];
        }
        return s;
    }



    public static  String getDay(Long l) {
        Date time = new Date(l * 1000);
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int daynum = cal.get(Calendar.DAY_OF_WEEK);

        switch (daynum) {
            case 1:
                return "DOMINGO";
            case 2:
                return "LUNES";
            case 3:
                return "MARTES";
            case 4:
                return "MIERCOLES";
            case 5:
                return "JUEVES";
            case 6:
                return "VIERNES";
            case 7:
                return "SABADO";
        }
        return null;
    }



    public static int setLytColor(String icon, LinearLayout lyt,Context context) {
        int colorInt;

        String color[] = context.getResources().getStringArray(R.array.color_weather);
        if (icon.equals("01d") || icon.equals("01n")) { // clear sky
            colorInt = Color.parseColor(color[0]);

        } else if (icon.equals("02d") || icon.equals("02n")) { // few clouds
            colorInt = Color.parseColor(color[1]);

        } else if (icon.equals("03d") || icon.equals("03n")) { // scatteredclouds
            colorInt = Color.parseColor(color[2]);

        } else if (icon.equals("04d") || icon.equals("04n")) { // broken clouds
            colorInt = Color.parseColor(color[3]);

        } else if (icon.equals("09d") || icon.equals("09n")) { // shower rain
            colorInt = Color.parseColor(color[4]);

        } else if (icon.equals("10d") || icon.equals("10n")) { // rain
            colorInt = Color.parseColor(color[5]);

        } else if (icon.equals("11d") || icon.equals("11n")) { // thunderstorm
            colorInt = Color.parseColor(color[6]);

        } else if (icon.equals("13d") || icon.equals("13n")) { // snow
            colorInt = Color.parseColor(color[7]);

        } else if (icon.equals("50d") || icon.equals("50n")) { // mist
            colorInt = Color.parseColor(color[8]);

        } else {
            colorInt = Color.parseColor(color[9]);
        }

        lyt.setBackgroundColor(colorInt);
        return colorInt;
    }



    public static String getFondoClima(String icon) {
       String Url="";
        if (icon.equals("01d") || icon.equals("01n")) { // clear sky
            Url= Global.ImagenesClimaEventual.get(0);
        } else if (icon.equals("02d") || icon.equals("02n")) { //few clouds
            Url= Global.ImagenesClimaEventual.get(1);
        } else if (icon.equals("03d") || icon.equals("03n")) { // scattered clouds
            Url= Global.ImagenesClimaEventual.get(2);
        } else if (icon.equals("04d") || icon.equals("04n")) { //broken clouds
            Url= Global.ImagenesClimaEventual.get(3);

        } else if (icon.equals("09d") || icon.equals("09n")) {  //shower rain
            Url= Global.ImagenesClimaEventual.get(4);

        } else if (icon.equals("10d") || icon.equals("10n")) { //rain
            Url= Global.ImagenesClimaEventual.get(5);

        } else if (icon.equals("11d") || icon.equals("11n")) { //thunderstorm
            Url= Global.ImagenesClimaEventual.get(6);

        } else if (icon.equals("13d") || icon.equals("13n")) { //snow
            Url= Global.ImagenesClimaEventual.get(7);

        } else if (icon.equals("50d") || icon.equals("50n")) { //mist
            Url= Global.ImagenesClimaEventual.get(8);

        } else {
            Url= Global.ImagenesClimaEventual.get(9);
        }

        return Url;
    }



}

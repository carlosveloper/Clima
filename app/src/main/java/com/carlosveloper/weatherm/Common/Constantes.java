package com.carlosveloper.weatherm.Common;

public class Constantes {
    public static String UrlBase="http://api.openweathermap.org/data/2.5/";
    public static String ApiKey="2b350a87de1accceaaa66f9966efb1fe";



    public  static  String UrlImage(String codeImage){
      String url="http://openweathermap.org/img/wn/"+codeImage+"@2x.png";
      return url.trim();
    }
}

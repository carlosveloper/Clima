# Clima
App de Clima con patron MVVM  permite visualizar el clima de ciertas ciudades en este caso 10 ciudades de Ecuador
usando la api de https://openweathermap.org/ para configurar su propia Key debera dirigirse a:


Common-->Constantes --->variable Apikey 
reemplazarla con su key 

para el control de los fondos de cada clima si se desea editar debera ir a :


Common-->Global-----> funcion llenarImagenesCLima   
y podra modificar los fondos con lo que considere acorde al clima 


En la carpeta drawable se encuentra varias imagenes para el clima en este caso se usa solo algunas
ya que por el momento se toma las imagenes que la misma api nos proporciona

Tambien se encuentra un par de estilos xml que se usan el buscador y en el recycler de los climas 

Si desea tener todas las ciudades (20000+) en la carpeta assets existe el archivo citylist.json que podra configurar

Utils------>Utils-----> funcion loadJSONFromAsset

reemplazar city.json ------> citylist.json 


 # [Requisitos]


Android Studio  version '4.0.1'

Api Key :  https://openweathermap.org/






# [Septiembre 2020]
-Pantalla loading city 

-Pantalla ciudades(Se visualiza todas las ciudades)

-Pantalla loading clima 

-Pantalla clina(Se visualiza el clima de 5 dias anteriores y 7 dias Posteriores)

-Pruebas unitarias a la Api https://openweathermap.org/



![119910427_318634706031887_4225344591219815016_n](https://user-images.githubusercontent.com/41177884/93630867-5131cd00-f9b0-11ea-8bef-58a47ff3723e.jpg)
![119609564_788838145202224_9100717814619652333_n](https://user-images.githubusercontent.com/41177884/93630877-555dea80-f9b0-11ea-9778-4f07f6caba43.jpg)
![WhatsApp Image 2020-09-18 at 12 52 10](https://user-images.githubusercontent.com/41177884/93630882-5858db00-f9b0-11ea-8c1b-c4416b911bfa.jpeg)
![119635224_348781089607182_6801090294736108276_n](https://user-images.githubusercontent.com/41177884/93630890-5b53cb80-f9b0-11ea-9eab-2e1b5f3cf34d.jpg)
![119936864_336654680994641_7552127876517529316_n](https://user-images.githubusercontent.com/41177884/93631015-876f4c80-f9b0-11ea-8fec-802a798618e2.jpg)






# [Dependencies]

Retrofit 2 version: '2.9.0'

OkHttp 3 version: '4.8.1'

Glide version: '4.11.0'

Lifecycle version: '1.1.1'

Material-spinner version: '1.3.1'

Mockito version: '2.27.0'

Lottie  version: '3.4.1'







# [Contribuir y reportar problemas]
Si desea contribuir en este repositorio, envíeme una solicitud de extracción o si desea informar algunos problemas,
envíela a https://github.com/carlosveloper/Clima/issues

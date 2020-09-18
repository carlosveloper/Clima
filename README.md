# Clima
App de Clima con patron MVVM 
Una app para poder visualizar el clima de ciertas ciudades en este caso 10 ciudades de Ecuador
usando la api de https://openweathermap.org/ para configurar su propia Key debera dirigirse a:


Common-->Constantes --->variable Apikey 
reemplazarla con su key 

para el control de los fondos de cada clima si se desea editar debera ir a :


Common-->Global-----> funcion llenarImagenesCLima   
y podra modificar los fondos con lo que considere acorde al clima 


en la carpeta drawable se encuentra varias imagenes para el clima en este caso se usa solo algunas
ya que por el momento se toma las imagenes que la misma api nos proporciona

Tambien se encuentra un par de estilos xml que se usan el buscador y en el recycler de los climas 







[Septiembre 2020]

Pantalla loading city 
Pantalla ciudades(Se visualiza todas las ciudades)
Pantalla loading clima 
Pantalla clina(Se visualiza el clima de 5 dias anteriores y 7 dias Posteriores)

Pruebas unitarias a la Api https://openweathermap.org/









[Dependencies]
Retrofit 2 version: '2.9.0'
OkHttp 3 version: '4.8.1'
Glide version: '4.11.0'
Lifecycle version: '1.1.1'
Material-spinner version: '1.3.1'
Mockito version: '2.27.0'
Lottie  version: '3.4.1'







[Contribuir y reportar problemas]

Si desea contribuir en este repositorio, envíeme una solicitud de extracción o si desea informar algunos problemas,
envíela a https://github.com/carlosveloper/Clima/issues

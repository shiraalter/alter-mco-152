package alter.weather;

import retrofit2.Call;
import retrofit2.http.*;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("https://api.openweathermap.org/data/2.5/weather?APPID=f0294b71a48b8c8a985f8f63afeaba95&units=imperial")
    Call<CurrentWeather> getZip(@Query("zip") String zip);
}

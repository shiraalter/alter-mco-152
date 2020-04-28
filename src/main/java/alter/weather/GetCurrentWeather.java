package alter.weather;

import com.google.gson.Gson;
import org.omg.CORBA.Current;

import java.io.BufferedReader;
import java.io.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetCurrentWeather {

        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?zip=07666,US&appid=f0294b71a48b8c8a985f8f63afeaba95");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Gson gson = new Gson();
        CurrentWeather currentWeather = gson.fromJson(reader, CurrentWeather.class);


    public GetCurrentWeather() throws IOException {

    }

}


package edu.indiana.soic.homeshare.homeshare.api;

import edu.indiana.soic.homeshare.homeshare.data.model.WeatherData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    String API_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather?units=imperial&APPID=17e3cc1b98b51a8d6a446ffc133c43f1")
    Call<WeatherData> getWeather(@Query("lat") String lat, @Query("lon") String lon);
}

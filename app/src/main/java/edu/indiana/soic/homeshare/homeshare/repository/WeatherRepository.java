package edu.indiana.soic.homeshare.homeshare.repository;

import android.arch.lifecycle.LiveData;
import android.location.Location;
import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.R;
import edu.indiana.soic.homeshare.homeshare.api.WeatherService;
import edu.indiana.soic.homeshare.homeshare.data.db.HomeshareDao;
import edu.indiana.soic.homeshare.homeshare.data.model.LocationLiveData;
import edu.indiana.soic.homeshare.homeshare.data.model.Main;
import edu.indiana.soic.homeshare.homeshare.data.model.Weather;
import edu.indiana.soic.homeshare.homeshare.data.model.WeatherData;
import edu.indiana.soic.homeshare.homeshare.data.model.WeatherInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private final Executor executor;
    private final HomeshareDao homeshareDao;
    private final WeatherService weatherService;
    private final LocationLiveData location;
    public static final String TAG = "WeatherRepository";

    @Inject
    public WeatherRepository(Executor executor, HomeshareDao homeshareDao, WeatherService weatherService, LocationLiveData location) {
        this.executor = executor;
        this.homeshareDao = homeshareDao;
        this.weatherService = weatherService;
        this.location = location;
    }

    public LocationLiveData getLocation() {
        return location;
    }

    public void refreshLocation() {
        location.refreshLocation();
    }

    public LiveData<WeatherInfo> getWeather(Location location) {
        String lat = String.valueOf(location.getLatitude());
        String lon = String.valueOf(location.getLatitude());
        fetchFromServer(lat, lon);
        return homeshareDao.getWeather();
    }

    public void fetchFromServer(String lat, String lon) {
        weatherService.getWeather(lat, lon).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                try {
                    Weather weather = response.body().getWeather().get(0);
                    Main temp = response.body().getMain();
                    String city = response.body().getName();
                    int icon = getWeatherIcon(weather.getId());
                    WeatherInfo weatherInfo = new WeatherInfo(weather.getMain(), weather.getDescription(), icon, temp.getTemp(),temp.getTempMin(), temp.getTempMax(), System.currentTimeMillis(), city);
                    executor.execute(() -> {
                        homeshareDao.addWeather(weatherInfo);
                    });
                }catch (Exception e) {
                    Log.d(TAG, "onResponse: Failed " + e);
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.d(TAG, "onFailure: failed to fetch from server");
            }
        });
    }

    private int getWeatherIcon(int actualId) {
        int id = actualId/100;
        int icon = R.string.sun;
        if(actualId == 800){
            icon = R.string.sun;
        }else{
            switch (id){
                case 2:
                    icon = R.string.thunder;
                    break;
                case 3:
                    icon = R.string.drizzle;
                    break;
                case 5:
                    icon = R.string.rain;
                    break;
                case 6:
                    icon = R.string.snow;
                    break;
                case 7:
                    icon = R.string.fog;
                    break;
                case 8:
                    icon = R.string.cloud;
                    break;
            }
        }
        return icon;
    }
}

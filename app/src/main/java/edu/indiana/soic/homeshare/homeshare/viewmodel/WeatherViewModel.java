package edu.indiana.soic.homeshare.homeshare.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.location.Location;
import android.util.Log;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.data.model.LocationLiveData;
import edu.indiana.soic.homeshare.homeshare.data.model.WeatherInfo;
import edu.indiana.soic.homeshare.homeshare.repository.WeatherRepository;

public class WeatherViewModel extends ViewModel {

    private WeatherRepository weatherRepository;
    private LocationLiveData location;
    public LiveData<WeatherInfo> weatherInfoLiveData;

    @Inject
    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        location = weatherRepository.getLocation();
        weatherInfoLiveData = Transformations.switchMap(location,
                (location) -> weatherRepository.getWeather(location));
    }

    public LiveData<WeatherInfo> getWeatherInfoLiveData() {
        return weatherInfoLiveData;
    }

    public void weatherByLocation() {
        weatherRepository.refreshLocation();
    }
}

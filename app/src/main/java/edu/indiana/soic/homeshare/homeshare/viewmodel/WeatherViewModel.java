package edu.indiana.soic.homeshare.homeshare.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.data.model.LocationLiveData;
import edu.indiana.soic.homeshare.homeshare.data.model.WeatherInfo;
import edu.indiana.soic.homeshare.homeshare.repository.WeatherRepository;

public class WeatherViewModel extends ViewModel {

    private WeatherRepository weatherRepository;
    private LocationLiveData locationLiveData;

    @Inject
    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        locationLiveData = weatherRepository.getLocationLiveData();
    }

    public LiveData<WeatherInfo> weatherInfoLiveData = Transformations.switchMap(locationLiveData,
            (location) -> weatherRepository.getWeather(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())));


    public LiveData<WeatherInfo> getWeatherInfoLiveData() {
        return weatherInfoLiveData;
    }
}

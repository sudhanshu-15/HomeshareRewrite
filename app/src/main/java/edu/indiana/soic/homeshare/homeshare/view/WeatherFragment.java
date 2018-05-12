package edu.indiana.soic.homeshare.homeshare.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.R;
import edu.indiana.soic.homeshare.homeshare.databinding.WeatherFragmentBinding;
import edu.indiana.soic.homeshare.homeshare.di.Injectable;
import edu.indiana.soic.homeshare.homeshare.viewmodel.WeatherViewModel;

public class WeatherFragment extends Fragment implements Injectable {
    public static final String TAG = "WeatherFragment";
    private WeatherViewModel weatherViewModel;
    private WeatherFragmentBinding weatherFragmentBinding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        weatherFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.weather_fragment, container, false);
        return weatherFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weatherViewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel.class);
        weatherViewModel.weatherByLocation();
        weatherViewModel.getWeatherInfoLiveData().observe(this, weatherInfo -> {
            if (weatherInfo != null) {
                weatherFragmentBinding.group.setVisibility(View.VISIBLE);
                weatherFragmentBinding.setWeather(weatherInfo);
                weatherFragmentBinding.icon.setText(weatherInfo.getIcon());
                weatherFragmentBinding.noWeather.setVisibility(View.GONE);
            } else {
                weatherFragmentBinding.group.setVisibility(View.GONE);
                weatherFragmentBinding.noWeather.setVisibility(View.VISIBLE);
            }
        });
    }
}

package edu.indiana.soic.homeshare.homeshare.repository;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.api.WeatherService;
import edu.indiana.soic.homeshare.homeshare.data.db.HomeshareDao;

public class WeatherRepository {

    private final Executor executor;
    private final HomeshareDao homeshareDao;
    private final WeatherService weatherService;
    public static final String TAG = "WeatherRepository";

    @Inject
    public WeatherRepository(Executor executor, HomeshareDao homeshareDao, WeatherService weatherService) {
        this.executor = executor;
        this.homeshareDao = homeshareDao;
        this.weatherService = weatherService;
    }
}

package edu.indiana.soic.homeshare.homeshare.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.indiana.soic.homeshare.homeshare.view.InterviewListFragment;
import edu.indiana.soic.homeshare.homeshare.view.SurveyListFragment;
import edu.indiana.soic.homeshare.homeshare.view.SurveyWebFragment;
import edu.indiana.soic.homeshare.homeshare.view.WeatherFragment;

@Module
public abstract class FragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract SurveyListFragment contributeSurveyListFragment();

    @ContributesAndroidInjector
    abstract SurveyWebFragment contributeSurveyWebFragment();

    @ContributesAndroidInjector
    abstract InterviewListFragment contributeInterviewListFragment();

    @ContributesAndroidInjector
    abstract WeatherFragment contributeWeatherFragment();
}

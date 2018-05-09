package edu.indiana.soic.homeshare.homeshare.di;

import com.journeyapps.barcodescanner.CaptureActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.indiana.soic.homeshare.homeshare.view.HomeActivity;
import edu.indiana.soic.homeshare.homeshare.view.UserActivity;

@Module
public abstract class HomeActivityModule {
    @ContributesAndroidInjector
    abstract HomeActivity contributeHomeActivityInjector();

    @ContributesAndroidInjector
    abstract UserActivity contributeUserActivityInjector();

    @ContributesAndroidInjector
    abstract CaptureActivity contributeCaptureActivityInjector();


}

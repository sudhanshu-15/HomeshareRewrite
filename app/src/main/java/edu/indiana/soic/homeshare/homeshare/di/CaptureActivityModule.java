package edu.indiana.soic.homeshare.homeshare.di;

import com.journeyapps.barcodescanner.CaptureActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CaptureActivityModule {
    @ContributesAndroidInjector
    abstract CaptureActivity contributeActivityInjector();
}

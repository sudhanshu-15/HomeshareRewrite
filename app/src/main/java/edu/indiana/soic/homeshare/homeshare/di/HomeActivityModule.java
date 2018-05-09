package edu.indiana.soic.homeshare.homeshare.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.indiana.soic.homeshare.homeshare.HomeActivity;

@Module
public abstract class HomeActivityModule {
    @ContributesAndroidInjector
    abstract HomeActivity contributeActivityInjector();
}

package edu.indiana.soic.homeshare.homeshare.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.indiana.soic.homeshare.homeshare.view.UserActivity;

@Module
public abstract class UserActivityModule {

    @ContributesAndroidInjector
    abstract UserActivity contributeActivityInjector();
}

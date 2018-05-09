package edu.indiana.soic.homeshare.homeshare.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.indiana.soic.homeshare.homeshare.view.SurveyListFragment;

@Module
public abstract class FragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract SurveyListFragment contributeSurveyListFragment();
}

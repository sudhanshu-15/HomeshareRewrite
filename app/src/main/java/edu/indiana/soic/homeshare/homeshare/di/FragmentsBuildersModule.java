package edu.indiana.soic.homeshare.homeshare.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.indiana.soic.homeshare.homeshare.view.SurveyListFragment;
import edu.indiana.soic.homeshare.homeshare.view.SurveyWebFragment;

@Module
public abstract class FragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract SurveyListFragment contributeSurveyListFragment();

    @ContributesAndroidInjector
    abstract SurveyWebFragment contributeSurveyWebFragment();
}

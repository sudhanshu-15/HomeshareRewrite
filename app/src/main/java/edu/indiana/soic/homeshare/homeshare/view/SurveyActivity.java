package edu.indiana.soic.homeshare.homeshare.view;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import edu.indiana.soic.homeshare.homeshare.R;

public class SurveyActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        if (savedInstanceState == null) {
            SurveyListFragment fragment = new SurveyListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.surveyFragmentContainer, fragment, SurveyListFragment.TAG)
                    .commit();
        }
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    public void showSurvey(String url) {
        SurveyWebFragment fragment = SurveyWebFragment.forSurveyLink(url);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.surveyFragmentContainer, fragment, null)
                .commit();
    }
}

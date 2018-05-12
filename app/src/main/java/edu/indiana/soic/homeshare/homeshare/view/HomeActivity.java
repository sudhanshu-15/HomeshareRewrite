package edu.indiana.soic.homeshare.homeshare.view;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import edu.indiana.soic.homeshare.homeshare.R;
import edu.indiana.soic.homeshare.homeshare.databinding.ActivityHomeBinding;
import edu.indiana.soic.homeshare.homeshare.viewmodel.HomeActivityViewModel;

public class HomeActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    private static final String GARMIN_PKG = "com.garmin.android.apps.connectmobile";
    private HomeActivityViewModel homeActivityViewModel;
    private ActivityHomeBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        if (savedInstanceState == null) {
            WeatherFragment weatherFragment = new WeatherFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, weatherFragment, weatherFragment.TAG)
                    .commit();
        }
        homeActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeActivityViewModel.class);
        homeActivityViewModel.getCount().observe(this, count -> {
            if (count != null) {
                binding.setCount(count);
                if (count.getSurveyCount() > 0) {
                    binding.surveyNumber.setVisibility(View.VISIBLE);
                } else {
                    binding.surveyNumber.setVisibility(View.GONE);
                }
                if (count.getInterviewCount() > 0) {
                    binding.interviewNumber.setVisibility(View.VISIBLE);
                } else {
                    binding.interviewNumber.setVisibility(View.GONE);
                }
            }
        });
        binding.contraintCard.findViewById(R.id.okButton)
                .setOnClickListener(view -> binding.cardNotified.setVisibility(View.GONE));
    }

    public void showSurvey(View view) {
        Intent surveyActivity = new Intent(getApplicationContext(), SurveyActivity.class);
        startActivity(surveyActivity);
    }

    public void showInterview(View view) {
        Intent interviewActivity = new Intent(getApplicationContext(), InterviewActivity.class);
        startActivity(interviewActivity);
    }

    public void startGarmin(View view) {
        Log.d("HomeActivity", "startGarmin: ");
        Intent garminApp = getPackageManager().getLaunchIntentForPackage(GARMIN_PKG);
        if (garminApp != null) {
            garminApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(garminApp);
        } else {
            garminApp = new Intent(Intent.ACTION_VIEW);
            garminApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            garminApp.setData(Uri.parse("market://details?id=" + GARMIN_PKG));
            startActivity(garminApp);
        }
    }

    public void contactResearchers(View view) {
        homeActivityViewModel.notifyResearcher();
        binding.cardNotified.setVisibility(View.VISIBLE);
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}

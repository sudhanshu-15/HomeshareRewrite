package edu.indiana.soic.homeshare.homeshare.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import edu.indiana.soic.homeshare.homeshare.R;

public class InterviewActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        if (savedInstanceState == null) {
            InterviewListFragment fragment = new InterviewListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.interviewFragmentContainer, fragment, InterviewListFragment.TAG)
                    .commit();
        }
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    public void startZoom(String zoomLink) {
        Intent openZoom = new Intent(Intent.ACTION_VIEW, Uri.parse(zoomLink));
        PackageManager packageManager = getPackageManager();
        if (openZoom.resolveActivity(packageManager) != null) {
            startActivity(openZoom);
        }

    }
}

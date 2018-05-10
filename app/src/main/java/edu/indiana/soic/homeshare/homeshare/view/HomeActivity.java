package edu.indiana.soic.homeshare.homeshare.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import edu.indiana.soic.homeshare.homeshare.R;

public class HomeActivity extends AppCompatActivity {

    private static final String GARMIN_PKG = "com.garmin.android.apps.connectmobile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
}

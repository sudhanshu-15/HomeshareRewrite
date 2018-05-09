package edu.indiana.soic.homeshare.homeshare.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.indiana.soic.homeshare.homeshare.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void showSurvey(View view) {
        Intent surveyActivity = new Intent(getApplicationContext(), SurveyActivity.class);
        startActivity(surveyActivity);
    }
}

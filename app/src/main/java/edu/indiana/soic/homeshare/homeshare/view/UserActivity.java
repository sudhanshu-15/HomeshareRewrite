package edu.indiana.soic.homeshare.homeshare.view;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import edu.indiana.soic.homeshare.homeshare.BuildConfig;
import edu.indiana.soic.homeshare.homeshare.R;
import edu.indiana.soic.homeshare.homeshare.api.ParticipantToken;
import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
import edu.indiana.soic.homeshare.homeshare.databinding.ActivityUserBinding;
import edu.indiana.soic.homeshare.homeshare.view.HomeActivity;
import edu.indiana.soic.homeshare.homeshare.viewmodel.UserActivityViewModel;

public class UserActivity extends AppCompatActivity {

    private ActivityUserBinding binding;
    private Participant participant;
    private UserActivityViewModel userActivityViewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        checkPermission();
        super.onCreate(savedInstanceState);
        userActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserActivityViewModel.class);
        checkFirstRun();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user);
    }

    public void scanParticipantDetails(View view) {
        launchQRScan();
    }

    public void saveParticipantDetails(View view) {
        String token = sharedPreferences.getString("firebaseToken", "");
        if (participant != null && token.length() != 0) {
            userActivityViewModel.insertParticipantDetails(participant);
            int currentVersionCode = BuildConfig.VERSION_CODE;
            sharedPreferences.edit().putInt("VERSION_CODE", currentVersionCode).apply();
            startHomeActivity();

        }
    }

    public void generateToken(View view) {
        String token = FirebaseInstanceId.getInstance().getToken();
        String pId = sharedPreferences.getString("pId", "");
        if (token.length() != 0){
            binding.firebaseToken.setText(token);
            sharedPreferences.edit().putString("firebaseToken", token).apply();
        }
        if (token.length() != 0 && pId.length() != 0){
            ParticipantToken participantToken = new ParticipantToken(pId, token);
            userActivityViewModel.sendTokenToServer(participantToken);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            Gson gson = new Gson();
            try {
                participant = gson.fromJson(result.getContents(), Participant.class);
                binding.setParticipant(participant);
                sharedPreferences.edit().putString("pId", participant.getParticipantId()).apply();
            }catch (Exception e) {
                Snackbar.make(binding.getRoot(), R.string.QRError, Snackbar.LENGTH_LONG);
                launchQRScan();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void checkFirstRun() {
        final int DOESNT_EXIST = -1;

        int currentVersionCode = BuildConfig.VERSION_CODE;

        int savedVersionCode = sharedPreferences.getInt("VERSION_CODE", DOESNT_EXIST);

        if (currentVersionCode == savedVersionCode) {
            Log.d("UserActivity", "checkFirstRun: Normal Run Detected");
            sharedPreferences.edit().putInt("VERSION_CODE", currentVersionCode).apply();
            Participant participant = userActivityViewModel.getParticipant();
            if(participant != null && participant.getParticipantId().length() > 0) {
                startHomeActivity();
            }
        }else if (savedVersionCode == DOESNT_EXIST) {
            Log.d("UserActivity", "checkFirstRun: First Run Detected");
        }else if (currentVersionCode > savedVersionCode) {
            Log.d("UserActivity", "checkFirstRun: Upgrade detected");
            sharedPreferences.edit().putInt("VERSION_CODE", currentVersionCode).apply();
            Participant participant = userActivityViewModel.getParticipant();
            if(participant != null && participant.getParticipantId().length() > 0) {
                startHomeActivity();
            }
        }
    }

    private void launchQRScan() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan participant QR to setup");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.initiateScan();
    }

    private void startHomeActivity() {
        Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }

    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION}, 111);
        }else{
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 111:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                   return;
                }else{
                    Log.d("No Permission", "onRequestPermissionsResult: ");;
                }
        }
    }

}

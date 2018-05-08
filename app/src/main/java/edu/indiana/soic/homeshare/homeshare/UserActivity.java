package edu.indiana.soic.homeshare.homeshare;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
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
import edu.indiana.soic.homeshare.homeshare.api.ParticipantToken;
import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
import edu.indiana.soic.homeshare.homeshare.databinding.ActivityUserBinding;
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
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        userActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserActivityViewModel.class);
    }

    public void scanParticipantDetails(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan participant QR to setup");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.initiateScan();
    }

    public void saveParticipantDetails(View view) {
        if (participant != null) {
            userActivityViewModel.insertParticipantDetails(participant);
        }
    }

    public void generateToken(View view) {
        String token = FirebaseInstanceId.getInstance().getToken();
        String pId = sharedPreferences.getString("pId", "");
        if (token.length() != 0){
            binding.firebaseToken.setText(token);
            sharedPreferences.edit().putString("firebaseToken", token).apply();
        }
        Log.d("UserActivity", "generateToken: " + pId);
        Log.d("UserActivity", "generateToken: " + token);
        if (token.length() != 0 && pId.length() != 0){
            Log.d("SendToken", "generateToken: ");
            ParticipantToken participantToken = new ParticipantToken(pId, token);
            userActivityViewModel.sendTokenToServer(participantToken);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            Gson gson = new Gson();
            participant = gson.fromJson(result.getContents(), Participant.class);
            binding.setParticipant(participant);
            sharedPreferences.edit().putString("pId", participant.getParticipantId()).apply();
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

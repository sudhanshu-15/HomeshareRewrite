package edu.indiana.soic.homeshare.homeshare.notification;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONObject;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.R;
import edu.indiana.soic.homeshare.homeshare.api.HomeshareService;
import edu.indiana.soic.homeshare.homeshare.api.ParticipantToken;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class HomeshareInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "HomeshareInstanceIDService";

    @Inject
    HomeshareService homeshareService;

    @Inject
    SharedPreferences sharedPreferences;


    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onTokenRefresh: " + refreshedToken);

        String pId = sharedPreferences.getString("pId", "");
        sharedPreferences.edit().putString("firebaseToken", refreshedToken).apply();

        if(refreshedToken.length() != 0 && pId.length() != 0){
            sendTokenToServer(refreshedToken, pId);
        }

    }

    private void sendTokenToServer(String refreshedToken, String pId){
        ParticipantToken participantToken = new ParticipantToken(pId, refreshedToken);
        homeshareService.updateToken(participantToken).enqueue(new Callback<ParticipantToken>() {
            @Override
            public void onResponse(Call<ParticipantToken> call, retrofit2.Response<ParticipantToken> response) {
                Log.d(TAG, "onResponse: Updated participant token");
            }

            @Override
            public void onFailure(Call<ParticipantToken> call, Throwable t) {
                Log.d(TAG, "onFailure: Error in updating participant token");
            }
        });
    }
}

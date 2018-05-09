package edu.indiana.soic.homeshare.homeshare.repository;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.indiana.soic.homeshare.homeshare.api.HomeshareService;
import edu.indiana.soic.homeshare.homeshare.api.ParticipantToken;
import edu.indiana.soic.homeshare.homeshare.data.db.ParticipantDao;
import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ParticipantDetailRepository {

    private final Executor executor;
    private final ParticipantDao participantDao;
    private final HomeshareService homeshareService;
    private static final String TAG = "ParticipantRepository";

    @Inject
    public ParticipantDetailRepository(HomeshareService homeshareService, ParticipantDao participantDao, Executor executor) {
        this.executor = executor;
        this.participantDao = participantDao;
        this.homeshareService = homeshareService;
    }

    public void insertParticipantDetails(Participant participant) {
        Log.d("Inserting", "insertParticipantDetails: " + participant.getFirstName());
        executor.execute(() -> {
            participantDao.addParticipant(participant);
        });
    }

    public void sendTokenToServer(ParticipantToken participantToken) {
        Log.d(TAG, "sendTokenToServer: here");
        homeshareService.updateToken(participantToken).enqueue(new Callback<ParticipantToken>() {
            @Override
            public void onResponse(Call<ParticipantToken> call, Response<ParticipantToken> response) {
                Log.d(TAG, "onResponse: Token sent to server");
            }

            @Override
            public void onFailure(Call<ParticipantToken> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed to send token to server");
            }
        });
    }

    public Participant getParticipant() {
        return participantDao.getParticipant();
    }
}

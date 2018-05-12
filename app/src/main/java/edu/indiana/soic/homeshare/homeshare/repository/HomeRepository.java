package edu.indiana.soic.homeshare.homeshare.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.api.HomeshareService;
import edu.indiana.soic.homeshare.homeshare.data.db.HomeshareDao;
import edu.indiana.soic.homeshare.homeshare.data.db.ParticipantDao;
import edu.indiana.soic.homeshare.homeshare.data.model.Count;
import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    public static final String TAG = "HomeRepository";
    private final Executor executor;
    private final HomeshareDao homeshareDao;
    private final HomeshareService homeshareService;
    private final ParticipantDao participantDao;

    @Inject
    public HomeRepository(Executor executor, HomeshareDao homeshareDao, HomeshareService homeshareService, ParticipantDao participantDao) {
        this.executor = executor;
        this.homeshareDao = homeshareDao;
        this.homeshareService = homeshareService;
        this.participantDao = participantDao;
    }

    public LiveData<Count> getCount() {
        return homeshareDao.getCount();
    }

    public void notifyResearchers() {
        Participant participant = participantDao.getParticipant();
        homeshareService.contactResearcher(participant.getParticipantId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: Notified Researcher");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed to notify");
            }
        });
    }
}

package edu.indiana.soic.homeshare.homeshare.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.api.HomeshareService;
import edu.indiana.soic.homeshare.homeshare.data.db.HomeshareDao;
import edu.indiana.soic.homeshare.homeshare.data.db.ParticipantDao;
import edu.indiana.soic.homeshare.homeshare.data.model.Data;
import edu.indiana.soic.homeshare.homeshare.data.model.Interview;
import edu.indiana.soic.homeshare.homeshare.data.model.Survey;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveyInterviewRepository {

    private final Executor executor;
    private final ParticipantDao participantDao;
    private final HomeshareDao homeshareDao;
    private final HomeshareService homeshareService;
    private static final String TAG = "HomeshareRepository";

    @Inject
    public SurveyInterviewRepository(Executor executor, ParticipantDao participantDao, HomeshareDao homeshareDao, HomeshareService homeshareService) {
        this.executor = executor;
        this.participantDao = participantDao;
        this.homeshareDao = homeshareDao;
        this.homeshareService = homeshareService;
    }

    public LiveData<List<Survey>> getSurveyList() {
        long currentTime = System.currentTimeMillis();
        return homeshareDao.getActiveSurveys(currentTime);
//        return homeshareDao.getAllSurveys();
    }

    public LiveData<List<Interview>> getInterviewList() {
        long currentTime = System.currentTimeMillis();
        return homeshareDao.getActiveInterviews(currentTime);
    }

    public void updateSurvey(Survey survey) {
        executor.execute(() -> {
            homeshareDao.updateSurvey(survey);
        });
    }

    public void updateInterview(Interview interview) {
        executor.execute(() -> {
            homeshareDao.updateInterview(interview);
        });
    }

    public void fetchFromServer(String pId) {
        executor.execute(() -> {
            int count = homeshareDao.getSurveyCount();
            boolean surveysExist = (count > 0);
            if (!surveysExist) {
                homeshareService.getInteviewsSurveys(pId).enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        executor.execute(() -> {
                            List<Survey> surveyList = response.body().getSurveys();
                            homeshareDao.addAllSurveys(surveyList);
                            List<Interview> interviewList = response.body().getInterviews();
                            homeshareDao.addAllInterviews(interviewList);
                        });
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t);
                    }
                });
            }
        });
    }
}

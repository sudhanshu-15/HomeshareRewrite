package edu.indiana.soic.homeshare.homeshare.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.data.model.Survey;
import edu.indiana.soic.homeshare.homeshare.repository.SurveyInterviewRepository;

public class SurveyListViewModel extends AndroidViewModel {

    private LiveData<List<Survey>> surveyList;
    private SurveyInterviewRepository repository;

    @Inject
    public SurveyListViewModel(SurveyInterviewRepository repository, Application application) {
        super(application);
        this.repository = repository;

    }

    public LiveData<List<Survey>> getSurveyList() {
        return surveyList;
    }

    public void refreshSurveyList() {

    }

    public void updateCompleted() {

    }
}

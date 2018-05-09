package edu.indiana.soic.homeshare.homeshare.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
import edu.indiana.soic.homeshare.homeshare.data.model.Survey;
import edu.indiana.soic.homeshare.homeshare.repository.ParticipantDetailRepository;
import edu.indiana.soic.homeshare.homeshare.repository.SurveyInterviewRepository;

public class SurveyListViewModel extends AndroidViewModel {

    private LiveData<List<Survey>> surveyList;
    private SurveyInterviewRepository repository;
    private ParticipantDetailRepository participantRepository;

    @Inject
    public SurveyListViewModel(SurveyInterviewRepository repository, ParticipantDetailRepository participantRepository, Application application) {
        super(application);
        this.repository = repository;
        this.participantRepository = participantRepository;
        surveyList = repository.getSurveyList();
    }

    public LiveData<List<Survey>> getSurveyList() {
        return surveyList;
    }

    public void refreshSurveyList() {
        Participant participant = getParticipant();
        Log.d("SurveyListVM", "refreshSurveyList: " + participant.getParticipantId());
        repository.fetchFromServer(participant.getParticipantId());
    }

    public void updateCompleted(Survey survey) {
        repository.updateSurvey(survey);
    }

    private Participant getParticipant() {
        return participantRepository.getParticipant();
    }
}

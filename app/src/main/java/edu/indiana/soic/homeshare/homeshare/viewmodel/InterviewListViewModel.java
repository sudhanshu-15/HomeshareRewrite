package edu.indiana.soic.homeshare.homeshare.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.data.model.Interview;
import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
import edu.indiana.soic.homeshare.homeshare.repository.ParticipantDetailRepository;
import edu.indiana.soic.homeshare.homeshare.repository.SurveyInterviewRepository;

public class InterviewListViewModel extends AndroidViewModel {

    private LiveData<List<Interview>> interviewList;
    private SurveyInterviewRepository repository;
    private ParticipantDetailRepository participantRepository;

    @Inject
    public InterviewListViewModel(@NonNull Application application, SurveyInterviewRepository repository, ParticipantDetailRepository participantRepository) {
        super(application);
        this.repository = repository;
        this.participantRepository = participantRepository;
        interviewList = repository.getInterviewList();
    }

    public LiveData<List<Interview>> getInterviewList() {
        return interviewList;
    }

    public void refreshInterviewList() {
        Participant participant = getParticipant();
        repository.fetchFromServer(participant.getParticipantId());
    }

    public Participant getParticipant() {
        return participantRepository.getParticipant();
    }
}

package edu.indiana.soic.homeshare.homeshare.viewmodel;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
import edu.indiana.soic.homeshare.homeshare.repository.ParticipantDetailRepository;

public class UserActivityViewModel extends ViewModel {

    private ParticipantDetailRepository participantDetailRepository;

    @Inject
    public UserActivityViewModel(ParticipantDetailRepository participantDetailRepository) {
        this.participantDetailRepository = participantDetailRepository;
    }

    public void insertParticipantDetails(Participant participant){
        participantDetailRepository.insertParticipantDetails(participant);
    }
}

package edu.indiana.soic.homeshare.homeshare.repository;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.indiana.soic.homeshare.homeshare.data.db.ParticipantDao;
import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
@Singleton
public class ParticipantDetailRepository {

    private final Executor executor;
    private final ParticipantDao participantDao;

    @Inject
    public ParticipantDetailRepository(ParticipantDao participantDao, Executor executor) {
        this.executor = executor;
        this.participantDao = participantDao;
    }

    public void insertParticipantDetails(Participant participant) {
        Log.d("Inserting", "insertParticipantDetails: " + participant.getFirstName());
        executor.execute(() -> {
            participantDao.addParticipant(participant);
        });
    }
}

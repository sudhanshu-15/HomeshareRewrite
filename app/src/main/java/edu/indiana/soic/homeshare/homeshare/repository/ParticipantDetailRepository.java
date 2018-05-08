package edu.indiana.soic.homeshare.homeshare.repository;

import java.util.concurrent.Executor;

import edu.indiana.soic.homeshare.homeshare.data.db.ParticipantDao;
import edu.indiana.soic.homeshare.homeshare.data.model.Participant;

public class ParticipantDetailRepository {

    private final Executor executor;
    private final ParticipantDao participantDao;

    public ParticipantDetailRepository(ParticipantDao participantDao, Executor executor) {
        this.executor = executor;
        this.participantDao = participantDao;
    }

    public void insertParticipantDetails(Participant participant) {
        executor.execute(() -> {
            participantDao.addParticipant(participant);
        });
    }
}

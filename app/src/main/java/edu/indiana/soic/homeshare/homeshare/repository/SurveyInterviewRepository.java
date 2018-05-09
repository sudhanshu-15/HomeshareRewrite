package edu.indiana.soic.homeshare.homeshare.repository;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.api.HomeshareService;
import edu.indiana.soic.homeshare.homeshare.data.db.HomeshareDao;
import edu.indiana.soic.homeshare.homeshare.data.db.ParticipantDao;

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
}

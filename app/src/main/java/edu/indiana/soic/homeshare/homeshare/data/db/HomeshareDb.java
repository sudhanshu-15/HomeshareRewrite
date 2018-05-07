package edu.indiana.soic.homeshare.homeshare.data.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import edu.indiana.soic.homeshare.homeshare.data.model.Interview;
import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
import edu.indiana.soic.homeshare.homeshare.data.model.Survey;

@Database(entities = {Participant.class, Survey.class, Interview.class}, version = 1)
public abstract class HomeshareDb extends RoomDatabase{

    abstract public HomeshareDao homeshareDao();

    abstract public ParticipantDao participantDao();
}

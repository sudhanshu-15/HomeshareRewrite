package edu.indiana.soic.homeshare.homeshare.data.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import edu.indiana.soic.homeshare.homeshare.data.model.Interview;
import edu.indiana.soic.homeshare.homeshare.data.model.Main;
import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
import edu.indiana.soic.homeshare.homeshare.data.model.Survey;
import edu.indiana.soic.homeshare.homeshare.data.model.Weather;
import edu.indiana.soic.homeshare.homeshare.data.model.WeatherInfo;

@Database(entities = {Participant.class, Survey.class, Interview.class, WeatherInfo.class}, version = 2)
public abstract class HomeshareDb extends RoomDatabase{

    abstract public HomeshareDao homeshareDao();

    abstract public ParticipantDao participantDao();
}

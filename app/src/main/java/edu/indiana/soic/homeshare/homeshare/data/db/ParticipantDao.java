package edu.indiana.soic.homeshare.homeshare.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import edu.indiana.soic.homeshare.homeshare.data.model.Participant;

@Dao
public interface ParticipantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addParticipant(Participant participant);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateParticipant(Participant participant);

    @Query("SELECT * FROM participant ORDER BY `dateCreated` LIMIT 1")
    Participant getParticipant();
}

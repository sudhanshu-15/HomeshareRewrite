package edu.indiana.soic.homeshare.homeshare.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.indiana.soic.homeshare.homeshare.data.model.Interview;
import edu.indiana.soic.homeshare.homeshare.data.model.Main;
import edu.indiana.soic.homeshare.homeshare.data.model.Survey;
import edu.indiana.soic.homeshare.homeshare.data.model.Weather;
import edu.indiana.soic.homeshare.homeshare.data.model.WeatherInfo;

@Dao
public interface HomeshareDao {

    @Query("SELECT * FROM surveys")
    LiveData<List<Survey>> getAllSurveys();

    @Query("SELECT * FROM interviews")
    LiveData<List<Interview>> getAllInterviews();

    @Query("SELECT * FROM surveys WHERE surveyId LIKE :surveyId")
    Survey getSurvey(String surveyId);

    @Query("SELECT * FROM surveys WHERE dueDate >= :currentDate AND status LIKE 'pending'")
    LiveData<List<Survey>> getActiveSurveys(long currentDate);

    @Query("SELECT * FROM interviews WHERE interviewId LIKE :interviewId")
    Interview getInterview(String interviewId);

    @Query("SELECT * FROM interviews WHERE dateScheduled >= :currentDate AND status LIKE 'pending'")
    LiveData<List<Interview>> getActiveInterviews(long currentDate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllSurveys(List<Survey> surveyList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllInterviews(List<Interview> interviewList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSurvey(Survey survey);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addInterview(Interview interview);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSurvey(Survey survey);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateInterview(Interview interview);

    @Query("SELECT COUNT(*) FROM surveys")
    int getSurveyCount();

    @Query("SELECT COUNT(*) FROM interviews")
    int getInterviewCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addWeather(WeatherInfo weather);

    @Query("SELECT * FROM weather ORDER BY `dateCreated` DESC LIMIT 1")
    Weather getWeather();

}

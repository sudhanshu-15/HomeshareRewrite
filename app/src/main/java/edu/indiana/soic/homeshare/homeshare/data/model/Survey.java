package edu.indiana.soic.homeshare.homeshare.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "surveys")
public class Survey {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String surveyId;
    private long dueDate;
    @SerializedName("projectname")
    private String projectName;
    private long dateSent;
    private String status;
    private String surveyUrl;
    private String description;
    private String participantId;
    private long dateModified;

    public Survey(int id, String surveyId, long dueDate, String projectName, long dateSent, String status, String surveyUrl, String description, String participantId, long dateModified) {
        this.id = id;
        this.surveyId = surveyId;
        this.dueDate = dueDate;
        this.projectName = projectName;
        this.dateSent = dateSent;
        this.status = status;
        this.surveyUrl = surveyUrl;
        this.description = description;
        this.participantId = participantId;
        this.dateModified = dateModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public long getDateSent() {
        return dateSent;
    }

    public void setDateSent(long dateSent) {
        this.dateSent = dateSent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSurveyUrl() {
        return surveyUrl;
    }

    public void setSurveyUrl(String surveyUrl) {
        this.surveyUrl = surveyUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public long getDateModified() {
        return dateModified;
    }

    public void setDateModified(long dateModified) {
        this.dateModified = dateModified;
    }
}

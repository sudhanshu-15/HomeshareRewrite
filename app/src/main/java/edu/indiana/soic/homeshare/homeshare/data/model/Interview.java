package edu.indiana.soic.homeshare.homeshare.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "interviews")
public class Interview {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String interviewId;
    private long dateScheduled;
    private long dateModified;
    @SerializedName("projectname")
    private String projectName;
    private String interviewer;
    private String status;
    private String participantId;
    private String description;

    public Interview(int id, String interviewId, long dateScheduled, long dateModified, String projectName, String interviewer, String status, String participantId, String description) {
        this.id = id;
        this.interviewId = interviewId;
        this.dateScheduled = dateScheduled;
        this.dateModified = dateModified;
        this.projectName = projectName;
        this.interviewer = interviewer;
        this.status = status;
        this.participantId = participantId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    public long getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(long dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

    public long getDateModified() {
        return dateModified;
    }

    public void setDateModified(long dateModified) {
        this.dateModified = dateModified;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

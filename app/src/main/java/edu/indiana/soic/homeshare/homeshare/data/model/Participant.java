package edu.indiana.soic.homeshare.homeshare.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "participant")
public class Participant {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String participantId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String wearable;
    private long dateOfBirth;
    private int age;
    private String address;
    private String email;
    private String lifelabId;
    private String homeId;
    private String zoomLink;
    private long dateCreated;

    public Participant(@NonNull int id, String participantId, String firstName, String middleName, String lastName, String wearable, long dateOfBirth, int age, String address, String email, String lifelabId, String homeId, String zoomLink, long dateCreated) {
        this.id = id;
        this.participantId = participantId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.wearable = wearable;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.address = address;
        this.email = email;
        this.lifelabId = lifelabId;
        this.homeId = homeId;
        this.zoomLink = zoomLink;
        this.dateCreated = dateCreated;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWearable() {
        return wearable;
    }

    public void setWearable(String wearable) {
        this.wearable = wearable;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLifelabId() {
        return lifelabId;
    }

    public void setLifelabId(String lifelabId) {
        this.lifelabId = lifelabId;
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public String getZoomLink() {
        return zoomLink;
    }

    public void setZoomLink(String zoomLink) {
        this.zoomLink = zoomLink;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }
}

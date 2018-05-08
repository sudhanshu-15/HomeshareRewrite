package edu.indiana.soic.homeshare.homeshare.api;

public class ParticipantToken {

    private String participantId;
    private String fbasetoken;

    public ParticipantToken(String participantId, String fbasetoken) {
        this.participantId = participantId;
        this.fbasetoken = fbasetoken;
    }
}

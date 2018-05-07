package edu.indiana.soic.homeshare.homeshare.data.model;

import java.util.List;

public class Data {

    private List<Survey> surveys;
    private List<Interview> interviews;

    public Data(List<Survey> surveys, List<Interview> interviews) {
        this.surveys = surveys;
        this.interviews = interviews;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }
}

package edu.indiana.soic.homeshare.homeshare.data.model;

public class Count {
    private int surveyCount;
    private int interviewCount;

    public Count(int surveyCount, int interviewCount) {
        this.surveyCount = surveyCount;
        this.interviewCount = interviewCount;
    }

    public int getSurveyCount() {
        return surveyCount;
    }

    public void setSurveyCount(int surveyCount) {
        this.surveyCount = surveyCount;
    }

    public int getInterviewCount() {
        return interviewCount;
    }

    public void setInterviewCount(int interviewCount) {
        this.interviewCount = interviewCount;
    }
}

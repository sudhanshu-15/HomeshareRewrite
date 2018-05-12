package edu.indiana.soic.homeshare.homeshare.di;

import dagger.Subcomponent;
import edu.indiana.soic.homeshare.homeshare.viewmodel.HomeActivityViewModel;
import edu.indiana.soic.homeshare.homeshare.viewmodel.InterviewListViewModel;
import edu.indiana.soic.homeshare.homeshare.viewmodel.SurveyListViewModel;
import edu.indiana.soic.homeshare.homeshare.viewmodel.UserActivityViewModel;
import edu.indiana.soic.homeshare.homeshare.viewmodel.WeatherViewModel;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    UserActivityViewModel userActivityViewModel();
    SurveyListViewModel surveyListViewModel();
    InterviewListViewModel interviewListViewModel();
    WeatherViewModel weatherViewModel();
    HomeActivityViewModel homeActivityViewModel();
}

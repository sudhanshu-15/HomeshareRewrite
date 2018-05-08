package edu.indiana.soic.homeshare.homeshare.di;

import dagger.Subcomponent;
import edu.indiana.soic.homeshare.homeshare.viewmodel.UserActivityViewModel;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    UserActivityViewModel userActivityViewModel();
}
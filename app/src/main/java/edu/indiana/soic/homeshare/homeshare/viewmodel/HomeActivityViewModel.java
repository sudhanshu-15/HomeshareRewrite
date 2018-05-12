package edu.indiana.soic.homeshare.homeshare.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.data.model.Count;
import edu.indiana.soic.homeshare.homeshare.repository.HomeRepository;

public class HomeActivityViewModel extends ViewModel {

    private HomeRepository homeRepository;

    @Inject
    public HomeActivityViewModel(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public LiveData<Count> getCount() {
        return homeRepository.getCount();
    }

    public void notifyResearcher() {
        homeRepository.notifyResearchers();
    }
}

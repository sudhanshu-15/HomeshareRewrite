package edu.indiana.soic.homeshare.homeshare.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.ArrayMap;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.indiana.soic.homeshare.homeshare.viewmodel.UserActivityViewModel;

@Singleton
public class HomeshareViewModelFactory implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public HomeshareViewModelFactory(ViewModelSubComponent viewModeSubComponent) {
        creators = new ArrayMap<>();

        creators.put(UserActivityViewModel.class, () -> viewModeSubComponent.userActivityViewModel());
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry :creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class" + modelClass);
        }
        try {
            return (T) creator.call();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

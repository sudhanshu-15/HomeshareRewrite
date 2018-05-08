package edu.indiana.soic.homeshare.homeshare.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import edu.indiana.soic.homeshare.homeshare.HomeshareApplication;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        UserActivityModule.class,
        AppModule.class,
        CaptureActivityModule.class
})
public interface AppComponent{
    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }
    void inject(HomeshareApplication homeshareApplication);
}

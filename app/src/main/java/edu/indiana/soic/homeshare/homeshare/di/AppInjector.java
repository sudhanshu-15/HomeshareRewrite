package edu.indiana.soic.homeshare.homeshare.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import dagger.android.AndroidInjection;
import dagger.android.support.HasSupportFragmentInjector;
import edu.indiana.soic.homeshare.homeshare.HomeshareApplication;

public class AppInjector {
    private AppInjector() {}

    public static void init(HomeshareApplication homeshareApplication) {
        DaggerAppComponent.builder().application(homeshareApplication)
                .build().inject(homeshareApplication);

        homeshareApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                handleActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private static void handleActivity(Activity activity) {
        if (activity instanceof Activity) {
            AndroidInjection.inject(activity);
        }
        if (activity instanceof HasSupportFragmentInjector) {
            AndroidInjection.inject(activity);
        }

    }
}

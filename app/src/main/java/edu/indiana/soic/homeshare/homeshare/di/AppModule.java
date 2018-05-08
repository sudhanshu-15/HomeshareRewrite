package edu.indiana.soic.homeshare.homeshare.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.indiana.soic.homeshare.homeshare.BuildConfig;
import edu.indiana.soic.homeshare.homeshare.api.HomeshareService;
import edu.indiana.soic.homeshare.homeshare.data.db.HomeshareDb;
import edu.indiana.soic.homeshare.homeshare.data.db.ParticipantDao;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class AppModule {

    @Singleton
    @Provides
    HomeshareDb provideDb(Application application) {
        return Room.databaseBuilder(application, HomeshareDb.class, "homeshare.db").build();
    }

    @Singleton
    @Provides
    ParticipantDao provideParticipantDao(HomeshareDb homeshareDb) {
        return homeshareDb.participantDao();
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Singleton
    @Provides
    HomeshareService provideHomeshareService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HomeshareService.class);
    }
}

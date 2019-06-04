package com.ajdi.yassin.newsreader.di;

import android.app.Application;

import androidx.room.Room;

import com.ajdi.yassin.newsreader.data.local.ArticlesDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.ajdi.yassin.newsreader.data.local.ArticlesDatabase.DATABASE_NAME;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
@Module
public class DBModule {

    @Singleton
    @Provides
    ArticlesDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(
                application.getApplicationContext(),
                ArticlesDatabase.class,
                DATABASE_NAME).build();
    }
}

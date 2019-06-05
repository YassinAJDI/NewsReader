package com.ajdi.yassin.newsreader.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ajdi.yassin.newsreader.data.local.ArticlesDatabase;
import com.ajdi.yassin.newsreader.data.model.Source;
import com.ajdi.yassin.newsreader.data.model.SourceResponse;
import com.ajdi.yassin.newsreader.data.remote.ArticlesService;
import com.ajdi.yassin.newsreader.utils.AppExecutors;

import java.io.IOException;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Response;
import timber.log.Timber;

import static com.ajdi.yassin.newsreader.data.local.ArticlesDatabase.DATABASE_NAME;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
@Module
public class DBModule {

    @Singleton
    @Provides
    ArticlesDatabase provideDatabase(Application application, AppExecutors appExecutors, ArticlesService service) {
        return Room.databaseBuilder(application.getApplicationContext(), ArticlesDatabase.class, DATABASE_NAME)
                // prepopulate the database after onCreate was called
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull final SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        // insert the data on the IO Thread
                        appExecutors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                Timber.d("populate database");
                                try {
                                    Response<SourceResponse> response = service.getAllSources().execute();
                                    if (response.isSuccessful()) {
                                        List<Source> sourceList = response.body().getSources();
                                        Timber.d("Number of sources: " + sourceList.size());
                                        Room.databaseBuilder(application.getApplicationContext(),
                                                ArticlesDatabase.class, DATABASE_NAME).build()
                                                .sourcesDao().insertSources(sourceList);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                })
                .build();
    }
}

package com.ajdi.yassin.newsreader.sync;

import androidx.annotation.NonNull;

import com.ajdi.yassin.newsreader.data.local.ArticlesDatabase;
import com.ajdi.yassin.newsreader.data.model.Article;
import com.ajdi.yassin.newsreader.data.model.NewsResponse;
import com.ajdi.yassin.newsreader.data.remote.ArticlesService;
import com.ajdi.yassin.newsreader.utils.AppExecutors;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.SimpleJobService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Response;
import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 6/6/2019.
 */
public class FeedsFirebaseJobService extends SimpleJobService {

    @Inject
    ArticlesDatabase mDatabase;
    @Inject
    ArticlesService mService;
    @Inject
    AppExecutors mExecutors;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public int onRunJob(@NonNull JobParameters job) {
        Timber.d("Firebase job service started");
        mExecutors.networkIO().execute(() -> {
            Timber.d("Fetch feeds started");
            try {
                Response<NewsResponse> response = mService.fetchTopHeadlines().execute();
                NewsResponse data = response.body();
                List<Article> articles = data != null ? data.getArticles() : Collections.emptyList();
                mDatabase.articlesDao().insertArticles(articles);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return RESULT_SUCCESS;
    }
}

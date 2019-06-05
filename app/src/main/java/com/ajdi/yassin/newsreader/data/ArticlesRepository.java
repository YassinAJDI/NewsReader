package com.ajdi.yassin.newsreader.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.ajdi.yassin.newsreader.data.local.ArticlesDatabase;
import com.ajdi.yassin.newsreader.data.model.Article;
import com.ajdi.yassin.newsreader.data.model.NewsResponse;
import com.ajdi.yassin.newsreader.data.model.Resource;
import com.ajdi.yassin.newsreader.data.remote.ApiResponse;
import com.ajdi.yassin.newsreader.data.remote.ArticlesService;
import com.ajdi.yassin.newsreader.utils.AppExecutors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
@Singleton
public class ArticlesRepository {

    private AppExecutors mExecutors;
    private ArticlesDatabase database;
    private ArticlesService articlesService;

    @Inject
    public ArticlesRepository(ArticlesDatabase db, ArticlesService service, AppExecutors executors) {
        database = db;
        articlesService = service;
        mExecutors = executors;
    }

    public LiveData<Resource<List<Article>>> loadTopHeadlines() {
        return new NetworkBoundResource<List<Article>, NewsResponse>(mExecutors) {
            @Override
            protected void saveCallResult(@NonNull NewsResponse item) {
                database.articlesDao().insertArticles(item.getArticles());
                Timber.d("Article list inserted into database");
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Article> data) {
                return data == null || data.isEmpty(); // only fetch fresh data if it doesn't exist in database
            }

            @NonNull
            @Override
            protected LiveData<List<Article>> loadFromDb() {
                Timber.d("Loading article list from database");
                return database.articlesDao().getAllArticles();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<NewsResponse>> createCall() {
                Timber.d("Downloading articles from network");
                return articlesService.getTopHeadlines();
            }

            @NonNull
            @Override
            protected void onFetchFailed() {
                // ignored
                Timber.d("Fetch failed!!");
            }
        }.getAsLiveData();
    }

}
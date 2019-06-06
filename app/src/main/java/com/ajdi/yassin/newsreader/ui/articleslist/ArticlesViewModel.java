package com.ajdi.yassin.newsreader.ui.articleslist;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ajdi.yassin.newsreader.R;
import com.ajdi.yassin.newsreader.data.ArticlesRepository;
import com.ajdi.yassin.newsreader.data.model.Feed;
import com.ajdi.yassin.newsreader.data.model.Resource;
import com.ajdi.yassin.newsreader.ui.pager.ArticlesFilterType;
import com.ajdi.yassin.newsreader.utils.Event;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
public class ArticlesViewModel extends ViewModel implements ArticleItemUserActionsListener {

    //    private LiveData<Resource<List<Article>>> result;
    private ArticlesRepository mRepository;
    private LiveData<Resource<List<Feed>>> resultFeeds;
    private LiveData<Resource<List<Feed>>> resultSport;
    private LiveData<Resource<List<Feed>>> resultTechnology;

    private MutableLiveData<Event<Integer>> mSnackbarMessageEvent = new MutableLiveData<>();
    private MutableLiveData<Event<String>> mOpenFeedDetailEvent = new MutableLiveData<>();

    @Inject
    public ArticlesViewModel(ArticlesRepository repository) {
        Timber.d("Initializing ArticlesViewModel");
        mRepository = repository;
//        result = repository.loadTopHeadlines();
        resultFeeds = mRepository.loadFeeds();
        resultSport = mRepository.loadFeedsFiltredBy(ArticlesFilterType.SPORTS);
        resultTechnology = mRepository.loadFeedsFiltredBy(ArticlesFilterType.TECHNOLOGY);
    }

//    public LiveData<Resource<List<Article>>> getResult() {
//        return result;
//    }

    public LiveData<Resource<List<Feed>>> getResultFeeds(ArticlesFilterType filterType) {
        switch (filterType) {
            case TOP_HEADLINES:
                return resultFeeds;
            case SPORTS:
                return resultSport;
            case TECHNOLOGY:
                return resultTechnology;
            default:
                throw new IllegalArgumentException("unknown filterType");
        }
    }

    /**
     * Called by {@link ArticleViewHolder}.
     */
    public void openFeedDetailEvent(String articleId) {
        mOpenFeedDetailEvent.setValue(new Event<>(articleId));
    }

    public LiveData<Event<String>> getOpenFeedDetailEvent() {
        return mOpenFeedDetailEvent;
    }

    public LiveData<List<Feed>> getFavoritesListLiveData() {
        // load by lazy
        return mRepository.getAllFavoriteArticles();
    }

    @Override
    public void onFavoriteClicked(@NonNull Feed feed) {
        mRepository.favoriteArticle(feed);
        mSnackbarMessageEvent.setValue(new Event<>(R.string.added_to_favorites));
    }

    @Override
    public void onUnFavoriteClicked(@NonNull Feed feed) {
        mRepository.unfavoriteArticle(feed);
        mSnackbarMessageEvent.setValue(new Event<>(R.string.removed_from_favorites));
    }

    public LiveData<Event<Integer>> getSnackbarMessageEvent() {
        return mSnackbarMessageEvent;
    }
}

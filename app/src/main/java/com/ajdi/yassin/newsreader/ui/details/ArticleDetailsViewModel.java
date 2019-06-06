package com.ajdi.yassin.newsreader.ui.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ajdi.yassin.newsreader.data.ArticlesRepository;
import com.ajdi.yassin.newsreader.data.model.Feed;
import com.ajdi.yassin.newsreader.utils.Event;

import javax.inject.Inject;

/**
 * @author Yassin Ajdi
 * @since 6/6/2019.
 */
public class ArticleDetailsViewModel extends ViewModel {

    private ArticlesRepository mRepository;
    private LiveData<Feed> feedLiveData;
    private MutableLiveData<Event<Integer>> mSnackbarMessageEvent = new MutableLiveData<>();

    @Inject
    public ArticleDetailsViewModel(ArticlesRepository repository) {
        mRepository = repository;
    }

    public void init(String articleId) {
        if (feedLiveData != null) {
            return; // load article details only once the activity created first time
        }

        feedLiveData = mRepository.getArticleDetails(articleId);
    }

    public LiveData<Feed> getFeedLiveData() {
        return feedLiveData;
    }
}

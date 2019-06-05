package com.ajdi.yassin.newsreader.ui.articleslist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ajdi.yassin.newsreader.data.ArticlesRepository;
import com.ajdi.yassin.newsreader.data.model.Feed;
import com.ajdi.yassin.newsreader.data.model.Resource;
import com.ajdi.yassin.newsreader.ui.pager.ArticlesFilterType;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
public class ArticlesViewModel extends ViewModel {

    //    private LiveData<Resource<List<Article>>> result;
    private LiveData<Resource<List<Feed>>> resultFeeds;
    private LiveData<Resource<List<Feed>>> resultSport;
    private LiveData<Resource<List<Feed>>> resultTechnology;

    @Inject
    public ArticlesViewModel(ArticlesRepository repository) {
        Timber.d("Initializing ArticlesViewModel");
//        result = repository.loadTopHeadlines();
        resultFeeds = repository.loadFeeds();
        resultSport = repository.loadFeedsFiltredBy(ArticlesFilterType.SPORTS);
        resultTechnology = repository.loadFeedsFiltredBy(ArticlesFilterType.TECHNOLOGY);
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
}

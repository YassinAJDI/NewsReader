package com.ajdi.yassin.newsreader.ui.articleslist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ajdi.yassin.newsreader.data.ArticlesRepository;
import com.ajdi.yassin.newsreader.data.model.Article;
import com.ajdi.yassin.newsreader.data.model.Resource;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
public class ArticlesViewModel extends ViewModel {

    private LiveData<Resource<List<Article>>> result;

    @Inject
    public ArticlesViewModel(ArticlesRepository repository) {
        Timber.d("Initializing ArticlesViewModel");
        result = repository.loadTopHeadlines();
    }

    public LiveData<Resource<List<Article>>> getResult() {
        return result;
    }
}

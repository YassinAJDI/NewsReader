package com.ajdi.yassin.newsreader.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ajdi.yassin.newsreader.di.scope.ViewModelKey;
import com.ajdi.yassin.newsreader.ui.articleslist.ArticlesViewModel;
import com.ajdi.yassin.newsreader.ui.details.ArticleDetailsViewModel;
import com.ajdi.yassin.newsreader.utils.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel.class)
    abstract ViewModel bindArticlesViewModel(ArticlesViewModel articlesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ArticleDetailsViewModel.class)
    abstract ViewModel bindArticleDetailsViewModel(ArticleDetailsViewModel articleDetailsViewModel);

}

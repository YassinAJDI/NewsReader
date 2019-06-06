package com.ajdi.yassin.newsreader.di;

import com.ajdi.yassin.newsreader.di.scope.FragmentScoped;
import com.ajdi.yassin.newsreader.ui.articleslist.ArticlesFragment;
import com.ajdi.yassin.newsreader.ui.details.ArticleDetailsFragment;
import com.ajdi.yassin.newsreader.ui.favorites.FavoritesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
@Module
public abstract class HomeActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ArticlesFragment contributeArticlesListFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract FavoritesFragment contributeFavoritesFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ArticleDetailsFragment contributeArticleDetailsFragment();

}

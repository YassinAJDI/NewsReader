package com.ajdi.yassin.newsreader.di;

import com.ajdi.yassin.newsreader.di.scope.ActivityScoped;
import com.ajdi.yassin.newsreader.ui.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
@Module
public abstract class ActivityBuilder {

    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity bindHomeActivity();

}

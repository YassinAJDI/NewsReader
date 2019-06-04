package com.ajdi.yassin.newsreader.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
@Singleton
@Component(modules = {
        DBModule.class,
        NetworkModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

//    void inject(NewsApplication app);
}

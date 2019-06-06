package com.ajdi.yassin.newsreader;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.ajdi.yassin.newsreader.di.DaggerAppComponent;
import com.ajdi.yassin.newsreader.sync.SyncUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
public class NewsApplication extends Application implements HasActivityInjector, HasServiceInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    @Inject
    DispatchingAndroidInjector<Service> dispatchingServiceInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        // trigger job to schedule & synchronize feeds periodically
        SyncUtils.scheduleFirebaseJobDispatcherSync(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return dispatchingServiceInjector;
    }
}

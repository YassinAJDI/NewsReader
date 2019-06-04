package com.ajdi.yassin.newsreader.utils;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
@Singleton
public class AppExecutors {

    private static final int THREAD_COUNT = 4;

    private final Executor diskIO;

    private final Executor mainThread;

    private final Executor networkIO;

    @Inject
    public AppExecutors() {
        this.diskIO = Executors.newSingleThreadExecutor();
        this.networkIO = Executors.newFixedThreadPool(THREAD_COUNT);
        this.mainThread = new MainThreadExecutor();
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor mainThread() {
        return mainThread;
    }

    public Executor networkIO() {
        return networkIO;
    }

    public static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}

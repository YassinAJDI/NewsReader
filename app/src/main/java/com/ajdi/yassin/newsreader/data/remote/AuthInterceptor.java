package com.ajdi.yassin.newsreader.data.remote;

import com.ajdi.yassin.newsreader.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Yassin Ajdi
 * @since 6/3/2019.
 */
public class AuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("apiKey", BuildConfig.NEWS_API_KEY)
                .build();

        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}

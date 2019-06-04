package com.ajdi.yassin.newsreader.di;

import com.ajdi.yassin.newsreader.data.model.Article;
import com.ajdi.yassin.newsreader.data.remote.ArticleDeserializer;
import com.ajdi.yassin.newsreader.data.remote.ArticlesService;
import com.ajdi.yassin.newsreader.data.remote.AuthInterceptor;
import com.ajdi.yassin.newsreader.utils.LiveDataCallAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
@Module
public class NetworkModule {

    private static final String BASE_URL = "https://newsapi.org/v2/";

    @Singleton
    @Provides
    ArticlesService provideArticlesService(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(client)
                .build()
                .create(ArticlesService.class);
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Article.class, new ArticleDeserializer())
                .create();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHTTPClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new AuthInterceptor())
                .build();
    }
}

package com.ajdi.yassin.newsreader.data.remote;

import com.ajdi.yassin.newsreader.data.model.Article;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Yassin Ajdi
 * @since 6/3/2019.
 */
public class ApiClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final OkHttpClient client;

    private static final Object sLock = new Object();

    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new AuthInterceptor())
                .build();
    }

    private static Retrofit getRetrofitInstance() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Article.class, new ArticleDeserializer()).create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}

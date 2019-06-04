package com.ajdi.yassin.newsreader.data.remote;

import com.ajdi.yassin.newsreader.data.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
public interface ArticlesService {

    @GET("/top-headlines")
    Call<NewsResponse> getTopHeadlines();

    @GET("/top-headlines?country=us")
    Call<NewsResponse> getNewsForCategory(@Query("category") String category);
}

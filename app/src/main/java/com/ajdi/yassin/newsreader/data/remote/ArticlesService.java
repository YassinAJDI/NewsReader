package com.ajdi.yassin.newsreader.data.remote;

import androidx.lifecycle.LiveData;

import com.ajdi.yassin.newsreader.data.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
public interface ArticlesService {

    @GET("top-headlines?country=us")
    LiveData<ApiResponse<NewsResponse>> getTopHeadlines();

    @GET("top-headlines?country=us")
    Call<NewsResponse> getNewsForCategory(@Query("category") String category);
}

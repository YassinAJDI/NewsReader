package com.ajdi.yassin.newsreader.data.remote;

import androidx.lifecycle.LiveData;

import com.ajdi.yassin.newsreader.data.model.NewsResponse;
import com.ajdi.yassin.newsreader.data.model.SourceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
public interface ArticlesService {

    @GET("sources")
    Call<SourceResponse> getAllSources();

    @GET("top-headlines?country=us&Size=100")
    Call<NewsResponse> fetchTopHeadlines();

    @GET("top-headlines?country=us&Size=100")
    LiveData<ApiResponse<NewsResponse>> getTopHeadlines();

//    @GET("everything?domains=wsj.com,nytimes.com&pageSize=100")
//    LiveData<ApiResponse<NewsResponse>> getSportNews();

    @GET("top-headlines?country=us&pageSize=100")
    LiveData<ApiResponse<NewsResponse>> getNewsForCategory(@Query("category") String category);
}

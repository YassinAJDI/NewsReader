package com.ajdi.yassin.newsreader.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ajdi.yassin.newsreader.data.model.Article;
import com.ajdi.yassin.newsreader.data.model.Feed;

import java.util.List;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
@Dao
public interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertArticles(List<Article> articleList);

    @Query("SELECT * FROM article")
    LiveData<List<Article>> getAllArticles();

    /**
     * Query that returns a list of feeds.
     */
    @Query("SELECT article.id, title, published_at, url_to_image, article.description, source_name, source_url "
            + "FROM article LEFT JOIN source ON source_id = source.id"
    )
    LiveData<List<Feed>> getAllArticlesWithSource();
}

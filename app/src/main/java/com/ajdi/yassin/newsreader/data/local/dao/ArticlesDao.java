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
    @Query("SELECT article.id, title, published_at, url_to_image, is_favorite, article.description, " +
            "source_name, source_url "
            + "FROM article LEFT JOIN source ON source_id = source.id"
    )
    LiveData<List<Feed>> getAllArticlesWithSource();

    /**
     * Query that returns a list of feeds for certain category.
     */
    @Query("SELECT article.id, title, published_at, url_to_image, is_favorite, article.description, " +
            "source_name, source_url "
            + "FROM article INNER JOIN source ON source_id = source.id WHERE category =:category"
    )
    LiveData<List<Feed>> getArticlesForCategory(String category);

    /**
     * Favorite article.
     */
    @Query("UPDATE article SET is_favorite = 1 WHERE id = :articleId")
    int favoriteArticle(String articleId);

    /**
     * Unfavorite article.
     */
    @Query("UPDATE article SET is_favorite = 0 WHERE id = :articleId")
    int unFavoriteArticle(String articleId);
}

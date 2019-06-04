package com.ajdi.yassin.newsreader.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.ajdi.yassin.newsreader.data.model.Article;

import java.util.List;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
@Dao
public interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertArticles(List<Article> articleList);

    @Transaction
    @Query("SELECT * FROM article")
    LiveData<List<Article>> getAllArticles();
}

package com.ajdi.yassin.newsreader.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ajdi.yassin.newsreader.data.local.dao.ArticlesDao;
import com.ajdi.yassin.newsreader.data.local.dao.SourcesDao;
import com.ajdi.yassin.newsreader.data.model.Article;
import com.ajdi.yassin.newsreader.data.model.Source;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
@Database(entities = {Article.class, Source.class},
        version = 1,
        exportSchema = false)
public abstract class ArticlesDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "News.db";

    public abstract ArticlesDao articlesDao();
    public abstract SourcesDao sourcesDao();

}

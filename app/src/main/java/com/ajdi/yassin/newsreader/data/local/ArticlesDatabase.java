package com.ajdi.yassin.newsreader.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ajdi.yassin.newsreader.data.local.dao.ArticlesDao;
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

    public static final String DATABASE_NAME = "Movies.db";

    public abstract ArticlesDao articlesDao();

    private static ArticlesDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                ArticlesDatabase.class,
                DATABASE_NAME).build();
    }
}

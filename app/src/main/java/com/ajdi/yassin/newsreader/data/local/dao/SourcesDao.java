package com.ajdi.yassin.newsreader.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.ajdi.yassin.newsreader.data.model.Source;

import java.util.List;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
@Dao
public interface SourcesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSources(List<Source> sourceList);
}

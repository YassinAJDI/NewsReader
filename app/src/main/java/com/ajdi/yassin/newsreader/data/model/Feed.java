package com.ajdi.yassin.newsreader.data.model;

import androidx.room.ColumnInfo;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
public class Feed {

    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "published_at")
    public String publishedAt;

    @ColumnInfo(name = "url_to_image")
    public String urlToImage;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "source_name")
    public String sourceName;

    @ColumnInfo(name = "source_url")
    public String sourceUrl;

}

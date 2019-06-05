package com.ajdi.yassin.newsreader.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * @author Yassin Ajdi
 * @since 6/3/2019.
 */
@Entity(tableName = "article"
//        foreignKeys = @ForeignKey(
//                entity = Source.class,
//                parentColumns = "id",
//                childColumns = "source_id",
//                onDelete = CASCADE,
//                onUpdate = CASCADE
//        ),
//        indices = {
//                @Index(value = {"source_id"})
//        }
)
public class Article {

    @NonNull
    @PrimaryKey
    private String id;

//    @ColumnInfo(name = "source_id")
//    private String sourceId;

    @SerializedName("author")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @ColumnInfo(name = "url_to_image")
    @SerializedName("urlToImage")
    private String urlToImage;

    @ColumnInfo(name = "published_at")
    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("content")
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public String getSourceId() {
//        return sourceId;
//    }
//
//    public void setSourceId(String sourceId) {
//        this.sourceId = sourceId;
//    }
}

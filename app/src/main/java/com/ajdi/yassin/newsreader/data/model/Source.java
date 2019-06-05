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
@Entity(tableName = "Source")
public class Source {

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    private String id;

    @ColumnInfo(name = "source_name")
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @ColumnInfo(name = "source_url")
    @SerializedName("url")
    private String url;

    @SerializedName("category")
    private String category;

    @SerializedName("language")
    private String language;

    @SerializedName("country")
    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

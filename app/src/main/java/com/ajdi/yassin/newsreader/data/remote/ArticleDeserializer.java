package com.ajdi.yassin.newsreader.data.remote;

import com.ajdi.yassin.newsreader.data.model.Article;
import com.ajdi.yassin.newsreader.utils.StringUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author Yassin Ajdi
 * @since 6/3/2019.
 */
public class ArticleDeserializer implements JsonDeserializer<Article> {

    @Override
    public Article deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        String author = null;
        String title = null;
        String description = null;
        String url = null;
        String urlToImage = null;
        String publishedAt = new Date().toString();
        String content;

        JsonObject jsonObject = json.getAsJsonObject();
//        JsonObject source = jsonObject.get("source").getAsJsonObject();

        if (!jsonObject.get("author").isJsonNull()) {
            author = jsonObject.get("author").getAsString();
        }
        if (!jsonObject.get("title").isJsonNull()) {
            title = jsonObject.get("title").getAsString();
        }
        if (!jsonObject.get("url").isJsonNull()) {
            url = jsonObject.get("url").getAsString();
        }
        if (!jsonObject.get("urlToImage").isJsonNull()) {
            urlToImage = jsonObject.get("urlToImage").getAsString();
        }
        if (!jsonObject.get("publishedAt").isJsonNull()) {
            publishedAt = jsonObject.get("publishedAt").getAsString();
        }
        if (!jsonObject.get("description").isJsonNull()) {
            description = jsonObject.get("description").getAsString();
        }
        if (!jsonObject.get("content").isJsonNull()) {
            content = jsonObject.get("content").getAsString();
        } else {
            content = description;
        }

        Article article = new Article();
        article.setAuthor(author);
        article.setTitle(title);
        article.setUrlToImage(urlToImage);
        article.setPublishedAt(publishedAt);
        article.setDescription(description);
        article.setContent(content);
        article.setUrl(url);
        article.setId(StringUtils.sha1(url));
//        article.setSourceId(source.get("id").getAsString());

        return article;
    }
}

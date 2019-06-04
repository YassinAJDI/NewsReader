package com.ajdi.yassin.newsreader.data.remote;

import android.text.TextUtils;

import com.ajdi.yassin.newsreader.data.model.Article;
import com.ajdi.yassin.newsreader.utils.StringUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @author Yassin Ajdi
 * @since 6/3/2019.
 */
public class ArticleDeserializer implements JsonDeserializer<Article> {

    @Override
    public Article deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject source = jsonObject.get("source").getAsJsonObject();
        Article article = new Article();
        String url = jsonObject.get("url").getAsString();
        String content = jsonObject.get("content").getAsString();
        String description = jsonObject.get("description").getAsString();

        article.setAuthor(jsonObject.get("author").getAsString());
        article.setTitle(jsonObject.get("title").getAsString());
        article.setUrl(url);
        article.setUrlToImage(jsonObject.get("urlToImage").getAsString());
        article.setPublishedAt(jsonObject.get("publishedAt").getAsString());
        article.setDescription(description);
        article.setContent(TextUtils.isEmpty(content) ? description : content);
        article.setSourceId(source.get("id").getAsString());
        article.setId(StringUtils.sha1(url));

        return article;
    }
}

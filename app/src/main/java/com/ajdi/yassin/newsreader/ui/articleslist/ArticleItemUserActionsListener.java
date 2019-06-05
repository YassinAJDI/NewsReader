package com.ajdi.yassin.newsreader.ui.articleslist;

import androidx.annotation.NonNull;

import com.ajdi.yassin.newsreader.data.model.Feed;

/**
 * Listener used to process user actions on article items
 *
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
public interface ArticleItemUserActionsListener {

    void onFavoriteClicked(@NonNull Feed feed);

    void onUnFavoriteClicked(@NonNull Feed feed);
}

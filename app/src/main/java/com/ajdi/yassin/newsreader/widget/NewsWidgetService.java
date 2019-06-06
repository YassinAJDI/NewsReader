package com.ajdi.yassin.newsreader.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.room.Room;

import com.ajdi.yassin.newsreader.R;
import com.ajdi.yassin.newsreader.data.local.ArticlesDatabase;
import com.ajdi.yassin.newsreader.data.model.Article;

import java.util.ArrayList;
import java.util.List;

import static com.ajdi.yassin.newsreader.data.local.ArticlesDatabase.DATABASE_NAME;

/**
 * @author Yassin Ajdi
 * @since 6/6/2019.
 */
public class NewsWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new NewsRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

class NewsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private List<Article> articles = new ArrayList<>();
    private Context mContext;

    public NewsRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        // This is triggered when you call AppWidgetManager notifyAppWidgetViewDataChanged
        // on the collection view corresponding to this factory. You can do heaving lifting in
        // here, synchronously. For example, if you need to process an image, fetch something
        // from the network, etc., it is ok to do it here, synchronously. The widget will remain
        // in its current state while work is being done here, so you don't need to worry about
        // locking up the widget.
        articles.clear();
        ArticlesDatabase db = Room.databaseBuilder(mContext, ArticlesDatabase.class, DATABASE_NAME).build();
        articles = db.articlesDao().getAllArticles();
    }

    @Override
    public void onDestroy() {
        // In onDestroy() you should tear down anything that was setup for your data source,
        // eg. cursors, connections, etc.
        articles.clear();
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        // We construct a remote views item based on our widget item xml file, and set the
        // text based on the position.
        final RemoteViews remoteView = new RemoteViews(mContext.getPackageName(), R.layout.item_widget_row);
        Article article = articles.get(position);
        remoteView.setTextViewText(R.id.text_title, article.getTitle());
        // Next, set a fill-intent, which will be used to fill in the pending intent template
        // that is set on the collection view in StackWidgetProvider.
        Bundle extras = new Bundle();
        extras.putString(NewsWidgetProvider.EXTRA_ITEM, article.getId());
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        // Make it possible to distinguish the individual on-click
        // action of a given item
        remoteView.setOnClickFillInIntent(R.id.widget_item, fillInIntent);
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

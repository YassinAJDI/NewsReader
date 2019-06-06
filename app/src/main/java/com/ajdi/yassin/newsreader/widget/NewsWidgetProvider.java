package com.ajdi.yassin.newsreader.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.ajdi.yassin.newsreader.R;
import com.ajdi.yassin.newsreader.ui.HomeActivity;
import com.ajdi.yassin.newsreader.ui.details.ArticleDetailsFragment;

/**
 * Implementation of App Widget functionality.
 */
public class NewsWidgetProvider extends AppWidgetProvider {

    private static final String ARTICLE_CLICK_ACTION = "com.ajdi.ARTICLE_CLICK_ACTION";
    public static final String EXTRA_ITEM = "com.ajdi.EXTRA_ITEM";

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(ARTICLE_CLICK_ACTION)) {
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            String articleId = intent.getStringExtra(EXTRA_ITEM);
            Intent articleIntent = new Intent(context, HomeActivity.class);
            intent.putExtra(ArticleDetailsFragment.EXTRA_ARTICLE, articleId);
            articleIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(articleIntent);
        }
        super.onReceive(context, intent);
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Set up the intent that starts the NewsWidgetService, which will
        // provide the views for this collection.
        Intent intent = new Intent(context, NewsWidgetService.class);
        // Add the app widget ID to the intent extras.
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        // Instantiate the RemoteViews object for the app widget layout.
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_news);
        // Set up the RemoteViews object to use a RemoteViews adapter.
        // This adapter connect to a RemoteViewsService through the specified intent.
        remoteViews.setRemoteAdapter(R.id.widget_list, intent);
        // The empty view is displayed when the collection has no items. It should be a sibling
        // of the collection view.
        remoteViews.setEmptyView(R.id.widget_list, R.id.empty_view);
        // Here we setup the a pending intent template. Individuals items of a collection
        // cannot setup their own pending intents, instead, the collection as a whole can
        // setup a pending intent template, and the individual items can set a fillInIntent
        // to create unique before on an item to item basis.
        Intent clickIntent = new Intent(context, NewsWidgetProvider.class);
        // Set the action for the intent.
        // When the user touches a particular view, it will have the effect of
        // broadcasting ARTICLE_CLICK_ACTION.
        clickIntent.setAction(NewsWidgetProvider.ARTICLE_CLICK_ACTION);
        clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        clickIntent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context, 0, clickIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.widget_list, clickPendingIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


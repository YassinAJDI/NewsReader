package com.ajdi.yassin.newsreader.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.ShareCompat;

import com.ajdi.yassin.newsreader.data.model.Feed;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
public class UiUtils {

    // Fire a share intent for sharing articles
    public static void fireShareIntent(Context context, Feed feed) {
        Intent shareIntent = ShareCompat.IntentBuilder.from((Activity) context)
                .setType("text/plain")
                .setSubject(feed.title)
                // TODO: change link to article link
                .setText(feed.title + "\n" + feed.content)
                .createChooserIntent();

        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;

        shareIntent.addFlags(flags);
        if (shareIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(shareIntent);
        }
    }
}

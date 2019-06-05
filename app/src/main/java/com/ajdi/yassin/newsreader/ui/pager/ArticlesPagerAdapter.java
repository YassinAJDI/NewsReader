package com.ajdi.yassin.newsreader.ui.pager;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ajdi.yassin.newsreader.R;
import com.ajdi.yassin.newsreader.ui.articleslist.ArticlesFragment;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
public class ArticlesPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ArticlesPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ArticlesFragment.newInstance(ArticlesFilterType.TOP_HEADLINES);
            case 1:
                return ArticlesFragment.newInstance(ArticlesFilterType.SPORTS);
            case 2:
                return ArticlesFragment.newInstance(ArticlesFilterType.TECHNOLOGY);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String[] titles = mContext.getResources().getStringArray(R.array.pager_titles);
        switch (position) {
            case 0:
                return titles[0];
            case 1:
                return titles[1];
            case 2:
                return titles[2];
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}

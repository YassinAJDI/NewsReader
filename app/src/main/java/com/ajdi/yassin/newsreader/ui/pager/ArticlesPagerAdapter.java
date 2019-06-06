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
                return ArticlesFragment.newInstance(ArticlesFilterType.TECHNOLOGY);
            case 2:
                return ArticlesFragment.newInstance(ArticlesFilterType.ENTERTAINMENT);
            case 3:
                return ArticlesFragment.newInstance(ArticlesFilterType.HEALTH);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String[] titles = mContext.getResources().getStringArray(R.array.pager_titles);
        return titles[position];
    }

    @Override
    public int getCount() {
        return 4;
    }
}

package com.ajdi.yassin.newsreader.ui.details;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ajdi.yassin.newsreader.R;
import com.ajdi.yassin.newsreader.data.model.Feed;
import com.ajdi.yassin.newsreader.databinding.FragmentArticleDetailsBinding;
import com.ajdi.yassin.newsreader.utils.GlideApp;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleDetailsFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private FragmentArticleDetailsBinding mBinding;
    private ArticleDetailsViewModel mViewModel;
    private String articleId;

    public ArticleDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        articleId = getArguments().getString("article_id");
        if (articleId == null) {
            closeOnError();
            return;
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentArticleDetailsBinding.inflate(inflater, container, false);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ArticleDetailsViewModel.class);
        mViewModel.init(articleId);
        // observe feed details
        mViewModel.getFeedLiveData().observe(getViewLifecycleOwner(), new Observer<Feed>() {
            @Override
            public void onChanged(Feed feed) {
                if (feed != null) {
                    populateUi(feed);
                }
            }
        });
        return mBinding.getRoot();
    }

    private void populateUi(Feed feed) {
        // source
        String sourceUrl = feed.sourceUrl;
        String sourceImage = null;
        if (!TextUtils.isEmpty(sourceUrl)) {
            try {
                String host = new URL(sourceUrl).getHost();
                sourceImage = "https://icons.duckduckgo.com/ip2/" + host + ".ico";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        GlideApp.with(this)
                .load(sourceImage)
                .placeholder(R.color.md_grey_200)
                .into(mBinding.imagePublisherIcon);
        mBinding.textPublisherName.setText(feed.sourceName);
        // publish date
        mBinding.textTime.setText(feed.publishedAt);
        // title
        mBinding.textArticleTitle.setText(feed.title);
        // article image
        GlideApp.with(this)
                .load(feed.urlToImage)
                .placeholder(R.color.md_grey_200)
                .into(mBinding.imageArticleImage);
        // content
        mBinding.articleContent.setText(feed.content);
        mBinding.executePendingBindings();
    }

    private void closeOnError() {
        throw new IllegalArgumentException("Access denied.");
    }

}

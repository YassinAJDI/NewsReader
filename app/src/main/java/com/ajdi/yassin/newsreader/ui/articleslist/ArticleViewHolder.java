package com.ajdi.yassin.newsreader.ui.articleslist;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajdi.yassin.newsreader.R;
import com.ajdi.yassin.newsreader.data.model.Feed;
import com.ajdi.yassin.newsreader.databinding.ItemArticleBinding;
import com.ajdi.yassin.newsreader.utils.GlideApp;
import com.ajdi.yassin.newsreader.utils.UiUtils;
import com.bumptech.glide.request.RequestOptions;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder {
    public final int ALPHA_VALUE = 138;
    private final ItemArticleBinding binding;
    private ArticlesViewModel viewModel;

    public ArticleViewHolder(@NonNull ItemArticleBinding binding, ArticlesViewModel viewModel) {
        super(binding.getRoot());
        this.binding = binding;
        this.viewModel = viewModel;
    }

    public static ArticleViewHolder create(ViewGroup parent, ArticlesViewModel viewModel) {
        // Inflate
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Create the binding
        ItemArticleBinding binding =
                ItemArticleBinding.inflate(layoutInflater, parent, false);
        return new ArticleViewHolder(binding, viewModel);
    }

    public void bindTo(final Feed feed) {
        // feed image
        GlideApp.with(binding.getRoot())
                .load(feed.urlToImage)
                .placeholder(R.color.md_grey_200)
                .into(binding.imageNote);
        // source image
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
        GlideApp.with(binding.getRoot())
                .load(sourceImage)
                .apply(new RequestOptions().circleCrop())
                .placeholder(R.color.md_grey_200)
                .into(binding.imageUserIcon);
        binding.textPublisherName.setText(feed.sourceName);
        binding.textTitle.setText(feed.title);
        binding.textTime.setText(feed.publishedAt);
        // card add to favorites button
        binding.cardActionStarButton.setImageResource(
                feed.favorite ? R.drawable.ic_star_black_24dp : R.drawable.ic_star_border_black_24dp);
        binding.cardActionStarButton.setImageAlpha(ALPHA_VALUE);
        binding.cardActionStarButton.setOnClickListener(view -> {
            if (!feed.favorite) {
                viewModel.onFavoriteClicked(feed);
            } else {
                viewModel.onUnFavoriteClicked(feed);
            }
        });
        // card share button
        binding.cardActionShareButton.setImageResource(R.drawable.ic_share_black_24dp);
        binding.cardActionShareButton.setImageAlpha(ALPHA_VALUE);
        binding.cardActionShareButton.setOnClickListener(view ->
                UiUtils.fireShareIntent(binding.getRoot().getContext(), feed));
        binding.executePendingBindings();
    }
}

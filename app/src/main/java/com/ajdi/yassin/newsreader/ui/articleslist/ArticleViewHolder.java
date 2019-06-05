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
import com.bumptech.glide.request.RequestOptions;

import java.net.MalformedURLException;
import java.net.URL;

import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder {

    private final ItemArticleBinding binding;

    public ArticleViewHolder(@NonNull ItemArticleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public static ArticleViewHolder create(ViewGroup parent) {
        // Inflate
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Create the binding
        ItemArticleBinding binding =
                ItemArticleBinding.inflate(layoutInflater, parent, false);
        return new ArticleViewHolder(binding);
    }

    public void bindTo(final Feed article) {
        final int adapterPosition = getAdapterPosition();
        Timber.d("binding position: " + adapterPosition);
        // article image
        GlideApp.with(binding.getRoot())
                .load(article.urlToImage)
                .placeholder(R.color.md_grey_200)
                .into(binding.imageNote);
        // source image
        String sourceUrl = article.sourceUrl;
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
        binding.textPublisherName.setText(article.sourceName);
        binding.textTitle.setText(article.title);
        binding.textTime.setText(article.publishedAt);
        binding.executePendingBindings();
    }
}

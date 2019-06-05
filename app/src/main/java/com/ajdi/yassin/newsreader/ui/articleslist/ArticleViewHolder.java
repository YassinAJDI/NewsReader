package com.ajdi.yassin.newsreader.ui.articleslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajdi.yassin.newsreader.R;
import com.ajdi.yassin.newsreader.data.model.Article;
import com.ajdi.yassin.newsreader.databinding.ItemArticleBinding;
import com.ajdi.yassin.newsreader.utils.GlideApp;

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

    public void bindTo(final Article article) {
        final int adapterPosition = getAdapterPosition();
        Timber.d("binding position: " + adapterPosition);
        GlideApp.with(binding.getRoot())
                .load(article.getUrlToImage())
                .placeholder(R.color.md_grey_200)
                .into(binding.imageNote);
        binding.textTitle.setText(article.getTitle());
        binding.textTime.setText(article.getPublishedAt());
        binding.executePendingBindings();
    }
}

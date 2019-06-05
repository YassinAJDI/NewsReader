package com.ajdi.yassin.newsreader.ui.articleslist;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajdi.yassin.newsreader.data.model.Feed;

import java.util.List;

/**
 * @author Yassin Ajdi
 * @since 6/4/2019.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Feed> mArticleList;
    private ArticlesViewModel mViewModel;

    public ArticlesAdapter(ArticlesViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ArticleViewHolder.create(parent, mViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bindTo(mArticleList.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticleList != null ? mArticleList.size() : 0;
    }

    public void submitList(List<Feed> articles) {
        mArticleList = articles;
        notifyDataSetChanged();
    }
}

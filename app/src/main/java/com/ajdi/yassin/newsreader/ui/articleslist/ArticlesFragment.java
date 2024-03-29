package com.ajdi.yassin.newsreader.ui.articleslist;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ajdi.yassin.newsreader.R;
import com.ajdi.yassin.newsreader.databinding.FragmentArticlesBinding;
import com.ajdi.yassin.newsreader.ui.HomeActivity;
import com.ajdi.yassin.newsreader.ui.pager.ArticlesFilterType;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ArticlesFragment extends Fragment {

    private static final String ARG_FILTER_TYPE = "ARG_FILTER_TYPE";
    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private ArticlesViewModel mViewModel;
    private FragmentArticlesBinding mBinding;
    private ArticlesFilterType mFilterType;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public ArticlesFragment() {
        // Required empty public constructor
    }

    public static ArticlesFragment newInstance(ArticlesFilterType filterType) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_FILTER_TYPE, filterType);
        ArticlesFragment fragment = new ArticlesFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFilterType = (ArticlesFilterType) getArguments().getSerializable(ARG_FILTER_TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentArticlesBinding.inflate(inflater, container, false);
        mViewModel = HomeActivity.obtainArticleListViewModel(getActivity(), mViewModelFactory);
        setupListAdapter();
        setupSnackbar();
        setupSwipeRefresh();
        return mBinding.getRoot();
    }

    private void setupSwipeRefresh() {
        mSwipeRefreshLayout = mBinding.partialArticleList.refreshLayout;
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
    }

    private void setupSnackbar() {
        mViewModel.getSnackbarMessageEvent().observe(getViewLifecycleOwner(), messageEvent -> {
            Integer message = messageEvent.getContentIfNotHandled();
            if (message != null) {
                Snackbar.make(mBinding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void setupListAdapter() {
        RecyclerView recyclerView = mBinding.partialArticleList.recyclerArticleList;
        ArticlesAdapter adapter = new ArticlesAdapter(mViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        // observe result LiveData and update UI based on the changes
        mViewModel.getResultFeeds(mFilterType).observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status) {
                case LOADING: {
                    mBinding.partialArticleList.refreshLayout.setVisibility(View.VISIBLE);
                    mBinding.networkState.errorLayout.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setRefreshing(true);
                    break;
                }
                case SUCCESS: {
                    mSwipeRefreshLayout.setRefreshing(false);
                    mBinding.networkState.errorLayout.setVisibility(View.GONE);
                    adapter.submitList(resource.data);
                    break;
                }
                case ERROR: {
                    mSwipeRefreshLayout.setRefreshing(false);
                    mBinding.partialArticleList.refreshLayout.setVisibility(View.GONE);
                    mBinding.networkState.errorLayout.setVisibility(View.VISIBLE);
                    mBinding.networkState.errorMsg.setText(resource.message);
                    break;
                }
            }
        });
    }
}

package com.ajdi.yassin.newsreader.ui.articleslist;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajdi.yassin.newsreader.databinding.FragmentArticlesBinding;
import com.ajdi.yassin.newsreader.ui.HomeActivity;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ArticlesFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private ArticlesViewModel mViewModel;
    private FragmentArticlesBinding mBinding;

    public ArticlesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentArticlesBinding.inflate(inflater, container, false);
        mViewModel = HomeActivity.obtainArticleListViewModel(getActivity(), mViewModelFactory);
        setupListAdapter();
        return mBinding.getRoot();
    }

    private void setupListAdapter() {
        RecyclerView recyclerView = mBinding.recyclerArticleList;
        ArticlesAdapter adapter = new ArticlesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        // observe result LiveData and update UI based on the changes
        mViewModel.getResult().observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status) {
                case LOADING: {
                    break;
                }
                case SUCCESS: {
                    adapter.submitList(resource.data);
                    break;
                }
                case ERROR: {
                    // TODO: 6/5/2019 show errors message
                    break;
                }
            }
        });
    }
}

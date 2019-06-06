package com.ajdi.yassin.newsreader.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajdi.yassin.newsreader.databinding.FragmentFavoritesBinding;
import com.ajdi.yassin.newsreader.ui.HomeActivity;
import com.ajdi.yassin.newsreader.ui.articleslist.ArticlesAdapter;
import com.ajdi.yassin.newsreader.ui.articleslist.ArticlesViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private ArticlesViewModel mViewModel;
    private FragmentFavoritesBinding mBinding;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentFavoritesBinding.inflate(inflater, container, false);
        mViewModel = HomeActivity.obtainArticleListViewModel(getActivity(), mViewModelFactory);
        setupListAdapter();
        setupSnackbar();
        return mBinding.getRoot();
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
        // observe favorites list
        mViewModel.getFavoritesListLiveData().observe(getViewLifecycleOwner(), feeds -> {
            if (!feeds.isEmpty()) {
                adapter.submitList(feeds);
            } else {
                // TODO: 6/5/2019 display empty state
                Timber.d("Favorites is empty");
            }
        });
    }

}

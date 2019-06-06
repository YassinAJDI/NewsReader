package com.ajdi.yassin.newsreader.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.ajdi.yassin.newsreader.R;
import com.ajdi.yassin.newsreader.ui.articleslist.ArticlesViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private ArticlesViewModel mViewModel;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        mViewModel = HomeActivity.obtainArticleListViewModel(this, mViewModelFactory);
        setContentView(R.layout.activity_home);
        setUpBottomNav();
        // observe open article details event
        mViewModel.getOpenFeedDetailEvent().observe(this, idEvent -> {
            String articleId = idEvent.getContentIfNotHandled();
            if (articleId != null) {
                Bundle bundle = new Bundle();
                bundle.putString("article_id", articleId);
                navController.navigate(R.id.action_articles_pager_dest_to_article_details_dest, bundle);
            }
        });
    }

    private void setUpBottomNav() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNav, navController);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.article_details_dest) {
                bottomNav.setVisibility(View.GONE);
            } else {
                bottomNav.setVisibility(View.VISIBLE);
            }
        });
    }

    public static ArticlesViewModel obtainArticleListViewModel(FragmentActivity activity,
                                                               ViewModelProvider.Factory factory) {
        return ViewModelProviders.of(activity, factory).get(ArticlesViewModel.class);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}

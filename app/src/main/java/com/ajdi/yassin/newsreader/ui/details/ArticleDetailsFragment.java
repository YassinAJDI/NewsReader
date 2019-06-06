package com.ajdi.yassin.newsreader.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ajdi.yassin.newsreader.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleDetailsFragment extends Fragment {

    public ArticleDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_details, container, false);
    }

}

package com.ashchuk.bakingapp.ui.fragments;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Step;

public class RecipeDetailFragment extends MvpAppCompatFragment {
    public static final String ARG_ITEM_ID = "item_id";

    private Step step;

    public RecipeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            getArguments().getInt(ARG_ITEM_ID);
            step = getArguments().getParcelable("step");

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(step.getDescription());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_detail, container, false);
        if (step != null) {
            ((TextView) rootView.findViewById(R.id.recipe_detail)).setText(step.getDescription());
        }

        return rootView;
    }
}

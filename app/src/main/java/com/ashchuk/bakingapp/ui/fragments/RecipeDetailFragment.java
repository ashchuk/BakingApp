package com.ashchuk.bakingapp.ui.fragments;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Ingredient;
import com.ashchuk.bakingapp.mvp.models.Step;
import com.ashchuk.bakingapp.ui.adapters.IngredientsAdapter;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.List;

public class RecipeDetailFragment extends MvpAppCompatFragment {

    private Step step;
    private List<Ingredient> ingredients;
    private SimpleExoPlayer mExoPlayer;

    private RecyclerView ingredientsView;

    public RecipeDetailFragment() {
    }

    private MediaSource buildMediaSource(
            Uri uri) {
        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer-bakingapp"))
                .createMediaSource(uri, null, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey("step")) {
            step = getArguments().getParcelable("step");
        }
        if (getArguments().containsKey("ingredients")) {
            ingredients = getArguments().getParcelableArrayList("ingredients");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_detail, container, false);
        if (step != null) {
            ((TextView) rootView.findViewById(R.id.recipe_detail)).setText(step.getDescription());
        }

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Activity activity = this.getActivity();

        PlayerView playerView = activity.findViewById(R.id.video_view);
        ingredientsView = activity.findViewById(R.id.rvIngredients);

        ingredientsView.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        ingredientsView.setAdapter(new IngredientsAdapter(ingredients));

        mExoPlayer = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(mExoPlayer);

        mExoPlayer.setPlayWhenReady(true);
        mExoPlayer.seekTo(0, 0);

        String videoUrl = step.getVideoURL();

        mExoPlayer.prepare(buildMediaSource(Uri.parse(videoUrl)), true, false);
    }
}

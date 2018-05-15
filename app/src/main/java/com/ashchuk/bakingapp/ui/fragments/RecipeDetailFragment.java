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
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ashchuk.bakingapp.tools.Constants.INGREDIENTS_KEY;
import static com.ashchuk.bakingapp.tools.Constants.KEY_AUTO_PLAY;
import static com.ashchuk.bakingapp.tools.Constants.KEY_POSITION;
import static com.ashchuk.bakingapp.tools.Constants.KEY_WINDOW;
import static com.ashchuk.bakingapp.tools.Constants.STEP_KEY;

public class RecipeDetailFragment extends MvpAppCompatFragment {

    private Step step;
    private List<Ingredient> ingredients;
    private SimpleExoPlayer mExoPlayer;

    private final String USER_AGENT = "exoplayer-bakingapp";
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();

    private static boolean shouldAutoPlay = true;
    private int startWindow = 0;
    private long startPosition = 0;

    private DefaultTrackSelector trackSelector;
    private DefaultTrackSelector.Parameters trackSelectorParameters;

    @BindView(R.id.video_view)
    PlayerView playerView;

    @BindView(R.id.rvIngredients)
    RecyclerView ingredientsView;

    public RecipeDetailFragment() {
    }

    private MediaSource buildMediaSource(
            Uri uri) {
        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory(USER_AGENT))
                .createMediaSource(uri, null, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        if (getArguments().containsKey(STEP_KEY)) {
            step = getArguments().getParcelable(STEP_KEY);
        }
        if (getArguments().containsKey(INGREDIENTS_KEY)) {
            ingredients = getArguments().getParcelableArrayList(INGREDIENTS_KEY);
        }

        trackSelectorParameters = new DefaultTrackSelector.ParametersBuilder().build();

        if (savedInstanceState != null) {
            shouldAutoPlay = savedInstanceState.getBoolean(KEY_AUTO_PLAY);
            startWindow = savedInstanceState.getInt(KEY_WINDOW);
            startPosition = savedInstanceState.getLong(KEY_POSITION);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            updateTrackSelectorParameters();
            updateStartPosition();
            mExoPlayer.release();
            mExoPlayer = null;
            trackSelector = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        updateTrackSelectorParameters();
        updateStartPosition();

        outState.putLong(KEY_POSITION, startPosition);
        outState.putBoolean(KEY_AUTO_PLAY, shouldAutoPlay);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            startPosition = savedInstanceState.getLong(KEY_POSITION);
            shouldAutoPlay = savedInstanceState.getBoolean(KEY_AUTO_PLAY);
        }
    }

    private void updateTrackSelectorParameters() {
        if (trackSelector != null) {
            trackSelectorParameters = trackSelector.getParameters();
        }
    }

    private void updateStartPosition() {
        if (mExoPlayer != null) {
            shouldAutoPlay = mExoPlayer.getPlayWhenReady();
            startWindow = mExoPlayer.getCurrentWindowIndex();
            startPosition = Math.max(0, mExoPlayer.getContentPosition());
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

        if (savedInstanceState == null)
            shouldAutoPlay = true;

        Activity activity = this.getActivity();

        ButterKnife.bind(this, view);
        AdaptiveTrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
        trackSelector = new DefaultTrackSelector(trackSelectionFactory);
        trackSelector.setParameters(trackSelectorParameters);

        ingredientsView.setLayoutManager(new LinearLayoutManager(Objects.requireNonNull(activity).getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        ingredientsView.setAdapter(new IngredientsAdapter(ingredients));

        mExoPlayer = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                trackSelector, new DefaultLoadControl());

        playerView.setPlayer(mExoPlayer);
        String videoUrl = step.getVideoURL();
        mExoPlayer.prepare(buildMediaSource(Uri.parse(videoUrl)), true, false);

        mExoPlayer.setPlayWhenReady(shouldAutoPlay);
        mExoPlayer.seekTo(startWindow, startPosition);
    }
}

package com.ashchuk.bakingapp.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ashchuk.bakingapp.R;

public class RecipeStepsViewHolder extends RecyclerView.ViewHolder {
    public TextView mIdView;
    public TextView mContentView;

    public RecipeStepsViewHolder(View view) {
        super(view);
        mIdView = view.findViewById(R.id.id_text);
        mContentView = view.findViewById(R.id.content);
    }
}
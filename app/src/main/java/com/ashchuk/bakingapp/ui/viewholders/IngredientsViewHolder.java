package com.ashchuk.bakingapp.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ashchuk.bakingapp.R;

public class IngredientsViewHolder extends RecyclerView.ViewHolder {
    public TextView mIdView;

    public IngredientsViewHolder(View view) {
        super(view);
        mIdView = view.findViewById(R.id.id_text);
    }
}
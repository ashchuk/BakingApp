package com.ashchuk.bakingapp.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ashchuk.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.id_text)
    public TextView mIdView;

    public StepsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
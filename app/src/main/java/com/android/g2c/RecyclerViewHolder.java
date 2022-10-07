package com.android.g2c;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder  {

    public TextView itemTxt;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        itemTxt=(TextView) itemView.findViewById(R.id.itemTxt);
    }
}
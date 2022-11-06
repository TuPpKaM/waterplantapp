package com.example.waterplant;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ZeroMarginDecoration extends RecyclerView.ItemDecoration {
    private final static int MARGIN_IN_DP = 0;

    public ZeroMarginDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0,0,0,0);
        view.setPadding(0, 0, 0, 0);
    }


}

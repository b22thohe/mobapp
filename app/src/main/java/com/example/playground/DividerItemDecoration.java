package com.example.playground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable itemDivider;

    // Constructor
    public DividerItemDecoration(Context context, int resId) {
        itemDivider = ContextCompat.getDrawable(context, resId);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            RecyclerView.ViewHolder child = parent.getChildViewHolder(parent.getChildAt(i));

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.itemView.getLayoutParams();

            int top = child.itemView.getBottom() + params.bottomMargin;
            int bottom = top + itemDivider.getIntrinsicHeight();

            itemDivider.setBounds(left, top, right, bottom);
            itemDivider.draw(c);
        }
    }
}

package com.example.waterplant;

import 	android.content.res.Resources;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

//Import for basics
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;
import android.view.LayoutInflater;
//----------

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private String[] localDataSet;
    private int screenHeightPx;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public MyRecyclerViewAdapter(String[] dataSet, int screenHeightPx) {
        localDataSet = dataSet;
        this.screenHeightPx = screenHeightPx;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item, viewGroup, false);

        //setting item height
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (screenHeightPx/4);
        view.setLayoutParams(layoutParams);


        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }


}


package com.example.waterplant;

import 	android.content.res.Resources;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;

//Import for basics
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Chronometer;
import android.widget.TextView;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//----------

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private String[] localDataSet;
    private int screenHeightPx;
    private DateFormat date;
    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final Chronometer chronometer;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            chronometer = (Chronometer) view.findViewById(R.id.Chronometer);
            textView = (TextView) view.findViewById(R.id.textView);
        }
        public Chronometer getChronometer() {
            return chronometer;
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
    public MyRecyclerViewAdapter(String[] dataSet, int screenHeightPx, Context context) {
        localDataSet = dataSet;
        this.screenHeightPx = screenHeightPx;
        this.context = context;
        date = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
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
        Date datet = null;
        try {
            datet = date.parse(localDataSet[position]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long lastSuccess = datet.getTime(); //Some Date object
        long elapsedRealtimeOffset = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        viewHolder.getChronometer().setBase(lastSuccess - elapsedRealtimeOffset);
        viewHolder.getChronometer().start();

        //onclick for timer
        viewHolder.getChronometer().setOnChronometerTickListener(cArg -> {
            long time = SystemClock.elapsedRealtime() - cArg.getBase();

            int d = (int) (time / 86400000);
            if (d == 1) {
                cArg.setText(d + " day");
                return;
            } else if (d > 1) {
                cArg.setText(d + " days");
                return;
            }

            int h = (int) ((time / 3600000) % 24);
            if (h == 1) {
                cArg.setText(h + " hour");
                return;
            } else if (h > 1) {
                cArg.setText(h + " hours");
                return;
            }

            int m = (int) ((time / 60000) % 60);
            if (m >= 1) {
                cArg.setText(m + " min");
                return;
            }

            int s = (int) ((time / 1000) % 60);
            cArg.setText(s + " sec");
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }



}


package com.example.waterplant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private final String[] savedDataSet;
    private String[] localDataSet;
    private final int screenHeightPx;
    private final DateFormat date;
    private final Context context;
    private final SharedPreferences sharedPref;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Chronometer chronometer;
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            chronometer = (Chronometer) view.findViewById(R.id.Chronometer);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
        public Chronometer getChronometer() {
            return chronometer;
        }
        public ImageView getImageView() {
            return imageView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public MyRecyclerViewAdapter(String[] dataSet, int screenHeightPx, Context context, SharedPreferences sharedPref) {
        localDataSet = dataSet;
        savedDataSet = Arrays.copyOf(dataSet, dataSet.length);
        this.screenHeightPx = screenHeightPx;
        this.context = context;
        this.sharedPref = sharedPref;
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

        //calculate time
        Date savedDate = null;
        try {
            savedDate = date.parse(savedDataSet[position]);
        } catch (Exception e) {
            savedDate = Calendar.getInstance().getTime();
        }

        long lastPress = savedDate.getTime();
        long elapsedRealtimeOffset = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        long chronoBase = lastPress - elapsedRealtimeOffset;

        //TODO: setup images

        //setup Chronometer
        viewHolder.getChronometer().setBase(chronoBase);
        viewHolder.getChronometer().start();

        //onclick for item short
        viewHolder.getImageView().setOnClickListener(v -> {
            viewHolder.getChronometer().setBase(SystemClock.elapsedRealtime());
            localDataSet[position]= date.format(Calendar.getInstance().getTime());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("plant_date_"+position, localDataSet[position]);
            editor.commit();
        });

        //onclick for item long
        viewHolder.getImageView().setLongClickable(true);
        viewHolder.getImageView().setOnLongClickListener(v -> {
            viewHolder.getChronometer().setBase(chronoBase);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("plant_date_"+position, savedDataSet[position]);
            editor.commit();
            return true;
        });

        //onclick for timer
        viewHolder.getChronometer().setOnChronometerTickListener(cArg -> {
            long time = SystemClock.elapsedRealtime() - cArg.getBase();

            int d = (int) (time / 86400000);
            if (d > 1) {
                cArg.setText(d + " days");
                return;
            } else if (d == 1) {
                cArg.setText(d + " day");
                return;
            }

            int h = (int) ((time / 3600000) % 24);
            if (h > 1) {
                cArg.setText(h + " hours");
                return;
            } else if (h == 1) {
                cArg.setText(h + " hour");
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
        return savedDataSet.length;
    }



}


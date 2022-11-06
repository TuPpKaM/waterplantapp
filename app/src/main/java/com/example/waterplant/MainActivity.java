package com.example.waterplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

//Import for basics
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Window;
import android.widget.TextView;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Toast;
//----------

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //screen calculations
        int resource = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        int navigationBarHeight = getResources().getDimensionPixelSize(resource);
        Configuration configuration = getResources().getConfiguration();
        int screenWidthDp = configuration.screenWidthDp;
        int screenHeightDp = configuration.screenHeightDp;
        float factor = getResources().getDisplayMetrics().density;
        int screenHeightPx = (int) ((screenHeightDp)*factor)-(navigationBarHeight);

        //recyclerview setup
        MyRecyclerViewAdapter menuRecAdapter = new MyRecyclerViewAdapter(new String[]{"1", "2", "3", "4", "5", "6", "7","8"},screenHeightPx);
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.addItemDecoration(new ZeroMarginDecoration());
        recyclerView.setAdapter(menuRecAdapter);

        //debug
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, " W "+screenWidthDp + " H " + screenHeightDp + " S " + navigationBarHeight + " R " + resource, Toast.LENGTH_LONG);
        toast.show();



    }
}
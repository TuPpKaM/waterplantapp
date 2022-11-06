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

        Context context = getApplicationContext();

        //recyclerview setup
        MyRecyclerViewAdapter menuRecAdapter = new MyRecyclerViewAdapter(new String[]{"6/11/2022 23:50:05", "6/11/2022 23:40:05", "6/11/2022 23:14:05", "6/11/2022 23:10:05", "6/11/2022 23:22:05", "5/11/2022 23:17:05", "5/11/2022 23:10:05","4/11/2022 23:10:05"},screenHeightPx, context);
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setAdapter(menuRecAdapter);

        //debug

        Toast toast = Toast.makeText(context, " W "+screenWidthDp + " H " + screenHeightDp + " S " + navigationBarHeight + " R " + resource, Toast.LENGTH_LONG);
        toast.show();



    }
}
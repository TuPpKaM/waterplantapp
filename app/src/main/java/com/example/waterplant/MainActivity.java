package com.example.waterplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

//Import for basics
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
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

        Configuration configuration = getResources().getConfiguration();
        int screenWidthDp = configuration.screenWidthDp;
        int screenHeightDp = configuration.screenHeightDp;
        float factor = getResources().getDisplayMetrics().density;
        int screenHeightPx = (int) (screenHeightDp*factor);

        MyRecyclerViewAdapter menuRecAdapter = new MyRecyclerViewAdapter(new String[]{"1", "2", "3", "4", "5", "6", "7","8"},screenHeightPx);
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setAdapter(menuRecAdapter);

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, " W "+screenWidthDp + " H " + screenHeightDp, Toast.LENGTH_LONG);
        toast.show();



    }
}
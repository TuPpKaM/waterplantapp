package com.example.waterplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

//Import for basics
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.Calendar;
//----------

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //screen calculations
        Configuration configuration = getResources().getConfiguration();
        int screenWidthDp = configuration.screenWidthDp;
        int screenHeightDp = configuration.screenHeightDp;
        float factor = getResources().getDisplayMetrics().density;
        int screenHeightPx = (int) ((screenHeightDp-56)*factor);

        Context context = getApplicationContext();

        //load data
        SharedPreferences sharedPref = getSharedPreferences("plant_date_1", MODE_PRIVATE);
        String[] inputData = new String[8];
        for (int i =0; i<8 ; i++) {
            inputData[i] = sharedPref.getString("plant_date_"+i, null);
        }

        //TODO: add image storage

        /*
        String[] listan = new String[]{"6/11/2022 23:50:05", "6/11/2022 23:40:05", "6/11/2022 23:14:05", "6/11/2022 23:10:05", "6/11/2022 23:22:05", "5/11/2022 23:17:05", "5/11/2022 23:10:05","4/11/2022 23:10:05"};
        SharedPreferences.Editor editor = sharedPref.edit();
        for (int i =0; i<8 ; i++) {
            editor.putString("plant_date_"+i,listan[i]);
        }
        editor.commit();
        */


        //recyclerview setup
        MyRecyclerViewAdapter menuRecAdapter = new MyRecyclerViewAdapter(inputData,screenHeightPx, context, sharedPref);
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setAdapter(menuRecAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        //debug

        Toast toast = Toast.makeText(context, " W "+screenWidthDp + " H " + screenHeightDp + " S " + "F"+ factor, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    //TODO: find a way to make menu item clickable, decide on what menu to use
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.editButton:
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, " W ", Toast.LENGTH_LONG);
                toast.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
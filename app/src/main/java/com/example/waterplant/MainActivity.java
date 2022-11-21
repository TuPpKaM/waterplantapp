package com.example.waterplant;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.AdapterView;
import android.widget.Toast;
//----------

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //screen calculations
        Configuration configuration = getResources().getConfiguration();
        int screenWidthDp = configuration.screenWidthDp;  //unused
        int screenHeightDp = configuration.screenHeightDp;
        float factor = getResources().getDisplayMetrics().density;
        int screenHeightPx = (int) ((screenHeightDp-56)*factor);

       //setup timer backgrounds
        String[] backgroundColors = new String[8];
        for (int pos = 0; pos<backgroundColors.length; pos++) {
            Bitmap bitmap = ((BitmapDrawable) ResourcesCompat.getDrawable(getResources(),getResources().getIdentifier("plant"+(pos+1), "drawable", getPackageName()),getTheme())).getBitmap();
            Palette p = Palette.from(bitmap).generate();
            Palette.Swatch s = p.getDarkVibrantSwatch();
            backgroundColors[pos] = "#A6000000"; //default if swatch is missing
            //Todo take another swatch if first is null
            if (s != null) {
                backgroundColors[pos] = "#A6" + Integer.toHexString(s.getRgb()).substring(2);
            }
        }

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

        Context context = this;

        //recyclerview setup
        MyRecyclerViewAdapter menuRecAdapter = new MyRecyclerViewAdapter(inputData,screenHeightPx, context, sharedPref, backgroundColors);
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setAdapter(menuRecAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        //debug

        //Toast toast = Toast.makeText(context, " W "+screenWidthDp + " H " + screenHeightDp + " S " + "F"+ factor, Toast.LENGTH_LONG);
        //toast.show();
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
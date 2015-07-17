package com.teamtreehouse.stormy.UI;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.teamtreehouse.stormy.R;
import com.teamtreehouse.stormy.adapters.DayAdapter;
import com.teamtreehouse.stormy.weather.Day;

public class DailyForecastActivity extends ListActivity { //change 'ActionBarActivity' to 'ListActivity'

    private Day[] mDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

       // Deleted default adapter
       // Create a new DayAdapter
        DayAdapter adapter = new DayAdapter(this, mDays);

    }
}

package com.teamtreehouse.stormy.UI;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.teamtreehouse.stormy.R;

public class DailyForecastActivity extends ListActivity { //change 'ActionBarActivity' to 'ListActivity'

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        // So our real data is going to be an array of dat objects with details about the weather
        // but it takes a little more work so lets start with a mockup with an array of string where each item is simply a day of the week
        // This will illustrate how the main components of a ListView work.
        // And then we can move onto more advanced uses.
        String[] daysOfTheWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

    }



}

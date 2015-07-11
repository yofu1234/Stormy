package com.teamtreehouse.stormy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private CurrentWeather mCurrentWeather;

    @InjectView(R.id.timeLabel) TextView mTimeLabel; //doing it with butterknife annotation
    @InjectView(R.id.temperatureLabel) TextView mTemperatureLabel;
    @InjectView(R.id.humidityValue) TextView mHumidityValue;
    @InjectView(R.id.precipValue) TextView mPrecipValue;
    @InjectView(R.id.summaryLabel) TextView mSummaryLabel;
    @InjectView(R.id.iconImageView) ImageView mIconImageView;
    @InjectView(R.id.refreshImageView) ImageView mRefreshImageView;
    @InjectView(R.id.progressBar) ProgressBar mProgressBar;
    //create new method to check for network connectivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this); //butterknife

        mProgressBar.setVisibility(View.INVISIBLE); // We can hide the loading progress bar here or in the xml. The tutorial guy just decided to do it here. *

        final double latitude = 37.8267; //cut and paste this two lines from below to up here so we can use them in method calls
        final double longitude = -122.423;

        mRefreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We want to get a forecast just like we are in the OnCreate method.
                // Let's copy and paste everything to, no, I'm just kidding.
                // Let's refactor this code into a new method that we can use here and
                // on Create, and in the on click method.
                // Highlight everything from the API key to the bottom of the if else block> Right click on the highlighted>Extract>method > call it
                getForecast(latitude, longitude);

                // If we we want to allow for different locations, we might want to provide latitude and longitude.
            }
        });

        getForecast(latitude, longitude);

        Log.d(TAG, "Main UI code is running!");
    }

    //refactored and created the get Forecast method:
    private void getForecast(double latitude, double longitude) { //We'll pass in the double latitude and the double longitude.
        String apiKey = "d5faf0cb201f103df4dde0b8b0a4f490";
        String forecastUrl = "https://api.forecast.io/forecast/" + apiKey +
                "/" + latitude + "," + longitude;

        if (isNetworkAvailable()) {
            toggleRefresh();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(forecastUrl).build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    //This is updating the main UI so we need to 'runOnUiThread' not a background thread. so surround 'toggleRefresh();' with runOnUiThread(new Runnable()){      }
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            toggleRefresh();
                        }
                    });
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Response response) throws IOException {

                    //This is updating the UI so we need to 'runOnUiThread' not a background thread. so surround 'toggleRefresh();' with runOnUiThread(new Runnable()){      }
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            toggleRefresh();
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mCurrentWeather = getCurrentDetails(jsonData);

                            //can't do it in background thread because only the main thread is allowed to update the UI like we stated before
                            // What we need to do is send a message to the main UI thread that we have code ready for it.
                            // We can do this a few different ways, but
                            // we'll use a simple one using a special helper method called runOnUIThread.
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay(); //create  a new method for update display instead of putting it here so code isn't messy
                                }
                            });
                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
            else {
                Toast.makeText(this, getString(R.string.network_unavailable_message), Toast.LENGTH_LONG).show();
        }
    }

    private void toggleRefresh() {
        // hide things if they are showing and show things if they are invisible
        if(mProgressBar.getVisibility() == View.INVISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE); //make progress bar visible when refreshing *
            mRefreshImageView.setVisibility(View.INVISIBLE); //make the refresh image invisible when normal *
        }
        else { //otherwise:
            mProgressBar.setVisibility(View.INVISIBLE); //make progress bar invisible *
            mRefreshImageView.setVisibility(View.VISIBLE); //make the refresh image visible *
        }
    }

    private void updateDisplay() {
        mTemperatureLabel.setText(mCurrentWeather.getTemperature() + ""); // the temperature u get from .getTemperature is a 'Double' value thus you have to convert it to a string via + "" . simple.
        mTimeLabel.setText("At " + mCurrentWeather.getFormattedTime() + " it will be");
        mHumidityValue.setText(mCurrentWeather.getHumidity() + "");
        mPrecipValue.setText(mCurrentWeather.getPrecipChance() + "%");
        mSummaryLabel.setText(mCurrentWeather.getSummary());

        Drawable drawable = getResources().getDrawable(mCurrentWeather.getIconId());
        mIconImageView.setImageDrawable(drawable);
        //pretty simple stuff^
    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {

        JSONObject forecast = new JSONObject(jsonData);

        String timezone = forecast.getString("timezone");
            Log.i(TAG, "From JSON: " + timezone);

        JSONObject currently = forecast.getJSONObject("currently");
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setTimeZone(timezone);

        Log.d(TAG, currentWeather.getFormattedTime());

        return currentWeather;

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }
}

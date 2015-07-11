package com.teamtreehouse.stormy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CurrentWeather {

    private String mIcon; // The Icon // it comes from Forecast API as a string, we convert it to an int
    private long mTime; //Time
    private double mTemperature; //Temp
    private double mHumidity; //humidity
    private double mPrecipChance; //chance of precipitation
    private String mSummary; //summary at the bottom

    private String mTimeZone;

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    // Step 2: Generate getters and setters for everything: Code>Generate...>Getters and Setters:
    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public String getIcon() {
        return mIcon;
    }
    // now we want to add a new method to our model that gets the appropriate image
    // based on the icon value we are getting from the forecast API.
    // To get the image, we need to int ID that gets generated for each drawable resource.

    public int getIconId(){
        // clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
        int iconId = R.drawable.clear_day; //set this one in default, forecast api documentation says to "set a sensible icon as default"

        if(mIcon.equals("clear-day")){
            iconId = R.drawable.clear_day; //notice that the ids have _ compared to string "-" because we arent allowed to have dashes as resource names in Android Studio
        }
        else if (mIcon.equals("clear-night")){
            iconId = R.drawable.clear_night;
        }
        else if (mIcon.equals("rain")) {
            iconId = R.drawable.rain;
        }
        else if (mIcon.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (mIcon.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (mIcon.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (mIcon.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (mIcon.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (mIcon.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (mIcon.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }

        return iconId;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getPrecipChance() {
        double precipPercentage = mPrecipChance * 100; //like what we did with mTemperature
        return (int)Math.round(precipPercentage);
    }

    public void setPrecipChance(double precipChance) {
        mPrecipChance = precipChance;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public int getTemperature() {
        return (int)Math.round(mTemperature); //return rounded decimal places of temperature that is cast to an int
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public long getTime() {
        return mTime;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm, a"); // this special Java called SimpleDateFormat converts unix date format to locale "normal" format
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone())); //set time zone, logging it from MainActivity

        Date dateTime = new Date(getTime() * 1000);
        String timeString = formatter.format(dateTime);

        return timeString;
    }

    public void setTime(long time) {
        mTime = time;
    }

}

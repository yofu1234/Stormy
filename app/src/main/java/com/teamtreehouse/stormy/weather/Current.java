package com.teamtreehouse.stormy.weather;

import com.teamtreehouse.stormy.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Current {

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

        return Forecast.getIconId(mIcon); //return method called "getIconId" in the Forecast(.java) class (pass along Icon)
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

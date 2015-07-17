package com.teamtreehouse.stormy.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Day {
    private long mTime;
    private String mSummary;
    private double mTemperatureMax; //max temp of the day
    private String mIcon;
    private String mTimeZone;


    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public int getTemperatureMax() { //convert from "double" to an "int"
        return (int) Math.round(mTemperatureMax);
    }

    public void setTemperatureMax(double temperatureMax) {
        mTemperatureMax = temperatureMax;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getTimezone() {
        return mTimeZone;
    }

    public void setTimezone(String timeZone) {
        mTimeZone = timeZone;
    }

    public int getIconId(){
        return Forecast.getIconId(mIcon);
    }

    //Create Day Of The Week method
    public String getDayOfTheWeek(){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE"); //use SimpleDateFormat just like before in h:mm, a //And the format we want to use here for the Day value from the long timestamp is just four capital Es, EEEE.
        formatter.setTimeZone(TimeZone.getTimeZone(mTimeZone)); //this is why we created the TimeZone class. Get the Timezone method from that class and pass in the String mTimeZone --> So this takes the string that we get from the Forecast API and its a standard format that can be converted into a timezone object to use here.
        Date dateTime = new Date(mTime * 1000); //Date constructor expects number in seconds but mTime outputs in miliseconds so * 1000 to convert it.
        return formatter.format(dateTime);


    }

}






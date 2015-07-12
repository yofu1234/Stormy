package com.teamtreehouse.stormy.weather;


public class Hour {
    private long mTime; // use 'long' to hold the Time //use private to keep it in the class
    private String mSummary; //use 'String' to hold the summary, etc. simple.
    private double mTemperature;
    private String mIcon; // 'String' to hold the icon
    private String mTimezone; //need the timezone to display the hour properly

    // Step 2. code>generate>getters and setters> done. simple

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

    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }
}

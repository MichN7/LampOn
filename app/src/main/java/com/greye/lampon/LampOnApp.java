package com.greye.lampon;


import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;


public class LampOnApp extends Application {

    private static final String TWITTER_KEY = "5jT3g59qDkKNQ2gIWsE51dYDR";
    private static final String TWITTER_SECRET = "05VFrbmFOlcewPViIxlFIycfuCLGJUS7e5BN0dAmCP0GZ7jfAN";

    @Override
    public void onCreate(){
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}

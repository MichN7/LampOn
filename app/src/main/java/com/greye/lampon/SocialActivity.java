package com.greye.lampon;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.identity.*;


import io.fabric.sdk.android.Fabric;

public class SocialActivity extends Activity{

    //Facebook
    private LoginButton loginButtonFace;
    private CallbackManager callbackManager;

    //Twitter
    private static final String TWITTER_KEY = "5jT3g59qDkKNQ2gIWsE51dYDR";
    private static final String TWITTER_SECRET = "05VFrbmFOlcewPViIxlFIycfuCLGJUS7e5BN0dAmCP0GZ7jfAN";
    private TwitterLoginButton loginButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        //Twitter
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                String msg = "Se ha conectado correctamente a Twitter! ";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                goMainScreen();
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });


        //Facebook
        callbackManager = CallbackManager.Factory.create();

        loginButtonFace = (LoginButton) findViewById(R.id.loginButton);
        loginButtonFace.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String msg = "Se ha conectado correctamente a Facebook! ";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                goMainScreen();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void goMainScreen(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        //Twitter
        loginButton.onActivityResult(requestCode,resultCode,data);
        //Facebook
        callbackManager.onActivityResult(requestCode,resultCode,data);

    }
}

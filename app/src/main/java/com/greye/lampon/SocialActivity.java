package com.greye.lampon;


import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;



import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.identity.*;


import org.json.JSONException;
import org.json.JSONObject;

import io.fabric.sdk.android.Fabric;



public class SocialActivity extends Activity{

    //Extra
    final Context context = this;


    //Facebook
    private LoginButton loginButtonFace;
    private CallbackManager callbackManager;
    TextView details_txt;

    ImageView imageView;

    //Twitter
    private static final String TWITTER_KEY = "5jT3g59qDkKNQ2gIWsE51dYDR";
    private static final String TWITTER_SECRET = "05VFrbmFOlcewPViIxlFIycfuCLGJUS7e5BN0dAmCP0GZ7jfAN";
    private TwitterLoginButton loginButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        details_txt = (TextView)findViewById(R.id.nombre);
        setUpTwitterButton();
        setUpFacebookButton();
        imageView = (ImageView)findViewById(R.id.imageView5);



    }

    private void setUpFacebookButton(){
        //Facebook
        callbackManager = CallbackManager.Factory.create();

        loginButtonFace = (LoginButton) findViewById(R.id.loginButton);
        loginButtonFace.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                GraphRequest request = GraphRequest.newMeRequest(accessToken,
                        new GraphRequest.GraphJSONObjectCallback(){
                           public void onCompleted(JSONObject object, GraphResponse response){
                               try {
                                   String userID = (String) object.get("id");
                                   String userName = (String) object.get("name");
                                   ImageView image = new ImageView(getApplicationContext());
                                   Picasso.with(SocialActivity.this).load("https://graph.facebook.com/" + userID + "/picture?type=large").into(image);
                                   AlertDialog dialog = new AlertDialog.Builder(SocialActivity.this)
                                           .setView(image)
                                           .setMessage("                   Bienvenido/a \n" + "            "+ userName)
                                           .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialog, int which) {
                                                   dialog.dismiss();
                                               }
                                           }).create();
                                   dialog.show();
                               }catch(JSONException e){
                                   e.printStackTrace();
                               }
                           }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,birthday,picture");
                request.setParameters(parameters);
                request.executeAsync();
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
    } //Inicializa el boton de Facebook


    private void setUpTwitterButton(){
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
                    String msg = "Se ha conectado correctamente a Twitter! ";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    goMainScreen();

            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    } //Inicializa el boton de Twitter


    protected void goMainScreen(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    } //Regresa a la pantalla principal

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        //Facebook
        callbackManager.onActivityResult(requestCode,resultCode,data);

        //Twitter
        loginButton.onActivityResult(requestCode,resultCode,data);
    }
}

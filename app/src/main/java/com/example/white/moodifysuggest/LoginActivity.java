package com.example.white.moodifysuggest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;

/**
 * Created by White on 3/7/2018.
 */

public class LoginActivity extends Activity {
   MoodifySuggest application;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.login_activity_main);
      
      application =((MoodifySuggest)this.getApplication());
      
      application.create();
   
      Intent newIntent = new Intent(LoginActivity.this, HomeActivity.class);
      startActivity(newIntent);
   }

}

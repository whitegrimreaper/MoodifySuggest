package com.example.white.moodifysuggest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;
import com.wrapper.spotify.SpotifyApi;

import org.apache.http.auth.AUTH;

public class MainActivity extends Activity {
   
   public static final String CLIENT_ID = "021703a983cd4ea0939366c49fa98d52";
   public static final String REDIRECT_URI = "moodify-481://callback";
   
   private static final int REQUEST_CODE = 1337;
   
   public MoodifySuggest application;
   public SpotifyApi data;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
   
      application = new MoodifySuggest();
      
   }
   
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
      super.onActivityResult(requestCode, resultCode, intent);
      
      if(requestCode == REQUEST_CODE) {
         final AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
         
         ((MoodifySuggest) getApplication()).setResponse(response);
         Intent newIntent = new Intent(MainActivity.this, LoginActivity.class);
         startActivity(newIntent);
      }
   
      
   }
   
   public void log_in(View view) {
      AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
              AuthenticationResponse.Type.TOKEN,
              REDIRECT_URI);
      builder.setScopes(new String[]{"user-read-private","streaming"});
      AuthenticationRequest request = builder.build();
   
      Log.d("log_in", "Opening login activity");
      AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
   }
   
   
}

package com.example.white.moodifysuggest;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;
//import com.wrapper.spotify.SpotifyApi;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by White on 3/7/2018.
 */

public class MoodifySuggest extends Application implements
        SpotifyPlayer.NotificationCallback, ConnectionStateCallback {
   SpotifyPlayer player;
   SpotifyApi api;
   SpotifyService spotify;
   public AuthenticationResponse responseFromCreator;
   
   public MoodifySuggest() {
   
   }
   
   public void setResponse(AuthenticationResponse response) {
      responseFromCreator = response;
      //Log.d("reeeeeeeeeeeeeeeeeeeeee", "response set");
   }
   public void setPlayer(SpotifyPlayer spotifyPlayer) {
      player = spotifyPlayer;
   }
   public void setApi(SpotifyApi spotifyApi) {
      //api = spotifyApi;
   }
   
   public void create() {
      if(responseFromCreator.getType() == AuthenticationResponse.Type.TOKEN) {
         Config playerConfig = new Config(this, responseFromCreator.getAccessToken(), MainActivity.CLIENT_ID);
         Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
            @Override
            public void onInitialized(SpotifyPlayer spotifyPlayer) {
               player = spotifyPlayer;
               player.addConnectionStateCallback(MoodifySuggest.this);
               player.addNotificationCallback(MoodifySuggest.this);
               Log.d("MainActivity", "Initialized Player");
            }
         
            @Override
            public void onError(Throwable throwable) {
               Log.e("MainActivity","Could not initialize player: " + throwable.getMessage());
            }
         });

         api = new SpotifyApi();
         api.setAccessToken(responseFromCreator.getAccessToken());
         Log.d("MoodifySuggest","Access token set to " + responseFromCreator.getAccessToken());

         RestAdapter restAdapter = new RestAdapter.Builder()
                 .setEndpoint(SpotifyApi.SPOTIFY_WEB_API_ENDPOINT)
                 .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                       request.addHeader("Authorization", "Bearer " + responseFromCreator.getAccessToken());
                    }
                 })
                 .build();

         spotify = restAdapter.create(SpotifyService.class);
         //spotify = api.getService();

         Log.d("MoodifySuggest", "API Built");
      }
   }
   
   
   // TODO see what I need to do with regards to destroying the player, else the application will leak the memories
//   @Override
//   protected void onDestroy() {
//      Spotify.destroyPlayer(this.player);
//
//      // TODO this needs to be fixed
//      //super.onDestroy();
//   }
   
   @Override
   public void onPlaybackEvent(PlayerEvent playerEvent) {
      Log.d("MainActivity", "Playback event received: " + playerEvent.name());
      switch (playerEvent) {
         
         // Handle event type as necessary
         default:
            break;
      }
   }
   
   @Override
   public void onPlaybackError(Error error) {
      Log.d("MainActivity", "Playback error received: " + error.name());
      switch (error) {
         // Handle error type as necessary
         default:
            break;
      }
   }
   
   @Override
   public void onLoggedIn() {
      Log.d("MainActivity", "User logged in");
      
      //mPlayer.playUri(null, "spotify:track:6qAAOu6mkt8T0GTS7FGr9a", 0, 0);
      
      //open main menu activity here
      // TODO make menu activity class lol
      //Intent newIntent = new Intent(MoodifySuggest.this, MenuActivity.class);
      //startActivity(newIntent);
   }
   
   @Override
   public void onLoggedOut() {
      Log.d("MainActivity", "User logged out");
   }
   
   @Override
   public void onLoginFailed(Error i) {
      Log.d("MainActivity", "Login failed");
   }
   
   @Override
   public void onTemporaryError() {
      Log.d("MainActivity", "Temporary error occurred");
   }
   
   @Override
   public void onConnectionMessage(String message) {
      Log.d("MainActivity", "Received connection message: " + message);
   }
}

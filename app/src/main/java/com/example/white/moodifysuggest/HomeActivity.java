package com.example.white.moodifysuggest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Playlist;
import kaaes.spotify.webapi.android.models.PlaylistBase;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by White on 3/7/2018.
 */

public class HomeActivity extends AppCompatActivity {

   MoodifySuggest application;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_home);

      application =((MoodifySuggest)this.getApplication());
      
      Log.d("eh", "it worked");
   }

   protected void happy_click(View view) {
      kaaes.spotify.webapi.android.SpotifyApi SAPI = new kaaes.spotify.webapi.android.SpotifyApi();
      SAPI.setAccessToken(application.responseFromCreator.getAccessToken());

      Log.d("HomeActivity", "Clicked Happy");
      Map<String, Object> map = new HashMap<>();
      map.put("description", "new playlist description");
      map.put("public", "false");
      map.put("name", "M-Happy");
      //application.spotify.createPlaylist("1246481687",map);
      SAPI.getService().createPlaylist("1246481687", map, new Callback<Playlist>() {
         @Override
         public void success(Playlist playlist, Response response) {
            Log.d("HomeActivity", "Check spotify lol");
         }

         @Override
         public void failure(RetrofitError error) {
            Log.d("tag", "Failed, error was of type" + error.toString());
         }
      });

      //SAPI.getService().getPlaylistTracks("1246481687", "35BTjSEGnmXcZiCV6ckE9A", oMap);
      showPlaylistCreatedDialog(HomeActivity.this);
   }

   protected void angry_click(View view) {
      showPlaylistCreatedDialog(HomeActivity.this);
   }

   protected void sad_click(View view) {
      showPlaylistCreatedDialog(HomeActivity.this);
   }

   protected void slep_click(View view) {
      showPlaylistCreatedDialog(HomeActivity.this);
   }

   private void showPlaylistCreatedDialog(Context c) {
      //final EditText text = new EditText(c);
      AlertDialog dialog = new AlertDialog.Builder(c)
              .setTitle("Playlist Created!")
              .setMessage("Head to Spotify to take a listen!")
              //.setView(text)
              /*.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                    //String task = String.valueOf(text.getText());
                 }
              })*/
              .setNegativeButton("Continue", null)
              .create();
      dialog.show();
   }
}

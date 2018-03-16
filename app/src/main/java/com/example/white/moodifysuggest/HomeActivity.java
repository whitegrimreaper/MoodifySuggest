package com.example.white.moodifysuggest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

/**
 * Created by White on 3/7/2018.
 */

public class HomeActivity extends AppCompatActivity {
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_home);
      
      Log.d("eh", "it worked");
   }
}

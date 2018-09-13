package com.example.android.abnd_p4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class PlaylistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Intent intent = getIntent();
        Playlist p = intent.getParcelableExtra("playlist");
        Log.d("PLAYLIST ACTIVITY", ""+p.getSongsNumber());
    }
}

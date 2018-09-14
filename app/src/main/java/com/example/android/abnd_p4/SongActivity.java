package com.example.android.abnd_p4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SongActivity extends AppCompatActivity{


    public static final String PLAYLIST = "playlist";               // Standardize the playlist tag for easy modification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        LinearLayout linearLayout = findViewById(R.id.current_playing_song_linear_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        // Gets the intent coming from MainActivity with Playlist data
        Playlist p = getIntent().getParcelableExtra(PLAYLIST);

        // Gets the song list view reference
        ListView listView = findViewById(R.id.song_list_view);

        // Create an adapter for the songs list and attach it to the song list view
        SongAdapter adapter = new SongAdapter(this, p.getSongs());
        listView.setAdapter(adapter);
        listView.setEnabled(false);
    }

}

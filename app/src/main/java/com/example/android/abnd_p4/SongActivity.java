package com.example.android.abnd_p4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

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

        final ArrayList<Song> songs = p.getSongs();

        // Create an adapter for the songs list and attach it to the song list view
        SongAdapter adapter = new SongAdapter(this, songs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            SongAdapter.ViewHolder viewHolder;
            Song songPlaying;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song songToPlay = songs.get(position);

                // If there is no song playing
                if(songPlaying != null)
                {
                    songPlaying.setIsCurrentlyPlaying(false);
                    viewHolder.playImageView.setImageResource(songPlaying.getImageId());
                }

                // set the song to play
                songToPlay.setIsCurrentlyPlaying(true);
                songPlaying = songToPlay;

                // Get the view holder reference
                viewHolder = (SongAdapter.ViewHolder) view.getTag();
                viewHolder.playImageView.setImageResource(songPlaying.getImageId());
            }
        });
    }

}

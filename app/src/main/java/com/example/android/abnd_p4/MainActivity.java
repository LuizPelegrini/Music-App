package com.example.android.abnd_p4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a list of playlists
        ArrayList<Playlist> playlists = PlaylistsFactory.createPlaylists();

        // Get the list view
        ListView listView = findViewById(R.id.playlist_list_view);

        // Create a custom adapter for the playlist and attach it to the list view
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(this, playlists);
        listView.setAdapter(playlistAdapter);
    }
}

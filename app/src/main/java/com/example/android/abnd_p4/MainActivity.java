package com.example.android.abnd_p4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a list of playlists
        final ArrayList<Playlist> playlists = PlaylistsFactory.createPlaylists();

        // Get the list view
        ListView listView = findViewById(R.id.playlist_list_view);

        // Create a custom adapter for the playlist and attach it to the list view
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(this, playlists);
        listView.setAdapter(playlistAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Playlist p = playlists.get(position);
                Intent intent = new Intent(MainActivity.this, PlaylistActivity.class);
                Log.d("MAIN ACTIVITY", p.getName());
                startActivity(intent);
            }
        });
    }
}

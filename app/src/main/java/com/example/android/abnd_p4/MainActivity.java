package com.example.android.abnd_p4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a list of playlists
        final ArrayList<Playlist> playlists = PlaylistsFactory.createPlaylists();

        // Update current playing song
        updateCurrentPlayingSongUI();

        // Get the list view
        ListView listView = findViewById(R.id.playlist_list_view);

        // Create a custom adapter for the playlist and attach it to the list view
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(this, playlists);
        listView.setAdapter(playlistAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Playlist p = playlists.get(position);
                Intent intent = new Intent(MainActivity.this, SongActivity.class);
                intent.putExtra(SongActivity.PLAYLIST, p);
                startActivity(intent);
            }
        });
    }

    // Update the current playing song UI information
    private void updateCurrentPlayingSongUI()
    {
        Song currentPlayingSong = CurrentPlayingSong.getInstance().getCurrentSong();
        TextView songTextView = findViewById(R.id.current_playing_song_text_view);
        TextView artistTextView = findViewById(R.id.current_playing_artist_text_view);

        if(currentPlayingSong != null) {
            songTextView.setText(currentPlayingSong.getName());
            artistTextView.setText(currentPlayingSong.getArtistName());
        }
        else {
            songTextView.setText(getResources().getString(R.string.no_song_playing));
            artistTextView.setText("");
        }
    }
}

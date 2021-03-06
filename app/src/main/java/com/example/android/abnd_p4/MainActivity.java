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

        // Update the top view that contains the info of the song playing
        updateCurrentPlayingSong(playlists);

        // Update current playing song
        updateCurrentPlayingSongUI();

        // Get the list view
        ListView listView = findViewById(R.id.playlist_list_view);

        // Create a custom adapter for the playlist and attach it to the list view
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(this, playlists);
        listView.setAdapter(playlistAdapter);

        // Listen to any click event on any item of the list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Playlist p = playlists.get(position);

                // Create an intent containing playlist parcelable info
                Intent intent = new Intent(MainActivity.this, SongActivity.class);
                intent.putExtra(SongActivity.PLAYLIST, p);

                // And send it to the song activity
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

        if(currentPlayingSong != null)
        {
            songTextView.setText(currentPlayingSong.getName());
            artistTextView.setText(currentPlayingSong.getArtistName());
        }
        else
        {
            songTextView.setText(getResources().getString(R.string.no_song_playing));
            artistTextView.setText("");
        }
    }


    // Update the current playing song info in my playlists arraylist
    // This is necessary because when I pass a Parcelable Playlist, it creates a copy of the playlist,
    // so all the changes I make in the song activity does not reflect on this list
    private void updateCurrentPlayingSong(ArrayList<Playlist> playlists)
    {
        if(CurrentPlayingSong.getInstance().getCurrentSong() == null)
            return;

        // WARNING: Not a very clever way to find an id, but it is fine for this project
        for(int i=0;i < playlists.size(); i++)
        {
           Playlist p = playlists.get(i);
           for (int j = 0; j < p.getSongs().size(); j++)
           {
               Song song = p.getSongs().get(j);
               if(song.getId() == CurrentPlayingSong.getInstance().getCurrentSong().getId())
                   song.setIsCurrentlyPlaying(true);
               else
                   song.setIsCurrentlyPlaying(false);
           }
        }
    }
}

package com.example.android.abnd_p4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity{


    public static final String PLAYLIST = "playlist";               // Standardize the playlist tag for easy modification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        // Update the view containing the info of the current song
        updateCurrentPlayingSongUI();

        // Gets the intent coming from MainActivity with Playlist data
        Playlist p = getIntent().getParcelableExtra(PLAYLIST);

        // Gets the song list view reference
        ListView listView = findViewById(R.id.song_list_view);

        // List of songs coming from the playlist activity
        final ArrayList<Song> songs = p.getSongs();

        // Create an adapter for the songs list and attach it to the song list view
        SongAdapter adapter = new SongAdapter(this, songs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            SongAdapter.ViewHolder viewHolder;
            Song songPlaying;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // The clicked item will be the song to play
                Song songToPlay = songs.get(position);

                // Get any reference to the song that was playing before
                songPlaying = CurrentPlayingSong.getInstance().getCurrentSong();

                // If there is no song playing
                if(songPlaying != null)
                {
                    // "Pause" it
                    songPlaying.setIsCurrentlyPlaying(false);

                    // Gets the view reference of the currently playing song
                    viewHolder = CurrentPlayingSong.getInstance().getCurrentHolder();

                    // Change its imageView (pause to play), as it is no longer the current song
                    viewHolder.playImageView.setImageResource(songPlaying.getImageId());
                }

                // set the song to play
                songToPlay.setIsCurrentlyPlaying(true);
                songPlaying = songToPlay;

                // Get the view currentHolder reference
                viewHolder = (SongAdapter.ViewHolder) view.getTag();

                // Change its imageView (play to pause), as it became the current song
                viewHolder.playImageView.setImageResource(songPlaying.getImageId());

                // Update the variables on the CurrentPlayingSong class
                CurrentPlayingSong.getInstance().setCurrentSong(songPlaying);
                CurrentPlayingSong.getInstance().setCurrentHolder(viewHolder);

                // Update the top view that shows the current song info
                updateCurrentPlayingSongUI();
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

}

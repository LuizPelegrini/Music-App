package com.example.android.abnd_p4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

class SongAdapter extends ArrayAdapter<Song> {

    // Helper class to hold view references, ViewHolder design pattern
    static class ViewHolder
    {
        TextView songNameTextView;
        TextView artistNameTextView;
        ImageButton playButton;
    }

    SongAdapter(@NonNull Context context, @NonNull List<Song> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Gets the song from the arraylist
        Song song = getItem(position);

        // Reference holder
        ViewHolder holder;

        // If there is no available view, inflate a new one and hold the reference for its inner views
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_item, parent, false);
            holder.songNameTextView = convertView.findViewById(R.id.song_name_text_view);
            holder.artistNameTextView = convertView.findViewById(R.id.song_artist_text_view);
            holder.playButton = convertView.findViewById(R.id.song_play_image_button);

            convertView.setTag(holder);
        }
        // Otherwise, gets the inner views references
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        if(song != null)
            setViewData(holder, song);

        // Return the modified view
        return convertView;
    }

    private void setViewData(ViewHolder holder, final Song songData)
    {
        // And set the data for each inner view
        holder.songNameTextView.setText(songData.getName());
        holder.artistNameTextView.setText(songData.getArtistName());
        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton imageButton = (ImageButton)v;
                CurrentPlayingSong.getInstance().setCurrentSong(songData, imageButton);
                imageButton.setBackgroundResource(R.drawable.ic_pause_circle_filled);
            }
        });

        if(songData == CurrentPlayingSong.getInstance().getCurrentSong())
            holder.playButton.setBackgroundResource(R.drawable.ic_pause_circle_filled);
        else
            holder.playButton.setBackgroundResource(R.drawable.ic_play_circle_filled);
    }
}

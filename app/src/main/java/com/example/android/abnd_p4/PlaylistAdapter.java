package com.example.android.abnd_p4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class PlaylistAdapter extends ArrayAdapter<Playlist> {


    // Usage of ViewHolder design pattern for optimal performance
    static class ViewHolder
    {
        // Holds the reference of views to remove unnecessary findViewById() calls
        ImageView playlistImageView;
        TextView playlistNameTextView;
        TextView playlistSongsTextView;
    }

    PlaylistAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get a playlist object
        Playlist playlist = getItem(position);

        // viewHolder to store views references
        ViewHolder viewHolder;

        // If there is no view available, then create a new one
        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            // Inflate it...
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.playlist_item, parent, false);

            // Hold the views...
            viewHolder.playlistNameTextView = convertView.findViewById(R.id.playlist_name_text_view);
            viewHolder.playlistSongsTextView = convertView.findViewById(R.id.playlist_number_songs_text_view);
            viewHolder.playlistImageView = convertView.findViewById(R.id.playlist_image);

            // And store these view references as a tag for further use
            convertView.setTag(viewHolder);
        }
        else
        {
            // If there is view available for reuse, then get the viewholder, which contains all the view references
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // All it is left to do is to populate the views with data
        viewHolder.playlistImageView.setImageResource(playlist.getImageId());
        viewHolder.playlistNameTextView.setText(playlist.getName());
        viewHolder.playlistSongsTextView.setText(getContext().getResources().getString(R.string.number_songs, playlist.getSongsNumber()));

        // Return the modified view
        return convertView;
    }
}

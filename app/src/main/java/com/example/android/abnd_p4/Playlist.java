package com.example.android.abnd_p4;

import java.util.ArrayList;

public class Playlist {

    private String _name;           // The name of the playlist
    private int _imageId;           // The image of the playlist
    private ArrayList<Song> _songs; // The songs this playlist contains

    Playlist(String name, int imageId)
    {
        this._name = name;
        this._imageId = imageId;
        this._songs = new ArrayList<>();
    }

    public void AddNewSong(Song song) {
        _songs.add(song);
    }

    public String getName() {
        return _name;
    }

    public int getSongsNumber() {
        return _songs.size();
    }

    public int getImageId() {
        return _imageId;
    }
}

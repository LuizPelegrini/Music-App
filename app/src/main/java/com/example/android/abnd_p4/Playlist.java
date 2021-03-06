package com.example.android.abnd_p4;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Playlist implements Parcelable{

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

    //// getters ////
    public String getName() {
        return _name;
    }

    public int getSongsNumber() {
        return _songs.size();
    }

    public int getImageId() {
        return _imageId;
    }

    public ArrayList<Song> getSongs() {
        return _songs;
    }

    //// Parcelable methods /////
    @Override
    public int describeContents() {
        return 0;
    }

    // Parcel the Playlist object, so I can attach it with an intent
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeInt(_imageId);
        dest.writeTypedList(_songs);
    }

    // CREATOR of the Playlist Parcelable
    public static final Parcelable.Creator<Playlist> CREATOR = new Parcelable.Creator<Playlist>(){
        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }

        @Override
        public Playlist createFromParcel(Parcel source) {
            String name = source.readString();
            int imageId = source.readInt();
            Playlist p = new Playlist(name, imageId);
            source.readTypedList(p._songs, Song.CREATOR);
            return p;
        }
    };
}

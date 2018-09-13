package com.example.android.abnd_p4;

import android.os.Parcel;
import android.os.Parcelable;

class Song implements Parcelable
{
    private String _name;           // The name of this song
    private String _artistName;     // Artist's name of this song

    Song(String name, String artistName)
    {
        this._name = name;
        this._artistName = artistName;
    }

    public String getName() {
        return _name;
    }

    public String getArtistName() {
        return _artistName;
    }

    //// Parcelable methods /////
    @Override
    public int describeContents() {
        return 0;
    }

    // Parcel the Song object, so I can attach it with an intent, inside of a Playlist Parcelable object
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeString(_artistName);
    }

    // CREATOR of the Song Parcelable
    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            String name = source.readString();
            String artistName = source.readString();
            return new Song(name, artistName);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}

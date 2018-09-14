package com.example.android.abnd_p4;

import android.os.Parcel;
import android.os.Parcelable;

class Song implements Parcelable
{
    private String _name;                   // The name of this song
    private String _artistName;             // Artist's name of this song
    private boolean _isCurrentlyPlaying;    // Is this song being currently playing?

    Song(String name, String artistName)
    {
        this._name = name;
        this._artistName = artistName;
        this._isCurrentlyPlaying = false;
    }

    public String getName() {
        return _name;
    }

    public String getArtistName() {
        return _artistName;
    }

    public boolean getIsCurrentlyPlaying() { return _isCurrentlyPlaying; }

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
        dest.writeByte((byte)(_isCurrentlyPlaying ? 1:0));
    }

    // CREATOR of the Song Parcelable
    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            String name = source.readString();
            String artistName = source.readString();
            Song song = new Song(name, artistName);
            song._isCurrentlyPlaying = ((int)source.readByte()) == 1;

            return song;
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}

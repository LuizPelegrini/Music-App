package com.example.android.abnd_p4;

import android.os.Parcel;
import android.os.Parcelable;

class Song implements Parcelable
{
    private int _id;                        // The id of the song
    private String _name;                   // The name of this song
    private String _artistName;             // Artist's name of this song
    private boolean _isCurrentlyPlaying;    // Is this song being currently playing?
    private int _imageId;                   // The image id resource that represents PLAY or PAUSE drawable

    Song(int id, String name, String artistName)
    {
        this._id = id;
        this._name = name;
        this._artistName = artistName;
        this.setIsCurrentlyPlaying(false);
    }

    //// getters /////
    public int getId() { return _id; }
    public String getName() { return _name; }
    public String getArtistName() { return _artistName; }
    public int getImageId() { return _imageId; }

    //// setter ////
    public void setId(int id) { this._id = id; }
    public void setIsCurrentlyPlaying(boolean isPlayingNow)
    {
        this._isCurrentlyPlaying = isPlayingNow;

        if(isPlayingNow)
            _imageId = R.drawable.ic_pause_circle_filled;
        else
            _imageId = R.drawable.ic_play_circle_filled;
    }

    //// Parcelable methods /////
    @Override
    public int describeContents() { return 0; }

    // Parcel the Song object, so I can attach it with an intent, inside of a Playlist Parcelable object
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(_name);
        dest.writeString(_artistName);
        dest.writeInt((_isCurrentlyPlaying ? 1:0));
    }

    // CREATOR of the Song Parcelable
    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            int id = source.readInt();
            String name = source.readString();
            String artistName = source.readString();
            Song song = new Song(id, name, artistName);
            song.setIsCurrentlyPlaying(source.readInt() == 1);

            return song;
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}

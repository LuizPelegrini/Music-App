package com.example.android.abnd_p4;

import java.util.ArrayList;

public class PlaylistsFactory {

    /**
     * This class is only responsible to create a list of playlists
     * */


    public static ArrayList<Playlist> createPlaylists()
    {
        ArrayList<Playlist> playlists = new ArrayList<>();

        for (int i=0; i < 5; i++)
        {
            Playlist newPlaylist = new Playlist("Name" + i, R.mipmap.ic_launcher);
            for(int j = 0; j < 2; j++)
            {
                Song song = new Song("Song " + j, "Artist " + j);
                newPlaylist.AddNewSong(song);
            }
        }

        return playlists;
    }

}

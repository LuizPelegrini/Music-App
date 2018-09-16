package com.example.android.abnd_p4;

import java.util.ArrayList;
import java.util.Random;

public class PlaylistsFactory {

    /**
     * This class is only responsible to create a list of playlists
     * */

    // List of random artists
    private static String[] artistNames = {
            "Guns n\' Roses",
            "Dualipa",
            "Justin Timberlake",
            "Alesso",
            "Zedd",
            "Selena Gomez",
            "Pearl Jam",
            "Roxette",
            "Bryan Adams",
            "Of Monsters and Men",
            "Édith Piaf",
            "Mumford & Sons",
            "Jason Mraz",
            "Arctic Monkeys",
            "Michael Jackson",
            "Kiss",
            "Lana Del Rey"
    };

    // Data structure to store the playlists info
    static ArrayList<Playlist> playlists;

    // Create a bunch of playlists, and for each playlist, create some songs objects
    public static ArrayList<Playlist> createPlaylists()
    {
        // Only creates new playlists, if there is none
        if(playlists == null)
        {
            Random random = new Random();
            // A simulated id to assign for each song
            int id = 0;
            playlists = new ArrayList<>();

            for (int i = 0; i < 5; i++)
            {
                Playlist newPlaylist = new Playlist("Playlist " + (i+1), R.drawable.ic_shop_two);
                for (int j = 0; j < (i+5); j++)
                {
                    Song song = new Song(id++,"Song " + id, "Artist: " + artistNames[random.nextInt(artistNames.length)]);
                    newPlaylist.AddNewSong(song);
                }
                playlists.add(newPlaylist);
            }
        }

        return playlists;
    }

}

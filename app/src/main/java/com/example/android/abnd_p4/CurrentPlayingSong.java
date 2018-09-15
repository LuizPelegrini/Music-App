package com.example.android.abnd_p4;

import android.widget.ImageButton;

class CurrentPlayingSong {

    /**
     * Singleton pattern to force an existence of only one playing song shared in the app
     * */
    private static CurrentPlayingSong instance;

    private Song _currentSong;
    private ImageButton _songButton;

    // Private constructor
    private CurrentPlayingSong(){}

    // This will be responsible to return an instance of this class
    // If there is none available, then create one. Otherwise, return an a previously created instance
    public static CurrentPlayingSong getInstance()
    {
        if(instance == null)
            instance = new CurrentPlayingSong();

        return instance;
    }

    ////// getter /////
    public void setCurrentSong(Song song, ImageButton newImageButton) {
        // If there is a song playing now, "pause" it
        if(_currentSong != null) {
            _currentSong.setIsCurrentlyPlaying(false);
            newImageButton.setBackgroundResource(R.drawable.ic_pause_circle_filled);
            _songButton.setBackgroundResource(R.drawable.ic_play_circle_filled);
            _songButton = newImageButton;

            _currentSong = song;
            _currentSong.setIsCurrentlyPlaying(true);
        }
        else
        {
            // Updates the song currently playing
            _currentSong = song;
            _currentSong.setIsCurrentlyPlaying(true);
            newImageButton.setBackgroundResource(R.drawable.ic_pause_circle_filled);
            _songButton = newImageButton;
        }


    }

    ///// setter /////
    public Song getCurrentSong() {
        return this._currentSong;
    }
}

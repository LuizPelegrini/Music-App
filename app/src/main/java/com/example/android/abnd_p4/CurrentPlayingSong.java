package com.example.android.abnd_p4;

class CurrentPlayingSong {

    /**
     * Singleton pattern to force an existence of only one playing song shared in the app
     * */
    private static CurrentPlayingSong instance;

    private Song _currentSong;

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
    public void setCurrentSong(Song song) {
        _currentSong = song;
    }

    ///// setter /////
    public Song getCurrentSong() {
        return this._currentSong;
    }
}

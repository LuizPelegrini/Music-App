package com.example.android.abnd_p4;

class CurrentPlayingSong {

    /**
     * Singleton pattern to force an existence of only one playing song shared in the app
     * */
    private static CurrentPlayingSong instance;

    private Song _currentSong;                          // The current song playing
    private SongAdapter.ViewHolder _currentHolder;      // The reference to the view which represents the currently playing song

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

    ////// setter /////
    public void setCurrentSong(Song songToPlay) { _currentSong = songToPlay; }
    public void setCurrentHolder(SongAdapter.ViewHolder holder) { _currentHolder = holder; }

    ///// getter /////
    public Song getCurrentSong() { return this._currentSong; }
    public SongAdapter.ViewHolder getCurrentHolder() { return _currentHolder; }
}

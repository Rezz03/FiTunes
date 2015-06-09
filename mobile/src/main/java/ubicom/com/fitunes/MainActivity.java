package ubicom.com.fitunes;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;

public class MainActivity extends ActionBarActivity {

    int[] songs = {R.raw.song, R.raw.song2, R.raw.song3};//TODO Remove after Milestone 2. Temporary (static) playlist of songs.
    int nextSong = 0;

    public MediaPlayer mediaPlayer;//TODO Remove this when we implement a real music player activity. Messy, single file, and for testing only
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO Remove this when we implement a real music player activity. Messy, single file, and for testing only
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
                playNext();
            }
        });
    }
    //This Method Plays the Next Song in the Playlist
    public void playNext() {

        if(nextSong < songs.length){
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, songs[nextSong]);
            mediaPlayer.setVolume(1,1);
            mediaPlayer.start();
        }
        nextSong++;

    }

    //Song has been downvoted mutes for remainder of song
    public void hate() {
        //TODO Add veto vote to server after Milestone 2
        mediaPlayer.setVolume(0,0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

package ubicom.com.fitunes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    int[] songs = {R.raw.song, R.raw.song2, R.raw.song3};//TODO Remove after Milestone 2. Temporary (static) playlist of songs.
    String[] songNames = {"Here Comes the Sun", "Maxwell's Silver Hammer", "The End"};
    int nextSong = 1;
    List<String> favourites;                           //TODO change identifying type to match song id

    public MediaPlayer mediaPlayer;//TODO Remove this when we implement a real music player activity. Messy, single file, and for testing only
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* This gets the intent from the the Group activity and sets the name of the group
        *  based on which one was selected. If no group is selected, the user is told.
        */
        TextView tv = (TextView) findViewById(R.id.group_tv_id);
        Bundle getGroupNameExtra = getIntent().getExtras();
        if (getGroupNameExtra != null)
        {
            tv.setText(getGroupNameExtra.getString("GroupName"));
        }
        else
        {
            String noGroupSelected = "No Group Selected!";

            tv.setText(noGroupSelected);
        }

            favourites = new ArrayList<String>();
        //TODO Remove this when we implement a real music player activity. Messy, single file, and for testing only
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
                playNext();
            }
        });

    }

    /**
     * Triggering music on start instead of button press. Maybe we move this later to support the music playing when the app is
     * not in focus.
     */
    protected void onStart(){
        super.onStart();
        mediaPlayer.start();
    }

    /**
     * Adds current song to favourites list
     */
    public void addToFavourites(){
        favourites.add(songNames[nextSong--]);
    }

    //This Method Plays the Next Song in the Playlist
    public void playNext() {
    }

    //Song has been downvoted mutes for remainder of song
    public void hate() {
        nextSong++;
        if(nextSong < songs.length){
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, songs[nextSong]);
            mediaPlayer.setVolume(1, 1);
            mediaPlayer.start();
        } else {
            mediaPlayer.setVolume(0,0);
        }

    }

    public void showSummary() {
        mediaPlayer.stop();
        Intent summaryIntent = new Intent(MainActivity.this, SummaryActivity.class);
        summaryIntent.putStringArrayListExtra("songs", (ArrayList<String>)favourites);
        startActivity(summaryIntent);
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

package ubicom.com.fitunes;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    int[] songs = {R.raw.song, R.raw.song2, R.raw.song3};//TODO Remove after Milestone 2. Temporary (static) playlist of songs.
    String[] songNames = {"Here Comes the Sun", "Maxwell's Silver Hammer", "The End"};
    int songIndex = 0;
    List<String> favourites;                           //TODO change identifying type to match song id
    boolean dislike = false;

    public MediaPlayer mediaPlayer;//TODO Remove this when we implement a real music player activity. Messy, single file, and for testing only
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
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


    }

    /**
     * Triggering music on start instead of button press. Maybe we move this later to support the music playing when the app is
     * not in focus.
     */
    protected void onStart(){
        super.onStart();
        playAudio();
    }

    /**
     * Adds current song to favourites list
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void addToFavourites(){
        favourites.add(songNames[songIndex]);
    }

    //This Method Plays the Next Song in the Playlist
    public void playAudio() {
        dislike = false;
        mediaPlayer = MediaPlayer.create(this, songs[songIndex]);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.stop();
                songIndex++;
                playAudio();
            }
        });
        mediaPlayer.start();
        mediaPlayer.setVolume(1, 1);
    }

    //Song has been downvoted mutes for remainder of song
    public void hate() {

        if(dislike){
            mediaPlayer.setVolume(0, 0);
        } else {
            mediaPlayer.setVolume(0.5f,0.5f);
            dislike = true;
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

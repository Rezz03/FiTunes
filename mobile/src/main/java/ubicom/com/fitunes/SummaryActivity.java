package ubicom.com.fitunes;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SummaryActivity extends ActionBarActivity {

    int[] songs = {R.raw.song, R.raw.song2, R.raw.song3};   //TODO Remove after Milestone 2. Temporary (static) playlist of songs.
    List<String> favourites;   //TODO change identifying type to match song id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        favourites = new ArrayList<String>();
        favourites = getIntent().getExtras().getStringArrayList("songs");

        if (favourites.isEmpty()) {
            Toast.makeText(this, "No favourite songs", Toast.LENGTH_LONG).show();
        } else {
//            TODO Add songs to list view

            ListView listView = (ListView) findViewById(R.id.listView);

            String[] values = new String[favourites.size()];
            for (int i = 0; i < favourites.size(); i++) {
                values[i] = favourites.get(i);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, values);

            // Assign adapter to ListView
            listView.setAdapter(adapter);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
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

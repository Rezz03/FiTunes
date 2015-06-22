package ubicom.com.fitunes;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/*
*
* Class: GroupActivity
* Author: Sam Churney, 2015
* Course: Ubiquitous Computing
* Group: 3
*
* This activity presents the user with a list of available groups to join.
* When a group is selected, they are taken to the MainActivity where the title of the group they
* selected is displayed.
*
*/
public class GroupActivity extends ActionBarActivity implements OnItemClickListener{
    ListView listView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Sets the layout for this activity to be based on fragment_group
        setContentView(R.layout.fragment_group);

        //Makes a new list view based off of groupsList
        listView = (ListView) findViewById(R.id.groupsList);

        //Set a click listener for each item in the listView (i.e. when a group is selected)
        listView.setOnItemClickListener(this);
    }

    /*
     * This is what happens on each click/tap of a group:
     *      - A new intent is created to start the MainActivity.
     *      - The item that is selected gets passed along in the intent to be used as the
     *        title of the group in the MainActivity.
     *
     * Parameters:
        adapter - The AdapterView where the click happened.
        view - The view within the AdapterView that was clicked
        position - The position of the view in the adapter.
        id - The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

        Toast.makeText(getApplicationContext(), "You joined: " + ((TextView) view).getText(),
                Toast.LENGTH_SHORT).show();

        String selectedGroup = ((TextView) view).getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("GroupName", selectedGroup);
        startActivity(intent);

    }

    /*
    *
    * onCreateOptionsMenu and onOptionsItemSelected control the functionality
    * of the options menu in the top right corner of the display.
    *
    */
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


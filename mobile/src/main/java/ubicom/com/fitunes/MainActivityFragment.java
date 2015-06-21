package ubicom.com.fitunes;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageButton;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment{

    public MainActivityFragment() {
    }
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ((MainActivity) getActivity()).playNext();
        ImageButton buttonUp = (ImageButton) rootView.findViewById(R.id.buttonUp);
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonUp(v);
            }
        });

        ImageButton buttonDown = (ImageButton) rootView.findViewById(R.id.buttonDown);
        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonDown(v);
            }
        });

        Button endButton= (Button) rootView.findViewById(R.id.buttonEnd);
        endButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onButtonEnd(v);
            }
        });

        return rootView;
    }
    public void onButtonUp(View v)
    {
        //Todo Add Favorite Function on Click
        Toast.makeText(getActivity(), "Up Button Pressed!", Toast.LENGTH_LONG).show();
        ((MainActivity) getActivity()).addToFavourites();
        //TODO Remove this Temp Fundtion when we implement a real music player!
    }

    public void onButtonDown(View v)
    {
       //Todo Add Skip Function on Click
        Toast.makeText(getActivity(), "Down Button Pressed!", Toast.LENGTH_LONG).show();
        ((MainActivity) getActivity()).hate();//TODO Remove this Temp Fundtion when we implement a real music player!
    }
    public void onButtonEnd(View v)
    {
        //TODO End current workout session
        ((MainActivity)getActivity()).showSummary();    //TODO Show summary of fitness information and favourite songs
    }

}

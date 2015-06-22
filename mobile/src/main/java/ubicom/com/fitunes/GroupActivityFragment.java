package ubicom.com.fitunes;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A placeholder fragment containing a simple view.
 */
public class GroupActivityFragment extends Fragment {

    public GroupActivityFragment() {
    }

    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_group, container, false);

        return rootView;
    }


}

package name.hd.cloudmoney.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import name.hd.cloudmoney.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindPersonFragment extends Fragment {


    public FindPersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_person, container, false);
    }

}

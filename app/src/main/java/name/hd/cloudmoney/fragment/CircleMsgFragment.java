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
public class CircleMsgFragment extends Fragment {


    public CircleMsgFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_circle_msg, container, false);
    }

}

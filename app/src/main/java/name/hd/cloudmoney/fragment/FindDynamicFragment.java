package name.hd.cloudmoney.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import name.hd.cloudmoney.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindDynamicFragment extends Fragment {
    private View view;
    private Activity context;
    private TextView mTex22,mTex32;
    public FindDynamicFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_find_dynamic, container, false);
        init();
        return view;
    }
    private void init(){
        mTex22=f(R.id.tex22);
        mTex32=f(R.id.tex32);
        String str1 = "乌索普<font color='#00bfff'>回复</font>路飞：我才是船长";
        mTex22.setText(Html.fromHtml(str1));
        String str2 = "乔巴<font color='#00bfff'>回复</font>路飞：我要吃棒棒糖";
        mTex32.setText(Html.fromHtml(str2));
    }

    public <T extends View> T f(int id) {
        return (T) view.findViewById(id);
    }
}

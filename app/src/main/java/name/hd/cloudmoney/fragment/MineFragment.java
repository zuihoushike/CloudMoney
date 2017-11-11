package name.hd.cloudmoney.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import name.hd.cloudmoney.R;
import name.hd.cloudmoney.db.UserDB;
import name.hd.cloudmoney.tools.ScreenSize;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {
    private Activity context;
    private Intent intent;
    private View view,mStatusColor;
    private TextView mSelfName;
    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context= (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_mine, container, false);
        init();
        monitor();
        immerseStatus();
        return view;
    }
    private void init(){
        mSelfName=f(R.id.mine_self_name);
        UserDB udb=new UserDB();
        udb.add(context,mSelfName.getText().toString().trim());
    }
    private void monitor(){

    }
    //获取状态栏高度并赋颜色
    public void immerseStatus() {
        mStatusColor = f(R.id.fr_mine_status_color);
        ViewGroup.LayoutParams params = mStatusColor.getLayoutParams();
        params.height = ScreenSize.getStatusBarHeight(context);
    }
    public <T extends View> T f(int id) {
        return (T)view. findViewById(id);
    }

}

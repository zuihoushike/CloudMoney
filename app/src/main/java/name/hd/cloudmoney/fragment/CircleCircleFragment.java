package name.hd.cloudmoney.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import name.hd.cloudmoney.R;
import name.hd.cloudmoney.db.UserDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleCircleFragment extends Fragment implements View.OnClickListener {
    int n=0;
    private Intent intent;
    private UserDB udb=new UserDB();
    private View view;
    private Activity context;
    private TextView mTex22,mTex32,mFabNum,mTvFab;
    private LinearLayout mFab,mFabNoUseLayout;

    public CircleCircleFragment() {
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
        view=inflater.inflate(R.layout.fragment_circle_circle, container, false);
        init();
        monitor();
        return view;
    }
    private void init(){
        mTex22=f(R.id.tex22);
        mTex32=f(R.id.tex32);
        String str1 = "乌索普<font color='#00bfff'>回复</font>路飞：我才是船长";
        mTex22.setText(Html.fromHtml(str1));
        String str2 = "乔巴<font color='#00bfff'>回复</font>路飞：我要吃棒棒糖。";
        mTex32.setText(Html.fromHtml(str2));
        mFab=f(R.id.ll_circle_fab);
        mFabNum=f(R.id.tv_fab_num);
        mFabNoUseLayout=f(R.id.fab_no_use_layout);
        mTvFab=f(R.id.tv_circle_fab);
    }
    private void monitor(){
        mFab.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_circle_fab:
                if (n % 2 == 0) {
                    mFabNoUseLayout.setVisibility(View.VISIBLE);
                    mFabNum.setText(udb.getAllUserName(context).get(0).getName()+"等赞过");
                    mTvFab.setText(mFabNum.getText().toString().trim().split("、").length+"");
                    n++;
                } else {
                    mFabNoUseLayout.setVisibility(View.GONE);
                    mFabNum.setText("");
                    mTvFab.setText(mFabNum.length()+"");
                    n++;
                }
                break;
            default:
                break;
        }
    }
    public <T extends View> T f(int id) {
        return (T) view.findViewById(id);
    }

}

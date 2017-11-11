package name.hd.cloudmoney.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import name.hd.cloudmoney.R;
import name.hd.cloudmoney.cloudmsg.ConfigActivity;
import name.hd.cloudmoney.tools.ScreenSize;
import name.hd.cloudmoney.view.SmartScrollView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoneyFragment extends Fragment implements View.OnClickListener {
    private Activity context;
    private Intent intent;
    private View view,mStatusColor,mGoneStatusColor;//布局，状态栏颜色
    private LinearLayout mTitle,mGoneTitle;
    private SmartScrollView mSvMoney;
    private TextView mRtMenu,mRtGoneMenu;
    private int mTouchShop;//最小滑动距离
    protected float mFirstY;//触摸下去的位置
    protected float mCurrentY;//滑动时Y的位置
    protected int direction;//判断是否上滑或者下滑的标志
    protected boolean mShow;//判断是否执行了上滑动画
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context= (Activity) context;
    }

    public MoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_money, container, false);
        immerseStatus();
        immerseGoneStatus();
        init();
        monitor();
        return view;
    }
    private void init(){
        mTitle=f(R.id.fr_money_title);
        mGoneTitle=f(R.id.fr_money_gone_title);
        mGoneStatusColor=f(R.id.fr_money_status_gone_color);
        mSvMoney=f(R.id.sv_money);
        mRtMenu=f(R.id.tv_money_rt_menu);
        mRtGoneMenu=f(R.id.tv_money_rt_gone_menu);
    }
    private void monitor(){
        slideUpDown();
        mRtMenu.setOnClickListener(this);
        mRtGoneMenu.setOnClickListener(this);
        mGoneTitle.setVisibility(View.GONE);
        mTitle.setVisibility(View.VISIBLE);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_money_rt_menu:
                intent=new Intent(context, ConfigActivity.class);
                context.startActivity(intent);
                break;
            case R.id.tv_money_rt_gone_menu:
                intent=new Intent(context, ConfigActivity.class);
                context.startActivity(intent);
                break;
            default:
                break;
        }
    }
    /*
       上下滑动
        */
    private void slideUpDown() {
        mTouchShop = ViewConfiguration.get(context).getScaledTouchSlop();//系统级别的一个属性,判断用户的最小滑动距离的,
        mSvMoney.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mFirstY = event.getY();//按下时获取位置
                        break;

                    case MotionEvent.ACTION_MOVE:
                        mCurrentY = event.getY();//得到滑动的位置
                        if (mCurrentY - mFirstY > mTouchShop) {//滑动的位置减去按下的位置大于最小滑动距离  则表示向下滑动
                            direction = 0;//down
                        } else if (mFirstY - mCurrentY > mTouchShop) {//反之向上滑动
                            direction = 1;//up
                        }

                        if (direction == 1) {//判断如果是向上滑动 则执行向上滑动的动画
                            if (mShow) {//判断动画是否执行了  执行了则改变状态
                                //执行往上滑动的动画
//                                tolbarAnim(1);
                                mGoneTitle.setVisibility(View.VISIBLE);
                                mTitle.setVisibility(View.GONE);
                                mShow = !mShow;
                            }
                        } else if (mSvMoney.isScrolledToTop()||(direction == 0&&mSvMoney.isScrolledToTop())) {//判断如果是向下滑动 则执行向下滑动的动画
                            if (!mShow) {//判断动画是否执行了  执行了则改变状态
                                //执行往下滑动的动画
//                                tolbarAnim(0);
                                mGoneTitle.setVisibility(View.GONE);
                                mTitle.setVisibility(View.VISIBLE);
                                mShow = !mShow;
                            }
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        break;

                }
                return false;
            }

        });
    }
    private void immerseStatus() {
        //获取状态栏高度并赋颜色
        mStatusColor=f(R.id.fr_money_status_color);
        ViewGroup.LayoutParams params=mStatusColor.getLayoutParams();
        params.height= ScreenSize.getStatusBarHeight(context);
    }
    private void immerseGoneStatus() {
        //获取状态栏高度并赋颜色
        mGoneStatusColor=f(R.id.fr_money_status_gone_color);
        ViewGroup.LayoutParams params=mGoneStatusColor.getLayoutParams();
        params.height= ScreenSize.getStatusBarHeight(context);
    }

    public <T extends View> T f(int id) {
        return (T)view. findViewById(id);
    }

}

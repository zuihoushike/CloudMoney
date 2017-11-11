package name.hd.cloudmoney.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import name.hd.cloudmoney.swipe.CaptureActivity;
import name.hd.cloudmoney.R;
import name.hd.cloudmoney.adapter.FindFragmentAdapter;
import name.hd.cloudmoney.tools.ScreenSize;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View
        .OnClickListener {
    private PopupWindow mPopupWindow;
    private Intent intent;
    private View view, mLine, mStatusColor,popupView;//绑定布局，蓝线，状态栏，加号
    private ImageView mIvTitleScreen;//筛选
    private TextView mTvTitleAdd,mLaunchGroupChat,mSwipe;
    private int mScreenWidth = 0, mPosition = 0, start = 0;//屏幕宽度，存储position，起始位置
    private RadioGroup mTitleLable;
    private Activity mContext;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    /*
    三要素
     */
    private ViewPager mPages;
    private List<Fragment> beans = new ArrayList<>();
    private FindFragmentAdapter mAdapter;

    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_find, container, false);
        immerseStatus();
        init();
        monitor();
        alertWindow();
        mPages.setAdapter(mAdapter);
        initFragment();
        moPageChange();
        //获取控件宽度
        mScreenWidth = mTitleLable.getMeasuredWidth();//ScreenSize.screenWidth(mContext);
        // -mIvTitleScreen.getMeasuredWidth()-mTvTitleAdd.getMeasuredWidth()
        //给线条设置宽、高
//        mLine.setLayoutParams(new RelativeLayout.LayoutParams(mScreenWidth / 6, 7));
        return view;
    }

    private void init() {
        mTitleLable = f(R.id.find_title_lable);
        mPages = f(R.id.find_pages);
//        mLine = f(R.id.find_slide_line);
        mAdapter = new FindFragmentAdapter(getChildFragmentManager());
        mIvTitleScreen = f(R.id.find_title_screen);
        mTvTitleAdd = f(R.id.find_title_add);
    }

    private void monitor() {
        mTitleLable.setOnCheckedChangeListener(this);
        mTvTitleAdd.setOnClickListener(this);
    }

    /*
    PopupWindow
     */
    private void alertWindow() {
        popupView = LayoutInflater.from(mContext).inflate(R.layout.ac_add_alert,null);
        mPopupWindow = new PopupWindow(popupView, 400, LinearLayout
                .LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mLaunchGroupChat=popupView.findViewById(R.id.launch_group_chat);
        mSwipe=popupView.findViewById(R.id.circle_find_swipe);
        mSwipe.setOnClickListener(this);
        mLaunchGroupChat.setOnClickListener(this);
    }

    //获取状态栏高度并赋颜色
    private void immerseStatus() {
        mStatusColor = f(R.id.fr_find_status_color);
        ViewGroup.LayoutParams params = mStatusColor.getLayoutParams();
        params.height = ScreenSize.getStatusBarHeight(mContext);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_title_screen:

                break;
            case R.id.find_title_add:
                int[] location=new int[2];
                view.getLocationInWindow(location);
                mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0]-mPopupWindow.getWidth(),location[1]+100);
                break;
            case R.id.launch_group_chat:
                Toast.makeText(mContext,"发起群聊",Toast.LENGTH_SHORT).show();
                break;
            case R.id.circle_find_swipe:
                intent=new Intent(mContext, CaptureActivity.class);
                mContext.startActivity(intent);
                break;
            default:
                break;
        }
    }

    /*
     监听左右滑动
      */
    private void moPageChange() {
        mPages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton button = (RadioButton) mTitleLable.getChildAt(position);
                button.setChecked(true);
                /*
                改变线的位置
                 */
                if (mPosition == 0 && position == 1) {
                    runAnimation(mScreenWidth / 6 * 0, mScreenWidth / 6 * 1);
                } else if (mPosition == 1 && position == 2) {
                    runAnimation(mScreenWidth / 6 * 2, mScreenWidth / 6 * 3);
                } else if (mPosition == 2 && position == 3) {
                    runAnimation(mScreenWidth / 6 * 3, mScreenWidth / 6 * 4);
                } else if (mPosition == 3 && position == 2) {
                    runAnimation(mScreenWidth / 6 * 4, mScreenWidth / 6 * 3);
                } else if (mPosition == 2 && position == 1) {
                    runAnimation(mScreenWidth / 6 * 3, mScreenWidth / 6 * 2);
                } else if (mPosition == 1 && position == 0) {
                    runAnimation(mScreenWidth / 6 * 2, mScreenWidth / 6 * 1);
                } else if (mPosition == 0 && position == 2) {
                    runAnimation(mScreenWidth / 6 * 1, mScreenWidth / 6 * 3);
                } else if (mPosition == 1 && position == 3) {
                    runAnimation(mScreenWidth / 6 * 3, mScreenWidth / 6 * 4);
                } else if (mPosition == 3 && position == 1) {
                    runAnimation(mScreenWidth / 6 * 4, mScreenWidth / 6 * 2);
                } else if (mPosition == 2 && position == 0) {
                    runAnimation(mScreenWidth / 6 * 3, mScreenWidth / 6 * 1);
                } else if (mPosition == 0 && position == 3) {
                    runAnimation(mScreenWidth / 6 * 1, mScreenWidth / 6 * 4);
                } else if (mPosition == 3 && position == 0) {
                    runAnimation(mScreenWidth / 6 * 4, mScreenWidth / 6 * 1);
                }
                mPosition = position;
                Log.v("srsj", mPosition + "  " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void runAnimation(int start, int end) {
        Animation animation = new TranslateAnimation(start, end, 0, 0);
        animation.setFillAfter(true);// 保持位置
        animation.setDuration(300);// 动画时间
//        mLine.startAnimation(animation);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        manager = getChildFragmentManager();
        transaction = manager.beginTransaction();
        switch (id) {
            case R.id.find_title_person:
                mPages.setCurrentItem(0);
                break;
            case R.id.find_title_group:
                mPages.setCurrentItem(1);
                break;
            case R.id.find__title_dynamic:
                mPages.setCurrentItem(2);
                break;
            case R.id.find_title_merchant:
                mPages.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    /*
        以下为初始化页面作用
         */
    private void initFragment() {
        beans.clear();
        beans.add(new FindPersonFragment());
        beans.add(new FindGroupFragment());
        beans.add(new FindDynamicFragment());
        beans.add(new FindMerchantFragment());
        mAdapter.setBeans(beans);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        mTvTitleAdd.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onResume() {
        super.onResume();
        mTvTitleAdd.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
    }

    public <T extends View> T f(int id) {
        return (T) view.findViewById(id);
    }
}

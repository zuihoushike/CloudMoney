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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import name.hd.cloudmoney.R;
import name.hd.cloudmoney.adapter.CircleFragmentAdapter;
import name.hd.cloudmoney.swipe.CaptureActivity;
import name.hd.cloudmoney.tools.ScreenSize;

public class CircleFragment extends Fragment implements RadioGroup.OnCheckedChangeListener,View.OnClickListener {
    private PopupWindow mPopupWindow;
    private Intent intent;
    private Activity context;
    private View view,popupView,mStatusColor;
    private TextView mTvTitleAdd,mLaunchGroupChat,mSwipe;
    private int mScreenWidth = 0, mPosition = 0, start = 0;//屏幕宽度，存储position，起始位置
    private RadioGroup mTitleLable;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    /*
    三要素
     */
    private ViewPager mPages;
    private List<Fragment> beans = new ArrayList<>();
    private CircleFragmentAdapter mAdapter;

    public CircleFragment() {
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
        view=inflater.inflate(R.layout.fragment_circle, container, false);

        immerseStatus();
        init();
        monitor();
        alertWindow();
        mPages.setAdapter(mAdapter);
        initFragment();
        moPageChange();
        //获取控件宽度
        mScreenWidth = mTitleLable.getMeasuredWidth();
        //给线条设置宽、高
//        mLine.setLayoutParams(new RelativeLayout.LayoutParams(mScreenWidth, 7));
        return view;
    }
    private void init() {
        mTitleLable = f(R.id.circle_title_lable);
        mPages = f(R.id.circle_pages);
//        mLine = f(R.id.circle_slide_line);
        mTvTitleAdd=f(R.id.circle_title_add);
        mAdapter = new CircleFragmentAdapter(getChildFragmentManager());
    }
    private void monitor() {
        mTitleLable.setOnCheckedChangeListener(this);
        mTvTitleAdd.setOnClickListener(this);
    }
    //获取状态栏高度并赋颜色
    public void immerseStatus() {
        mStatusColor = f(R.id.fr_circle_status_color);
        ViewGroup.LayoutParams params = mStatusColor.getLayoutParams();
        params.height = ScreenSize.getStatusBarHeight(context);
    }
    /*
   PopupWindow
    */
    private void alertWindow() {
        popupView = LayoutInflater.from(context).inflate(R.layout.ac_add_alert,null);
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
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.circle_title_add:
                int[] location=new int[2];
                view.getLocationInWindow(location);
//                mPopupWindow.showAtLocation(view, Gravity.TOP|Gravity.RIGHT,100,200);
                mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0]-mPopupWindow.getWidth(),location[1]+100);
                break;
            case R.id.launch_group_chat:
                Toast.makeText(context,"发起群聊",Toast.LENGTH_SHORT).show();
                break;
            case R.id.circle_find_swipe:
                intent=new Intent(context, CaptureActivity.class);
                context.startActivity(intent);
                break;
            default:
                break;
        }
    }
    /*
    监听左右滑动
     */
    private void moPageChange(){
        mPages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton button= (RadioButton) mTitleLable.getChildAt(position);
                button.setChecked(true);
                /*
                改变线的位置
                 */
                if (mPosition==0&&position==1){
                    runAnimation(mScreenWidth/4*0,mScreenWidth/4*1);
                }else if(mPosition==1&&position==2){
                    runAnimation(mScreenWidth/4*2,mScreenWidth/4*3);
                }else if(mPosition==2&&position==1){
                    runAnimation(mScreenWidth/4*3,mScreenWidth/4*2);
                }else if (mPosition==1&&position==0){
                    runAnimation(mScreenWidth/4*2,mScreenWidth/4*1);
                }else if (mPosition==0&&position==2){
                    runAnimation(mScreenWidth/4*1,mScreenWidth/4*3);
                }else if (mPosition==2&&position==0){
                    runAnimation(mScreenWidth/4*3,mScreenWidth/4*1);
                }
                mPosition=position;
                Log.v("srsj",mPosition+"  "+position);
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
            case R.id.circle_title_msg:
                mPages.setCurrentItem(0);
                break;
            case R.id.circle_title_record:
                mPages.setCurrentItem(1);
                break;
            case R.id.circle_title_circle:
                mPages.setCurrentItem(2);
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
        beans.add(new CircleMsgFragment());
        beans.add(new CircleRecordFragment());
        beans.add(new CircleCircleFragment());
        mAdapter.setBeans(beans);
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onPause() {
        super.onPause();
        mTvTitleAdd.setTextColor(context.getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onResume() {
        super.onResume();
        mTvTitleAdd.setTextColor(context.getResources().getColor(R.color.colorBlack));
    }
    public <T extends View> T f(int id) {
        return (T) view.findViewById(id);
    }


}

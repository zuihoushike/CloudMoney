package name.hd.cloudmoney.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import name.hd.cloudmoney.swipe.CaptureActivity;
import name.hd.cloudmoney.CloudBussActivity;
import name.hd.cloudmoney.R;
import name.hd.cloudmoney.SearchActivity;
import name.hd.cloudmoney.adapter.HomeNearbyMerchantAdapter;
import name.hd.cloudmoney.bean.Merchant;
import name.hd.cloudmoney.jar.AutoPlayViewPager;
import name.hd.cloudmoney.jar.BannerAdapter;
import name.hd.cloudmoney.tools.ScreenSize;
import name.hd.cloudmoney.view.MyView;
import name.hd.cloudmoney.view.NoRollListView;
import name.hd.cloudmoney.view.SmartScrollView;

public class HomeFragment extends Fragment implements View.OnClickListener, ViewPager
        .OnPageChangeListener{
    private AutoPlayViewPager pager;//三要素之视图
    //三要素之数据源
    private List<View> mViews = new ArrayList<>();
    private int[] imageIds = {R.mipmap.index_banner, R.mipmap.index_banner, R.mipmap.index_banner};
    //三要素之适配器
    private BannerAdapter<View> bannerAdapter;
    private LinearLayout mHomePoints;//用来显示小圆点的容器
    //将生成的小圆点缓存起来，后面用
    private List<View> mPointView = new ArrayList<>();
    private View view, mStatusColor, mStatusGoneColor;
    private Activity mContext;
    private Intent intent;
    private ImageView mSearchBox, mSearchIcon,mSearchGoneBox,mSearchGoneIcon, mSwipe,mGoneSwipe;
    private TextView mGoodsOriginalPrice, mHomeCloud, mSearchText,mSearchGoneText;
    private SmartScrollView mSvMo;
    private RelativeLayout mFixedTitle, mActiveTitle;
    private int mTouchShop;//最小滑动距离
    protected float mFirstY;//触摸下去的位置
    protected float mCurrentY;//滑动时Y的位置
    protected int direction;//判断是否上滑或者下滑的标志
    protected boolean mShow;//判断是否执行了上滑动画
    /*
    商家类别切换
     */
    private RadioGroup mNearbySort;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    /*
    商家列表
     */
    private NoRollListView mNearbyList;
    private List<Merchant> beans = new ArrayList<>();
    private HomeNearbyMerchantAdapter mAdapter;

    public HomeFragment() {
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        immerseStatus();
        immerseGoneStatus();
        init();
        monitor();
        bannerLoopPlay();
        initMerchantList();
        mAdapter = new HomeNearbyMerchantAdapter(mContext, beans);
        mNearbyList.setAdapter(mAdapter);
        return view;
    }
    private void init() {
        mHomeCloud = f(R.id.tv_frag_home_cloud);
        pager = f(R.id.home_banner);
        mHomePoints = f(R.id.home_points);
        mNearbySort = f(R.id.home_nearby_sort);
        mNearbyList = f(R.id.home_nearby_list);
        mSearchBox = f(R.id.home_search_box);
        mSearchIcon = f(R.id.home_search_icon);
        mSearchText = f(R.id.home_search_text);
        mSearchGoneBox=f(R.id.home_search_gone_box);
        mSearchGoneIcon=f(R.id.home_search_gone_icon);
        mSearchGoneText=f(R.id.home_search_gone_text);
        mSwipe = f(R.id.home_swipe);
        mGoneSwipe=f(R.id.home_gone_swipe);
        mGoodsOriginalPrice = f(R.id.home_goods_original_price);
        mStatusGoneColor = f(R.id.fr_home_status_gone_color);
        mSvMo = f(R.id.sv_monitor);
        mFixedTitle = f(R.id.home_fixed_title);
        mActiveTitle = f(R.id.home_active_title);
    }

    private void monitor() {
        slideUpDown();
        mHomeCloud.setOnClickListener(this);
        pager.setOnPageChangeListener(this);
        nearbyMerchant();
        mSearchText.setOnClickListener(this);
        mSearchIcon.setOnClickListener(this);
        mSearchBox.setOnClickListener(this);
        mSearchGoneBox.setOnClickListener(this);
        mSearchGoneIcon.setOnClickListener(this);
        mSearchGoneText.setOnClickListener(this);
        mSwipe.setOnClickListener(this);
        mGoneSwipe.setOnClickListener(this);
        mGoodsOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//给文字加删除线
        mFixedTitle.setVisibility(View.GONE);
        mActiveTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_frag_home_cloud:
                intent = new Intent(mContext, CloudBussActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.home_search_box:
                toSearchActivity();
                break;
            case R.id.home_search_text:
                toSearchActivity();
                break;
            case R.id.home_search_icon:
                toSearchActivity();
                break;
            case R.id.home_search_gone_box:
                toSearchActivity();
                break;
            case R.id.home_search_gone_text:
                toSearchActivity();
                break;
            case R.id.home_search_gone_icon:
                toSearchActivity();
                break;
            case R.id.home_swipe:
                intent = new Intent(mContext, CaptureActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.home_gone_swipe:
                intent = new Intent(mContext, CaptureActivity.class);
                mContext.startActivity(intent);
                break;
            default:
                break;
        }
    }
    private void nearbyMerchant() {
        mNearbySort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                manager = getChildFragmentManager();
                transaction = manager.beginTransaction();
//                switch (id){
//                    case R.id.home_nearby_entity:
//                        transaction.replace(R.id.home_nearby_list,new HomeEntityFragment());
//                        break;
//                    case R.id.home_nearby_personal:
//                        transaction.replace(R.id.home_nearby_list,new HomePersonalFragment());
//                        break;
//                    case R.id.home_nearby_factory:
//                        transaction.replace(R.id.home_nearby_list,new HomeFactoryFragment());
//                        break;
//                    default:
//                        break;
//                }
                transaction.commit();
            }
        });
//        manager=getChildFragmentManager();
//        transaction=manager.beginTransaction();
//        transaction.replace(R.id.home_nearby_list,this);
//        transaction.commit();
    }

    //获取状态栏高度并赋颜色
    public void immerseStatus() {
        mStatusColor = f(R.id.fr_home_status_color);
        ViewGroup.LayoutParams params = mStatusColor.getLayoutParams();
        params.height = ScreenSize.getStatusBarHeight(mContext);
    }

    //获取状态栏高度并赋颜色
    private void immerseGoneStatus() {
        mStatusGoneColor = f(R.id.fr_home_status_gone_color);
        ViewGroup.LayoutParams params = mStatusGoneColor.getLayoutParams();
        params.height = ScreenSize.getStatusBarHeight(mContext);
    }

    /*
   初始化附近商家
    */
    private void initMerchantList() {
        for (int i = 0; i < 3; i++) {
            Merchant merchant = new Merchant();
            merchant.setIcon(R.mipmap.kfc);
            merchant.setName("肯德基钟楼店");
            merchant.setInfo("提供免费wifi 现推出精品4人豪华套餐 周末提供休息场所");
            merchant.setPrice(56.00);
            merchant.setDistance(11.80);
            merchant.setSelled(741);
            beans.add(merchant);
        }
    }

    /*
    轮播主页banner图
     */
    private void bannerLoopPlay() {
        initData();
        initPoint();
        bannerAdapter = new BannerAdapter<>(mContext);
        bannerAdapter.update(mViews);
        pager.setAdapter(bannerAdapter);

        pager.setDirection(AutoPlayViewPager.Direction.LEFT);// 设置播放方向
        pager.setCurrentItem(2000); // 设置每个Item展示的时间
        pager.start(); // 开始轮播
    }

    //首页banner初始化数据源
    private void initData() {
        for (int i = 0; i < imageIds.length; i++) {
            mViews.add(new MyView(mContext, imageIds[i], imageIds, i).view);
        }

    }

    /*
       上下滑动
        */
    private void slideUpDown() {
        mTouchShop = ViewConfiguration.get(mContext).getScaledTouchSlop();//系统级别的一个属性,判断用户的最小滑动距离的,
        mSvMo.setOnTouchListener(new View.OnTouchListener() {
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
                                mFixedTitle.setVisibility(View.VISIBLE);
                                mActiveTitle.setVisibility(View.GONE);
                                mShow = !mShow;
                            }
                        } else if (mSvMo.isScrolledToTop()||(direction == 0&&mSvMo.isScrolledToTop())) {//判断如果是向下滑动 则执行向下滑动的动画
                            if (!mShow) {//判断动画是否执行了  执行了则改变状态
                                //执行往下滑动的动画
//                                tolbarAnim(0);
                                mFixedTitle.setVisibility(View.GONE);
                                mActiveTitle.setVisibility(View.VISIBLE);
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

    private void toSearchActivity() {
        intent = new Intent(mContext, SearchActivity.class);
        startActivity(intent);
    }

    //实例化小圆点
    private void initPoint() {
        if (mViews != null) {
            for (int i = 0; i < mViews.size(); i++) {//确定小圆点的数量
                View view = new View(mContext);
                LinearLayout.LayoutParams params =
                        new LinearLayout.LayoutParams(15, 15);
                params.setMargins(5, 5, 5, 5);//外边距
                view.setLayoutParams(params);
                if (i == 1) {//默认第一个选中
                    view.setBackgroundResource(R.drawable.point_on);
                } else {//未选中
                    view.setBackgroundResource(R.drawable.point_off);
                }
                mHomePoints.addView(view);//将动态生成的组件放入到容器中
                mPointView.add(view);//缓存小圆点
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mPointView != null) {
            for (int i = 0; i < mPointView.size(); i++) {
                if (i == position % mPointView.size()) {//选中
                    mPointView.get(i).setBackgroundResource(R.drawable.point_on);
                } else {//未选中
                    mPointView.get(i).setBackgroundResource(R.drawable.point_off);
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public <T extends View> T f(int id) {
        return (T) view.findViewById(id);
    }
}

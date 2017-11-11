package name.hd.cloudmoney;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import name.hd.cloudmoney.tools.ScreenSize;
import name.hd.cloudmoney.tools.Status;
import name.hd.cloudmoney.view.SmartScrollView;

public class CloudBussActivity extends Activity implements OnClickListener {
    private View mStatusColor,mStatusGoneColor;
    private ImageView mNavBack, mIvSearch, mIvSearchbox, mNavShare, mIvSellAll, mSearchbox,mSearchIcon,mGoneShare,mGoneBack;
    private LinearLayout mActiveTitle;
    private RelativeLayout mFixedTitle;
    private SmartScrollView mSvMain;
    private TextView mShopData, mGoodsManager, mOrderManager, mBussinessStat, mCustomStat,
            mShopManager, mMarketingManager, mShopAngel, mShopChain, mWillPay, mWillDeliver,
            mWillVerify, mWillEval, mSellAfter, mSellAll,mSearchText;
    Intent intent;
    private int mTouchShop;//最小滑动距离
    protected float mFirstY;//触摸下去的位置
    protected float mCurrentY;//滑动时Y的位置
    protected int direction;//判断是否上滑或者下滑的标志
    protected boolean mShow;//判断是否执行了上滑动画
    private Animator mAnimator;//动画属性

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_buss);
        Status.setStatusTextColor(this);
        immerseStatus();
        immerseGoneStatus();
        changeTransparent();
        init();
        monitor();
    }
    /*
    初始化
    */
    private void init() {
        mSvMain = f(R.id.sv_main);
        mNavBack = f(R.id.nav_back);
        mIvSearch = f(R.id.iv_search);
        mIvSearchbox = f(R.id.iv_searchbox);
        mNavShare = f(R.id.nav_share);
        mShopData = f(R.id.main_shop_data);
        mGoodsManager = f(R.id.main_goods_manager);
        mOrderManager = f(R.id.main_order_manager);
        mBussinessStat = f(R.id.main_bussiness_stat);
        mCustomStat = f(R.id.main_custom_stat);
        mShopManager = f(R.id.main_shop_manager);
        mMarketingManager = f(R.id.main_marketing_manager);
        mShopAngel = f(R.id.main_shop_angel);
        mShopChain = f(R.id.main_shop_chain);
        mWillPay = f(R.id.main_willpay);
        mWillDeliver = f(R.id.main_willdeliver);
        mWillEval = f(R.id.main_willeval);
        mWillVerify = f(R.id.main_willverify);
        mSellAfter = f(R.id.main_sellafter);
        mSellAll = f(R.id.sell_all);
        mIvSellAll = f(R.id.iv_sell_all);
        mFixedTitle=f(R.id.cloud_fixed_title);
        mStatusGoneColor=f(R.id.cloud_status_gone_color);
        mActiveTitle=f(R.id.cloud_active_title);
        mSearchbox=f(R.id.cloud_search_box);
        mSearchIcon=f(R.id.cloud_search_icon);
        mSearchText=f(R.id.cloud_search_text);
        mGoneShare=f(R.id.cloud_share);
        mGoneBack=f(R.id.cloud_back);
    }
    /*
     监听
    */
    private void monitor() {
        slideUpDown();
        mIvSearch.setOnClickListener(this);
        mIvSearchbox.setOnClickListener(this);
        mNavBack.setOnClickListener(this);
        mNavShare.setOnClickListener(this);
        mShopData.setOnClickListener(this);
        mGoodsManager.setOnClickListener(this);
        mOrderManager.setOnClickListener(this);
        mBussinessStat.setOnClickListener(this);
        mCustomStat.setOnClickListener(this);
        mShopManager.setOnClickListener(this);
        mMarketingManager.setOnClickListener(this);
        mShopAngel.setOnClickListener(this);
        mShopChain.setOnClickListener(this);
        mWillPay.setOnClickListener(this);
        mWillVerify.setOnClickListener(this);
        mWillEval.setOnClickListener(this);
        mWillDeliver.setOnClickListener(this);
        mSellAfter.setOnClickListener(this);
        mSellAll.setOnClickListener(this);
        mIvSellAll.setOnClickListener(this);
        mSearchbox.setOnClickListener(this);
        mSearchIcon.setOnClickListener(this);
        mSearchText.setOnClickListener(this);
        mGoneShare.setOnClickListener(this);
        mGoneBack.setOnClickListener(this);
        mFixedTitle.setVisibility(View.GONE);
        mActiveTitle.setVisibility(View.VISIBLE);
    }

    /*
    通知栏变透明
     */
    private void changeTransparent() {
        //判断当前设备版本号是否为4.4以上，如果是，则通过调用setTranslucentStatus让状态栏变透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Status.setTranslucentStatus(this, true);
        }
    }

    //获取状态栏高度并赋颜色
    private void immerseStatus() {
        mStatusColor = f(R.id.cloud_status_color);
        ViewGroup.LayoutParams params = mStatusColor.getLayoutParams();
        params.height = ScreenSize.getStatusBarHeight(this);
    }
    //获取状态栏高度并赋颜色
    private void immerseGoneStatus() {
        mStatusGoneColor = f(R.id.cloud_status_gone_color);
        ViewGroup.LayoutParams params = mStatusGoneColor.getLayoutParams();
        params.height = ScreenSize.getStatusBarHeight(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_back://返回
                finish();
                break;
            case R.id.cloud_back:
                finish();
                break;
            case R.id.iv_search://跳转搜索页
                toSearchPage();
                break;
            case R.id.iv_searchbox://跳转搜索页
                toSearchPage();
                break;
            case R.id.cloud_search_box:
                toSearchPage();
                break;
            case R.id.cloud_search_icon:
                toSearchPage();
                break;
            case R.id.cloud_search_text:
                toSearchPage();
                break;
            case R.id.nav_share://分享
                /*暂且先用文件代替*/
                File file = new File("/mnt/sdcard/blink.log");
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                intent.setType("*/*");
                startActivity(intent);
                break;
            case R.id.cloud_share:
                 /*暂且先用文件代替*/
                File file1 = new File("/mnt/sdcard/blink.log");
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file1));
                intent.setType("*/*");
                startActivity(intent);
                break;
            case R.id.main_shop_data:
                Toast.makeText(this, "店铺资料", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_goods_manager:
//                Toast.makeText(this, "商品管理", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, GoodsManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.main_order_manager:
                Toast.makeText(this, "订单管理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_bussiness_stat:
                Toast.makeText(this, "营业统计", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_custom_stat:
                Toast.makeText(this, "定制/直批", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_shop_manager:
                Toast.makeText(this, "客户管理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_marketing_manager:
//                Toast.makeText(this, "营销管理", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, MarketingManagerActivity.class);
                Log.d("srsj", intent.toString());
                startActivity(intent);
                break;
            case R.id.main_shop_angel:
                Toast.makeText(this, "店铺天使", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_shop_chain:
                Toast.makeText(this, "门店连锁", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_willpay:
                Toast.makeText(this, "待付款", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_willdeliver:
                Toast.makeText(this, "待发货", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_willverify:
                Toast.makeText(this, "待确认收货", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_willeval:
                Toast.makeText(this, "待评价", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_sellafter:
                Toast.makeText(this, "售后", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sell_all:
                Toast.makeText(this, "全部订单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_sell_all:
                Toast.makeText(this, "全部订单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_shop_data_right:
                Toast.makeText(this, "店铺实时数据", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

    /*
   上下滑动
    */
    private void slideUpDown() {
        mTouchShop = ViewConfiguration.get(this).getScaledTouchSlop();//系统级别的一个属性,判断用户的最小滑动距离的,
        mSvMain.setOnTouchListener(new View.OnTouchListener() {
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
                        } else if (mSvMain.isScrolledToTop()||(direction == 0&&mSvMain.isScrolledToTop())) {//判断如果是向下滑动且到顶部 则执行向下滑动的动画
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

    /*
    启动标题动画
     */
    /*private void tolbarAnim(int flag) {
        if (mAnimator != null && mAnimator.isRunning()) {//判断动画存在  如果启动了,则先关闭
            mAnimator.cancel();
        }
        if (flag == 0) {
            mAnimator = ObjectAnimator.ofFloat(mFixedTitle, "translationY", mMainTitle
                            .getTranslationY(),
                    0);//从当前位置位移到0位置
//            mAnimation= AnimationUtils.loadAnimation(this,R.anim.push_top_out);
        } else {
            mAnimator = ObjectAnimator.ofFloat(mFixedTitle, "translationY", mMainTitle
                            .getTranslationY(),
                    -mMainTitle.getHeight());//从当前位置移动到布局负高度的wiz
//            mAnimation=AnimationUtils.loadAnimation(this,R.anim.push_top_in);
        }
        mAnimator.start();//执行动画

    }*/
    private void toSearchPage() {
        intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public <T extends View> T f(int id) {
        return (T) findViewById(id);
    }

}

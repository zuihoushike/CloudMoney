package name.hd.cloudmoney;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import name.hd.cloudmoney.adapter.GoodsManagerAdapter;
import name.hd.cloudmoney.bean.Goods;
import name.hd.cloudmoney.db.StateDB;

public class GoodsManagerActivity extends Activity implements View.OnClickListener {
    public int n = 0;
    private PopupWindow mPopupWindow;
    private View popupView;
    StateDB state=new StateDB();
    private ImageView mGoodsBack, mIvGoodsSearch1, mIvGoodsSearch2;
    private TextView mTvSearch;
    private RadioButton  mTvSelect,mTvClassify,mTvSort,mTvScreen;
    private Intent intent;
    /*三大组件*/
    private ListView mListView;
    private List<Goods> beans = new ArrayList<>();
    private GoodsManagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_manager);
        init();
        monitor();
        alertWindow();
        initData();
        adapter = new GoodsManagerAdapter(this, beans);
        mListView.setAdapter(adapter);
    }
    private void alertWindow() {
        popupView = LayoutInflater.from(this).inflate(R.layout.goods_sort,null);
        mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout
                .LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
    }

    private void init() {
        mListView = f(R.id.lv_goods_manager);
        mGoodsBack = f(R.id.goods_back);
        mIvGoodsSearch1 = f(R.id.iv_goods_search1);
        mIvGoodsSearch2 = f(R.id.iv_goods_search2);
        mTvSearch = f(R.id.tv_goods_search);
        mTvSelect = f(R.id.goods_select);
        mTvClassify=f(R.id.goods_classify);
        mTvSort=f(R.id.goods_sort);
        mTvScreen=f(R.id.goods_screen);
//        state.add(this,mTvSelect.getText().toString().trim());
    }

    private void monitor() {
        mGoodsBack.setOnClickListener(this);
        mIvGoodsSearch1.setOnClickListener(this);
        mIvGoodsSearch2.setOnClickListener(this);
        mTvSearch.setOnClickListener(this);
        mTvSelect.setOnClickListener(this);
        mTvClassify.setOnClickListener(this);
        mTvSort.setOnClickListener(this);
        mTvScreen.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goods_back:
                finish();
                break;
            case R.id.iv_goods_search1:
                toSearchAct();
                break;
            case R.id.iv_goods_search2:
                toSearchAct();
                break;
            case R.id.tv_goods_search:
                toSearchAct();
                break;
            case R.id.goods_classify:
                intent=new Intent(this,GoodsClassifyActivity.class);
                startActivity(intent);
                break;
            case R.id.goods_sort:
                mPopupWindow.showAsDropDown(v);
                break;
            case R.id.goods_screen:
                intent=new Intent(this,GoodsScreenActivity.class);
                startActivity(intent);
                break;
            case R.id.goods_select:
                /*if (n%2==0){
                    Log.e("srsj","n："+n);
                    state.change(this,mTvSelect.getText().toString().trim(),0);
                    n++;
                }else {
                    Log.e("srsj","n："+n);
                    state.change(this,mTvSelect.getText().toString().trim(),1);
                    n++;
                }*/
                n++;
                break;
            default:
                break;
        }
    }

    private void initData() {
        for (int i = 0; i < 6; i++) {
            Goods good = new Goods();
            if (i % 2 == 0) {
                good.setState("上架");

            } else {
                good.setState("下架");
            }
            good.setIcon(R.mipmap.home_female_pic);
            good.setName("飞行装女士最新款春装");
            good.setScanNum(60);
            good.setSelledNum(50);
            good.setPrice(100.00);
            beans.add(good);
        }
    }

    private void toSearchAct() {
        intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public <T extends View> T f(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

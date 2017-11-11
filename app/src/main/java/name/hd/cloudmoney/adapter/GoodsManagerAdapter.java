package name.hd.cloudmoney.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import name.hd.cloudmoney.GoodsInfoActivity;
import name.hd.cloudmoney.GoodsManagerActivity;
import name.hd.cloudmoney.R;
import name.hd.cloudmoney.bean.Goods;
import name.hd.cloudmoney.db.StateDB;

/**
 * Created by AkiNobunaga on 2017/10/26.
 */

public class GoodsManagerAdapter extends BaseAdapter {
    int n = 0;
    private Context mContext;
    private List<Goods> goods;
    private LayoutInflater mInflater;
    private GoodsManagerActivity mActivity = new GoodsManagerActivity();

    public GoodsManagerAdapter(Context mContext, List<Goods> goods) {
        this.mContext = mContext;
        this.goods = goods;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public int getCount() {
        return goods != null ? goods.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return goods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.goods_list, null);
            holder = new ViewHolder();
            holder.goodsIcon = convertView.findViewById(R.id.goods_icon);
            holder.goodsName = convertView.findViewById(R.id.goods_name);
            holder.scanNum = convertView.findViewById(R.id.goods_scan_num);
            holder.selledNum = convertView.findViewById(R.id.goods_selled_num);
            holder.state = convertView.findViewById(R.id.goods_state);
            holder.price = convertView.findViewById(R.id.goods_price);
            holder.mCbSelect = convertView.findViewById(R.id.cb_goods_select);
            holder.mGoodsInfo=convertView.findViewById(R.id.goods_info);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        StateDB state = new StateDB();
        holder.mGoodsInfo.setOnClickListener(new MyOnclick(i,holder));

        holder.goodsIcon.setImageResource(R.mipmap.home_female_pic);
        holder.goodsName.setText(goods.get(i).getName());
        holder.scanNum.setText("浏览" + goods.get(i).getScanNum());
        holder.selledNum.setText("已售" + goods.get(i).getSelledNum());
//        holder.state.setText(goods.get(i).getState());
        holder.price.setText("￥" + goods.get(i).getPrice());
        if (mActivity.n % 2 == 1) {
            holder.mCbSelect.setVisibility(View.VISIBLE);
        } else if (mActivity.n % 2 == 0) {
            holder.mCbSelect.setVisibility(View.GONE);
        }
        /*int st = state.get(mContext);
        Log.e("srsj",st+"哈哈哈哈");
        if (st == 1) {
            holder.mCbSelect.setVisibility(View.VISIBLE);

        } else {
            holder.mCbSelect.setVisibility(View.GONE);
        }*/
        return convertView;
    }

    class ViewHolder {
        ImageView goodsIcon;
        TextView goodsName, scanNum, selledNum, state, price;
        CheckBox mCbSelect;
        RelativeLayout mGoodsInfo;
    }
    class MyOnclick implements View.OnClickListener{
        private int mPosition;
        private ViewHolder mHolder;
        public MyOnclick(int position,ViewHolder holder){
            this.mPosition=position;
            this.mHolder=holder;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.goods_info:
                    Intent intent=new Intent(mContext, GoodsInfoActivity.class);
                    mContext.startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }
}

package name.hd.cloudmoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import name.hd.cloudmoney.R;
import name.hd.cloudmoney.bean.Merchant;

/**
 * Created by AkiRuka on 2017/9/21.
 */

public class HomeNearbyMerchantAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Merchant> beans;

    public HomeNearbyMerchantAdapter(Context context, List<Merchant> merchants) {
        this.mContext = context;
        this.beans = merchants;
        mInflater=LayoutInflater.from(mContext);
    }

    public void setMerchants(List<Merchant> merchants) {
        this.beans = merchants;
    }

    @Override
    public int getCount() {
        return beans!=null?beans.size():0;
    }

    @Override
    public Object getItem(int i) {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            view=mInflater.inflate(R.layout.fr_home_nearby_merchant_item,null);
            holder=new ViewHolder();
            holder.icon=view.findViewById(R.id.home_nearby_merchant_icon);
            holder.name=view.findViewById(R.id.home_nearby_merchant_name);
            holder.info=view.findViewById(R.id.home_nearby_merchant_info);
            holder.price=view.findViewById(R.id.home_nearby_merchant_price);
            holder.distance=view.findViewById(R.id.home_nearby_merchant_distance);
            holder.selled=view.findViewById(R.id.home_nearby_merchant_selled);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();

        }
        holder.icon.setImageResource(beans.get(i).getIcon());
        holder.name.setText(beans.get(i).getName());
        holder.info.setText(beans.get(i).getInfo());
        holder.price.setText("人均消费 ￥"+beans.get(i).getPrice());
        holder.distance.setText(beans.get(i).getDistance()+"km");
        holder.selled.setText("已售"+beans.get(i).getSelled());
        return view;
    }
    class ViewHolder{
        ImageView icon;
        TextView name,info,price,distance,selled;
    }
}

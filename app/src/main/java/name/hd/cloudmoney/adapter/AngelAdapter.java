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
import name.hd.cloudmoney.bean.Angel;
import name.hd.cloudmoney.custom.FormatDouble;

/**
 * Created by AkiNobunaga on 2017/11/13.
 */

public class AngelAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Angel> beans;

    public AngelAdapter(Context context, List<Angel> beans) {
        this.mContext = context;
        this.beans = beans;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return beans!=null?beans.size():0;
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.angel_list_item,null);
            holder=new ViewHolder();
            holder.icon=convertView.findViewById(R.id.angel_icon);
            holder.nickname=convertView.findViewById(R.id.angel_nickname);
            holder.date=convertView.findViewById(R.id.angel_date);
            holder.turnover=convertView.findViewById(R.id.angel_turnover);
            holder.subCommission=convertView.findViewById(R.id.angel_sub_commission);
            holder.commission=convertView.findViewById(R.id.angel_commission);
            holder.orderNum=convertView.findViewById(R.id.angel_order_num);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.icon.setImageResource(beans.get(position).getIcon());
        holder.nickname.setText(beans.get(position).getNickname());
        holder.date.setText("时间："+beans.get(position).getDate());
        holder.turnover.setText(FormatDouble.f2(beans.get(position).getTurnover())+"");
        holder.subCommission.setText("分佣"+beans.get(position).getSubCommission()+"‰");
        holder.commission.setText("佣金￥"+FormatDouble.f2(beans.get(position).getCommission()));
        holder.orderNum.setText("订单 "+beans.get(position).getOrderNum()+"单");
        return convertView;
    }
    class ViewHolder{
        ImageView icon;
        TextView nickname,date,turnover,subCommission,commission,orderNum;
    }
}

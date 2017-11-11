
package name.hd.cloudmoney.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import name.hd.cloudmoney.R;


/**
 * �Զ����View
  *
 */
public class MyView {
   public View view;
   private RelativeLayout layout;
   private Button button;
   public MyView(final Context context,int imgId,int data[],int positon){
	   view = LayoutInflater.from(context).inflate(
			   R.layout.home_viewpager_item, null);
	   layout =  view.findViewById(R.id.rl);
	   layout.setBackgroundResource(imgId);
	   //以下是用来显示隐藏banner上控件的
	   /*button = (Button) view.findViewById(R.id.button1);
	   if((data.length-1)==positon){
		   button.setVisibility(View.VISIBLE);//��ʾ
	   }else{
		   button.setVisibility(View.GONE);//����
	   }*/
	}
	   
   }

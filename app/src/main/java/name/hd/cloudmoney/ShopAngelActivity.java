package name.hd.cloudmoney;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import name.hd.cloudmoney.adapter.AngelAdapter;
import name.hd.cloudmoney.bean.Angel;
import name.hd.cloudmoney.tools.ScreenSize;
import name.hd.cloudmoney.tools.Status;
import name.hd.cloudmoney.view.NoRollListView;

public class ShopAngelActivity extends Activity implements View.OnClickListener {
    private View mStatusColor;
    private ImageView mBack;
    /*
    三要素
     */
    private NoRollListView mList;
    private List<Angel>  beans=new ArrayList<>();
    private AngelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_angel);
        changeTransparent();
        immerseStatus();
        init();
        monitor();
        initData();
        adapter=new AngelAdapter(this,beans);
        mList.setAdapter(adapter);
    }
    private void init(){
        mBack=f(R.id.angel_back);
        mList=f(R.id.angel_list);
    }
    private void monitor(){
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.angel_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void initData(){
        for (int i=0;i<3;i++){
            Angel angel=new Angel();
            angel.setIcon(R.mipmap.xx1);
            angel.setNickname("木木");
            angel.setDate("2017-02-02");
            angel.setTurnover(500.00);
            angel.setSubCommission(10);
            angel.setCommission(10.00);
            angel.setOrderNum(5);
            beans.add(angel);
        }
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
        mStatusColor = f(R.id.angel_status_color);
        ViewGroup.LayoutParams params = mStatusColor.getLayoutParams();
        params.height = ScreenSize.getStatusBarHeight(this);
    }
    public <T extends View> T f(int id) {
        return (T) findViewById(id);
    }
}

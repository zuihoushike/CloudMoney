package name.hd.cloudmoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import name.hd.cloudmoney.tools.ScreenSize;
import name.hd.cloudmoney.tools.Status;
import name.hd.cloudmoney.uploadImg.ChooseActivity;

public class GoodsInfoActivity extends Activity implements View.OnClickListener {
    private Intent intent;
    private View mStatusColor;
    private ImageView mBack;
    private TextView mCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        changeTransparent();
        immerseStatus();
        init();
        monitor();
    }
    private void init(){
        mBack=f(R.id.info_back);
        mCover=f(R.id.cover_upload_img);
    }
    private void monitor(){
        mBack.setOnClickListener(this);
        mCover.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.info_back:
                finish();
                break;
            case R.id.cover_upload_img:
                intent=new Intent(this, ChooseActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    //获取状态栏高度并赋颜色
    private void immerseStatus() {
        mStatusColor = f(R.id.g_info_status_color);
        ViewGroup.LayoutParams params = mStatusColor.getLayoutParams();
        params.height = ScreenSize.getStatusBarHeight(this);
    }
    /*
    通知栏变透明
     */
    private void changeTransparent(){
        //判断当前设备版本号是否为4.4以上，如果是，则通过调用setTranslucentStatus让状态栏变透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Status.setTranslucentStatus(this,true);
        }
    }

    public <T extends View> T f(int id) {
        return (T) findViewById(id);
    }
}

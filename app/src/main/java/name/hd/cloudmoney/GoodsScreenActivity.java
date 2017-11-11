package name.hd.cloudmoney;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import name.hd.cloudmoney.tools.Status;

public class GoodsScreenActivity extends Activity implements View.OnClickListener {
    private View mStatusColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_goods_screen);
//        changeTransparent();
//        immerseStatus();
        init();
        monitor();
    }

    public GoodsScreenActivity() {

    }

    private void init() {
    }

    private void monitor() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    //获取状态栏高度并赋高度
    /*private void immerseStatus() {
        mStatusColor = f(R.id.screen_status_color);
        ViewGroup.LayoutParams params = mStatusColor.getLayoutParams();
        params.height = ScreenSize.getStatusBarHeight(this);
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
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

    public <T extends View> T f(int id) {
        return (T) findViewById(id);
    }

}

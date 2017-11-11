package name.hd.cloudmoney;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import name.hd.cloudmoney.fragment.CircleFragment;
import name.hd.cloudmoney.fragment.FindFragment;
import name.hd.cloudmoney.fragment.HomeFragment;
import name.hd.cloudmoney.fragment.MineFragment;
import name.hd.cloudmoney.fragment.MoneyFragment;
import name.hd.cloudmoney.tools.Status;

public class MainActivity extends FragmentActivity{
    public static long mat;
    private RadioGroup mBottomNav;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Status.setStatusTextColor(this);
        changeTransparent();
        init();
        monitor();
    }
    private void init(){
        mBottomNav=f(R.id.main_bottom_nav);
    }
    private void monitor(){
        thingBottomNav();
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

    /*
    监听底部导航栏
     */
    private void thingBottomNav(){
        mBottomNav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                manager=getSupportFragmentManager();
                transaction=manager.beginTransaction();
                switch (id){
                    case R.id.main_home:
                        transaction.replace(R.id.ll_page,new HomeFragment());
                        break;
                    case R.id.main_circle:
                        transaction.replace(R.id.ll_page,new CircleFragment());
                        break;
                    case R.id.main_find:
                        transaction.replace(R.id.ll_page,new FindFragment());
                        break;
                    case R.id.main_money:
                        transaction.replace(R.id.ll_page,new MoneyFragment());
                        break;
                    case R.id.main_mine:
                        transaction.replace(R.id.ll_page,new MineFragment());
                        break;
                    default:
                        break;
                }
                transaction.commit();
            }
        });
        /*
        以下为初始化页面作用
         */
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.ll_page,new HomeFragment());
        transaction.commit();
    }
    /**
     * 监控，按两次退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return exit();
        }
        return super.onKeyDown(keyCode, event);

    }

    public boolean exit() {
        if (System.currentTimeMillis() - mat > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mat = System.currentTimeMillis();
        } else {
            finish();
        }
        return true;
    }
    public <T extends View> T f(int id) {
        return (T) findViewById(id);
    }

}

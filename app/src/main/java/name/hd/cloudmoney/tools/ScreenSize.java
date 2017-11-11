package name.hd.cloudmoney.tools;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by AkiItachi on 2017/8/30.
 */

public class ScreenSize {
    // 方法1 Android获得屏幕的宽和高

    public static int screenWidth(Activity context){
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        return display.getWidth();
    }
    public  static int screenHeight(Activity context){
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        return  display.getHeight();
    }
    /**
     * 获取当前设备状态栏高度
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

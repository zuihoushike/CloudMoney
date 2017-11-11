package name.hd.cloudmoney.cloudmsg;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;

import name.hd.cloudmoney.R;


public class WelcomeActivity extends Activity {

	private ImageView mWelcomeImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		mWelcomeImage = (ImageView) findViewById(R.id.welcome_image);
		mWelcomeImage.setImageResource(R.mipmap.welcome);
		
		// 延迟2秒，然后跳转到登录页面
        new Handler().postDelayed(r, 2000);
	}
	
	 Runnable r = new Runnable() {
	        @Override
	        public void run() {
	            Intent intent = new Intent();
				intent.setClass(WelcomeActivity.this, ConfigActivity.class);
	            startActivity(intent);
	            finish();
	        }
	    };
}


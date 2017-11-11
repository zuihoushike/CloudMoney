package name.hd.cloudmoney;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MarketingManagerActivity extends Activity implements View.OnClickListener {
    private ImageView mNavBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing_manager);
        init();
        monitor();
    }
    private void init(){
        mNavBack=f(R.id.market_nav_back);
    }
    private void monitor(){
        mNavBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.market_nav_back:
                finish();
                break;
            default:
                break;
        }
    }

    public <T extends View> T f(int id) {
        return (T) findViewById(id);
    }
}

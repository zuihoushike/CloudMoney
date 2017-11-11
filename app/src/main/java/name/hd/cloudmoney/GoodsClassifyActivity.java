package name.hd.cloudmoney;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GoodsClassifyActivity extends Activity implements View.OnClickListener {
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_classify);
        init();
        monitor();
    }
    private void init(){
        back=f(R.id.classify_back);
    }
    private void monitor(){
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.classify_back:
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

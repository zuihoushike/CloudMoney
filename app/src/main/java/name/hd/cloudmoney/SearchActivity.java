package name.hd.cloudmoney;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SearchActivity extends Activity {
    private EditText mSearchSearch;
    private ImageView mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
        init();
        mSearchSearch.setFocusableInTouchMode(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) mSearchSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(mSearchSearch, 0);
                           }

                       },
                100);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSearchSearch==null||mSearchSearch.getText().toString().equals("")){
                    Toast.makeText(SearchActivity.this,"请输入要搜索的内容！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SearchActivity.this,"正在搜索，请稍后。。。",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void init() {
        mSearchSearch = findViewById(R.id.search_search);
        mSearch = findViewById(R.id.search_icon);
    }

}

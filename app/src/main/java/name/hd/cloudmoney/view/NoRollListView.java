package name.hd.cloudmoney.view;

import android.widget.ListView;
/*
解决ListView在ScrollView中不能展示item问题
 */
/**
 * Created by AkiRuka on 2017/9/22.
 */

public class NoRollListView extends ListView {
    public NoRollListView(android.content.Context context,android.util.AttributeSet attrs){
        super(context, attrs);
    }
    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}

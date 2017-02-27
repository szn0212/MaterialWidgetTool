package tools.study.com.materialwidgettool.swipeback;

import android.os.Bundle;
import android.support.annotation.Nullable;

import tools.study.com.materialwidgettool.R;
import tools.study.com.materialwidgettool.swipeback.library.SwipeBackActivity;

/**
 * @author suzhuning
 * @date 2016/11/18.
 * Description:
 */
public class SecondActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout_news_list_layout);

    }

}

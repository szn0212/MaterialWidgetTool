package tools.study.com.materialwidgettool.drawerlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;
import tools.study.com.materialwidgettool.drawerlayout.library.BaseLazyFragment;
import tools.study.com.materialwidgettool.utils.EventCenter;

/**
 * @author suzhuning
 * @date 2016/11/7.
 * Description:
 */
public class NewsListFragment extends BaseLazyFragment {

    @BindView(R.id.news_text)
    TextView news_text;

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            String id = bundle.getString("categoryId");
            news_text.setText(id);
        }
    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.drawerlayout_news_list_layout;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }
}

package tools.study.com.materialwidgettool.recycleview;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

import tools.study.com.materialwidgettool.R;

/**
 * @author suzhuning
 * @date 2016/11/21.
 * Description:
 */
public class CustomLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.recyclerview_loadmore_view_layout;
    }

    /**
     * 如果返回true，数据全部加载完毕后会隐藏加载更多
     * 如果返回false，数据全部加载完毕后会显示getLoadEndViewId()布局
     */
    @Override
    public boolean isLoadEndGone() {
        return true;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.loading_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.loading_view;
    }
}

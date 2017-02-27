package tools.study.com.materialwidgettool;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author suzhuning
 * @date 2016/11/15.
 * Description:
 */
public class WidgetListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private boolean changeHeight;

    public WidgetListAdapter(int layoutRes) {
        super(layoutRes, null);
    }

    public WidgetListAdapter(boolean changeHeight, int layoutRes) {
        super(layoutRes, null);
        this.changeHeight = changeHeight;
    }

    public WidgetListAdapter(int layoutRes, List<String> datas) {
        super(layoutRes, datas);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.textview, s);

        if(changeHeight){
            LinearLayout linearLayout = baseViewHolder.getView(R.id.item_layout);
            StaggeredGridLayoutManager.LayoutParams param = (StaggeredGridLayoutManager.LayoutParams) linearLayout.getLayoutParams();
            param.height = 120 + (int) (Math.random() * 200);
            linearLayout.setLayoutParams(param);
        }
    }
}

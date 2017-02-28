package tools.study.com.materialwidgettool.recycleview;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import tools.study.com.materialwidgettool.R;

/**
 * @author suzhuning
 * @date 2017/2/27.
 * Description:
 */
public class SectionListAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {

    public SectionListAdapter(int layoutResId, int sectionHeadResId, List<MySection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder baseViewHolder, MySection mySection) {
        baseViewHolder.setText(R.id.textview, mySection.header);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MySection mySection) {
        baseViewHolder.setText(R.id.textview, mySection.t);
    }
}

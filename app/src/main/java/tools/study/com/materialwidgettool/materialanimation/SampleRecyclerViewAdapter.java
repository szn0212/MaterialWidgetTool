package tools.study.com.materialwidgettool.materialanimation;

import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import tools.study.com.materialwidgettool.R;

/**
 * @author suzhuning
 * @date 2016/11/22.
 * Description:
 */
public class SampleRecyclerViewAdapter extends BaseQuickAdapter<SampleEntity, BaseViewHolder> {

    public SampleRecyclerViewAdapter(List<SampleEntity> lists) {
        super(R.layout.material_animation_sample_item_layout, lists);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SampleEntity sampleEntity) {
        baseViewHolder.setText(R.id.sample_text, sampleEntity.getName());

        DrawableCompat.setTint(((ImageView) baseViewHolder.getView(R.id.sample_icon)).getDrawable(),
                ContextCompat.getColor(mContext, sampleEntity.getColor()));
    }
}

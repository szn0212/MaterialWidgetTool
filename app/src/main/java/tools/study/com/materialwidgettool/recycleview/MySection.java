package tools.study.com.materialwidgettool.recycleview;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author suzhuning
 * @date 2017/2/27.
 * Description:
 */
public class MySection extends SectionEntity<String> {


    public MySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySection(String s) {
        super(s);
    }
}

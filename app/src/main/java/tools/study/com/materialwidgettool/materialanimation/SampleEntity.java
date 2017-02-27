package tools.study.com.materialwidgettool.materialanimation;

import android.support.annotation.ColorRes;

import java.io.Serializable;

/**
 * @author suzhuning
 * @date 2016/11/22.
 * Description:
 */
public class SampleEntity implements Serializable {

    private int color;
    private String name;

    public SampleEntity(@ColorRes int color, String name) {
        this.color = color;
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}

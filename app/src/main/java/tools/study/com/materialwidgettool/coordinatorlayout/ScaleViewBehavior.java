package tools.study.com.materialwidgettool.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * @author suzhuning
 * @date 2016/12/1.
 * Description:
 *      Behavior只有是CoordinatorLayout的直接子View才有意义。
 *      只要将Behavior绑定到CoordinatorLayout的直接子元素上，就能对触摸事件（touch events）、
 *      window insets、measurement、layout以及嵌套滚动（nested scrolling）等动作进行拦截。
 */
public class ScaleViewBehavior extends CoordinatorLayout.Behavior<ImageView> {

    public ScaleViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }
}

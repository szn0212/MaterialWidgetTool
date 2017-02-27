package tools.study.com.materialwidgettool.drawerlayout.library;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author suzhuning
 * @date 2016/11/2.
 * Description:
 */
public class XViewPager extends ViewPager {

    private boolean isEnableScroll = true;

    public XViewPager(Context context) {
        super(context);
    }

    public XViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEnableScroll(boolean enableScroll) {
        isEnableScroll = enableScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(!isEnableScroll){
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!isEnableScroll){
            return false;
        }
        return super.onTouchEvent(ev);
    }
}

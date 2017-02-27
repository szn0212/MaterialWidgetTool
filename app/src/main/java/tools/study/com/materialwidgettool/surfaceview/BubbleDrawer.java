package tools.study.com.materialwidgettool.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suzhuning
 * @date 2016/11/23.
 * Description:
 */
public class BubbleDrawer {

    private Paint mPaint;
    private int mWidth,mHeight;
    private List<CircleBubble> mBubbles;

    private GradientDrawable mGradientBg;       //渐变背景
    private int[] mGradientColors;              //渐变颜色数组

    public BubbleDrawer(Context context){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBubbles = new ArrayList<>();
    }

    public void setBackgroundGradient(int[] gradientColors){  //必须>=2，不然没法渐变
        this.mGradientColors = gradientColors;
    }

    private int[] getBackgroundGradient(){
        return mGradientColors;
    }

    //设置显示悬浮气泡的范围
    void setViewSize(int width, int height){
        if(this.mWidth != width && this.mHeight != height){
            this.mWidth = width;
            this.mHeight = height;
            if(this.mGradientBg != null){
                mGradientBg.setBounds(0, 0, width, height);
            }
        }

        //设置一些默认的气泡
        initDefaultBubble(width);
    }

    /**
     * 初始化默认的气泡
     */
    private void initDefaultBubble(int width) {
        if(mBubbles.size() == 0){
            mBubbles.add(new CircleBubble(0.20f * width, -0.30f * width, 0.06f * width, 0.022f * width,
                    0.56f * width, 0.0150f, 0x56ffc7c7));
            mBubbles.add(new CircleBubble(0.58f * width, -0.15f * width, -0.15f * width, 0.032f * width,
                    0.6f * width, 0.00600f, 0x45fffc9e));
            mBubbles.add(new CircleBubble(0.9f * width, -0.19f * width, 0.08f * width, -0.015f * width, 0.44f * width,
                    0.00300f, 0x7596ff8f));
            mBubbles.add(new CircleBubble(1.1f * width, 0.25f * width, -0.08f * width, -0.015f * width, 0.42f * width,
                    0.00200f, 0x80c7dcff));
            mBubbles.add(new CircleBubble(0.20f * width, 0.50f * width, -0.06f * width, 0.022f * width, 0.42f * width,
                    0.0150f, 0x70efc2ff));
            mBubbles.add(new CircleBubble(0.70f * width, 0.60f * width, 0.10f * width, 0.050f * width, 0.30f * width,
                    0.0100f, 0x75E99161));
        }
    }

    private void drawCircleBubble(Canvas canvas, float alpha){
        for (CircleBubble bubble : this.mBubbles){
            bubble.updateAndDraw(canvas, mPaint, alpha);
        }
    }

    /**
     * 绘制渐变背景
     */
    private void drawGradientBackground(Canvas canvas, float alpha){
        if(mGradientBg == null){
            //设置渐变模式和颜色
            mGradientBg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, getBackgroundGradient());
            //规定背景宽高  一般都为整屏
            mGradientBg.setBounds(0, 0, mWidth, mHeight);
        }
        //然后开始画
        mGradientBg.setAlpha(Math.round(alpha * 255f));
        mGradientBg.draw(canvas);
    }

    void drawBgAndBubble(Canvas canvas, float alpha){
        drawGradientBackground(canvas, alpha);
        drawCircleBubble(canvas, alpha);
    }
}

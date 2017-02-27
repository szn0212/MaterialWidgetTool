package tools.study.com.materialwidgettool.surfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author suzhuning
 * @date 2016/11/23.
 * Description:
 */
public class CircleBubble{

    private float cx,cy;        //圆心坐标
    private float dx,dy;        //圆心偏移距离
    private float radius;
    private int color;
    private float variationOfFrame;     //设置每帧变化量
    private boolean isGrowing = true;   //根据此标志位判断左右移动
    private float curVariationOfFrame = 0f;     //当前帧变化量

    public CircleBubble(float cx, float cy, float dx, float dy,
                        float radius, float variationOfFrame, int color){
        this.cx = cx;
        this.cy = cy;
        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
        this.variationOfFrame = variationOfFrame;
        this.color = color;
    }

    void updateAndDraw(Canvas canvas, Paint paint, float alpha){
        /**
         * 每次绘制时都根据标志位(isGrowing)和每帧变化量(variationOfFrame)进行更新
         * 说白了其实就是每帧都会变化一段距离  连在一起就产生动画效果
         */
        if(isGrowing){
            curVariationOfFrame += variationOfFrame;
            if(variationOfFrame > 1f){
                curVariationOfFrame = 1f;
                isGrowing = false;
            }
        }else {
            curVariationOfFrame -= variationOfFrame;
            if(curVariationOfFrame < 0f){
                curVariationOfFrame = 0f;
                isGrowing = true;
            }
        }

        //根据当前帧变化计算圆心偏移后的位置
        float curCx = cx + dx * curVariationOfFrame;
        float curCy = cy + dy * curVariationOfFrame;
        //设置画笔颜色
        int curColor = convertAlphaColor(alpha * (Color.alpha(color) / 255f), color);
        paint.setColor(curColor);
        //这里才是真正的开始画圆形气泡
        canvas.drawCircle(curCx, curCy, radius, paint);
    }

    /**
     * 转成透明色
     */
    private int convertAlphaColor(float percent,final int originalColor) {
        int newAlpha = (int)(percent * 255) & 0xFF;
        return (newAlpha << 24) | (originalColor & 0xFFFFFF);
    }

}

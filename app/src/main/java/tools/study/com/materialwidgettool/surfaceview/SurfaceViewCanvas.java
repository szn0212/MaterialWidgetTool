package tools.study.com.materialwidgettool.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author suzhuning
 * @date 2016/11/23.
 * Description:
 */
public class SurfaceViewCanvas extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mSurfaceHolder;
    //子线程标志位
    private boolean isDrawing;
    private Canvas mCanvas;
    private Paint mPaint;
    private Path mPath;

    private float mLastX;
    private float mLastY;

    public SurfaceViewCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);//能否获得焦点
        setFocusableInTouchMode(true);//能否通过触摸获得焦点
        setKeepScreenOn(true);//保持屏幕常亮

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStrokeWidth(10f);
        mPaint.setColor(Color.parseColor("#FF4081"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPath = new Path();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawing();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (isDrawing){
            drawing();
        }
        long end = System.currentTimeMillis();
        if(end - start < 100){
            try {
                Thread.sleep(100 - (end - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawing() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(mCanvas != null){
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                new Thread(this).start();
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;

            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mLastX);
                float dy = Math.abs(y - mLastY);
                if(dx >= 3 || dy >= 3){
                    mPath.quadTo(mLastX, mLastY, (mLastX + x) / 2, (mLastY + y) / 2);
                }
                mLastX = x;
                mLastY = y;
                break;

            case MotionEvent.ACTION_UP:
                isDrawing = false;
                break;
        }

        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if(wSpecMode == MeasureSpec.AT_MOST && hSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(300,300);
        }else if(wSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(300, hSpecSize);
        }else if(hSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(wSpecSize, 300);
        }
    }
}

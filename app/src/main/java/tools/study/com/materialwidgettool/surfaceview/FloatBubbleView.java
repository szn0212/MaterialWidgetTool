package tools.study.com.materialwidgettool.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.AnimationUtils;

/**
 * @author suzhuning
 * @date 2016/11/23.
 * Description:
 */
public class FloatBubbleView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread mDrawThread;
    private BubbleDrawer mPreDrawer;//上一次绘制对象
    private BubbleDrawer mCurDrawer;//当前绘制对象
    private float curDrawerAlpha = 0f;//当前透明度（范围为0 因为 CircleBubble 中 convertAlphaColor 方法已经处理过了）
    private int mWidth, mHeight;//当前屏幕宽高

    public FloatBubbleView(Context context) {
        super(context);
        initThreadAndHolder(context);
    }

    public FloatBubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initThreadAndHolder(context);
    }

    public FloatBubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initThreadAndHolder(context);
    }

    private void initThreadAndHolder(Context context) {
        mDrawThread = new DrawThread();
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        //渐变效果 就是显示SurfaceView的时候从暗到明
        surfaceHolder.setFormat(PixelFormat.RGBA_8888);
        mDrawThread.start();
    }


    /** Surface 回调方法 需要加同步锁 防止阻塞 START */

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    /**
     * 设置绘制者
     */
    public void setDrawer(BubbleDrawer bubbleDrawer){
        if(bubbleDrawer == null){
            return;
        }
        curDrawerAlpha = 0f;
        //如果当前有正在绘制的对象 直接设置为前一次绘制对象
        if(this.mCurDrawer != null){
            mPreDrawer = mCurDrawer;
        }
        this.mCurDrawer = bubbleDrawer;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        synchronized (mDrawThread){
            mDrawThread.mSurfaceHolder = holder;
            mDrawThread.notify();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        synchronized (mDrawThread){
            mDrawThread.mSurfaceHolder = holder;
            mDrawThread.notify();
            while (mDrawThread.mActive){
                try {
                    mDrawThread.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        holder.removeCallback(this);
    }
    /** ========== Surface 回调方法 需要加同步锁 防止阻塞 END========== */

    /** ========== 处理与 Activity 生命周期相关方法 需要加同步锁 防止阻塞 START========== */
    public void onDrawResume(){
        synchronized (mDrawThread){
            mDrawThread.mRunnning = true;
            mDrawThread.notify();
        }
    }

    public void onDrawPause(){
        synchronized (mDrawThread){
            mDrawThread.mRunnning = false;
            mDrawThread.notify();
        }
    }

    public void onDrawDestroy(){
        synchronized (mDrawThread){
            mDrawThread.mQuit = true;
            mDrawThread.notify();
        }
    }
    /** ========== 处理与 Activity 生命周期相关方法 需要加同步锁 防止阻塞 END========== */

    public class DrawThread extends Thread {

        SurfaceHolder mSurfaceHolder;
        boolean mRunnning, mActive, mQuit;
        Canvas canvas;

        @Override
        public void run() {
            while (true){
                synchronized (this){
                    if(!processDrawThreadState()){
                        return;
                    }
                    //动画开始时间
                    long startTime = AnimationUtils.currentAnimationTimeMillis();
                    //处理画布并进行绘制
                    processDrawCanvas(canvas);
                    //绘制时间
                    long drawTime = AnimationUtils.currentAnimationTimeMillis();
                    //处理一下线程需要的睡眠时间
                    processDrawThreadSleep(drawTime);
                }
            }
        }

        /**
         * 处理绘制线程的状态问题
         * @return true：不结束继续绘制 false：结束且不绘制
         */
        private boolean processDrawThreadState(){
            //处理没有运行或者Holder为null的情况
            while (mSurfaceHolder == null || !mRunnning){
                if(mActive){
                    mActive = false;
                    notify();
                }
                if(mQuit){
                    return false;
                }
                try {
                    wait();//等待
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            //其他情况肯定是活动状态
            if(!mActive){
                mActive = true;
                notify();//唤醒
            }
            return true;
        }

        /**
         * 处理画布与绘制过程 要注意一定要保证是同步锁中才能执行
         */
        private void processDrawCanvas(Canvas canvas){
            try {
                canvas = mSurfaceHolder.lockCanvas();
                if(canvas != null){
                    //清屏操作
                    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    drawSurface(canvas);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(canvas != null){
                    //释放canvas锁，并显示视图
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

        private void drawSurface(Canvas canvas) {
            //防空保护
            if(mWidth == 0 || mHeight == 0){
                return;
            }

            //如果前一次回执对象不为null 且当前绘制者有透明效果的话 会之前一次的对象即可
            if(mPreDrawer != null && curDrawerAlpha < 1f){
                mPreDrawer.setViewSize(mWidth, mHeight);
                mPreDrawer.drawBgAndBubble(canvas, 1f - curDrawerAlpha);
            }

            if(curDrawerAlpha < 1f){
                curDrawerAlpha += 0.6f;
                if(curDrawerAlpha > 1f){
                    curDrawerAlpha = 1f;
                    mPreDrawer = null;
                }
            }

            //如果当前有绘制对象 直接绘制即可 先设置绘制宽高再绘制气泡
            if(mCurDrawer != null){
                mCurDrawer.setViewSize(mWidth, mHeight);
                mCurDrawer.drawBgAndBubble(canvas, curDrawerAlpha);
            }
        }

        private void processDrawThreadSleep(long drawTime){
            long needSleepTime = 16 - drawTime;

            if(needSleepTime > 0){
                try {
                    Thread.sleep(needSleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}

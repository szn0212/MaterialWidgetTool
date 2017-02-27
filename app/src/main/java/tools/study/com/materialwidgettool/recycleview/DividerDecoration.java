package tools.study.com.materialwidgettool.recycleview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author suzhuning
 * @date 2016/11/15.
 * Description:
 */
public class DividerDecoration extends RecyclerView.ItemDecoration {

    private int mHeight;
    private int mWidth;
    private int mLPadding;
    private int mRPadding;
    private int mTPadding;
    private int mBPadding;
    private Paint mPaint;

    private DividerDecoration(int mWidth, int mHeight, int mLPadding, int mRPadding, int mTPadding, int mBPadding, int mColor) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mLPadding = mLPadding;
        this.mRPadding = mRPadding;
        this.mTPadding = mTPadding;
        this.mBPadding = mBPadding;
        mPaint = new Paint();
        mPaint.setColor(mColor);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof LinearLayoutManager){
            drawGridHorizontal(c, parent);
            drawGridVertical(c, parent);
        }else if(layoutManager instanceof LinearLayoutManager){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            if(linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL){
                drawHorizontal(c, parent);
            }else {
                drawVertical(c, parent);
            }
        }else {
            drawGridHorizontal(c, parent);
            drawGridVertical(c, parent);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        int left = parent.getChildAt(0).getLeft() + mLPadding;
        int right = parent.getChildAt(0).getRight() - mRPadding;
        for (int i = 0; i < count-1; i++){
            View child = parent.getChildAt(i);
            int top = child.getBottom() + mTPadding;
            int bottom = child.getBottom() + mHeight - mBPadding;

            c.save();
            c.drawRect(left, top, right, bottom, mPaint);
            c.restore();
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        int top = parent.getChildAt(0).getTop() + mTPadding;
        int bottom = parent.getChildAt(0).getBottom() - mBPadding;

        for (int i = 0; i < count-1; i++){
            View child = parent.getChildAt(i);
            int left = child.getRight() + mLPadding;
            int right = child.getRight() + mWidth - mRPadding;

            c.save();
            c.drawRect(left, top, right, bottom, mPaint);
            c.restore();
        }
    }

    private void drawGridHorizontal(Canvas c, RecyclerView parent){
        int count = parent.getChildCount();

        for (int i = 0; i < count-1; i++){
            View child = parent.getChildAt(i);
            int top = child.getTop() + mTPadding;
            int bottom = child.getBottom() - mBPadding;
            int left = child.getRight() + mLPadding;
            int right = child.getRight() + mWidth - mRPadding;

            c.save();
            c.drawRect(left, top, right, bottom, mPaint);
            c.restore();
        }
    }

    private void drawGridVertical(Canvas c, RecyclerView parent){
        int count = parent.getChildCount();
        for (int i = 0; i < count-1; i++){
            View child = parent.getChildAt(i);
            int left = child.getLeft() + mLPadding;
            int right = child.getRight() - mRPadding;
            int top = child.getBottom() + mTPadding;
            int bottom = child.getBottom() + mHeight - mBPadding;

            c.save();
            c.drawRect(left, top, right, bottom, mPaint);
            c.restore();
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof LinearLayoutManager){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            if(linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL){
                outRect.set(0,0,mWidth,0);
            }else {
                outRect.set(0,0,0,mHeight);
            }
        }else {
            int spanCount = getSpanCount(parent, layoutManager);
            int childCount = parent.getAdapter().getItemCount();
            int position = ((RecyclerView.LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
            if(isLastRaw(layoutManager, position, spanCount, childCount)){
                outRect.set(0,0,mWidth,0);
            }else if(isLastColum(layoutManager, position, spanCount, childCount)){
                outRect.set(0,0,0,mHeight);
            }else {
                outRect.set(0,0,mWidth,mHeight);
            }
        }
    }

    //如果是最后一行，则不需要绘制底部
    private boolean isLastRaw(RecyclerView.LayoutManager layoutManager, int position, int spanCount, int childCount){
        childCount = childCount - childCount % spanCount;
        if(layoutManager instanceof GridLayoutManager){
            if(position >= childCount){
                return true;
            }
        }else if(layoutManager instanceof StaggeredGridLayoutManager){
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            if(staggeredGridLayoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL){
                if(position >= childCount){
                    return true;
                }
            }else {
                if((position + 1) % spanCount == 0){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLastColum(RecyclerView.LayoutManager layoutManager, int position, int spanCount, int childCount){
        if(layoutManager instanceof GridLayoutManager){
            if((position + 1) % spanCount == 0){
                return true;
            }
        }else if(layoutManager instanceof StaggeredGridLayoutManager){
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            if(staggeredGridLayoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL){
                if((position + 1) % spanCount == 0){
                    return true;
                }
            }else {
                childCount = childCount - childCount % spanCount;
                if(position >= childCount){
                    return true;
                }
            }
        }
        return false;
    }

    //列数
    private int getSpanCount(RecyclerView parent, RecyclerView.LayoutManager layoutManager) {
        int spanCount = -1;
        if(layoutManager instanceof GridLayoutManager){
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }else if(layoutManager instanceof StaggeredGridLayoutManager){
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    public static class Builder{

        private Context mContext;
        private Resources mResources;
        private int mHeight;
        private int mWidth;
        private int mLPadding;
        private int mRPadding;
        private int mTPadding;
        private int mBPadding;
        private int mColor;

        public Builder(Context context){
            mContext = context;
            mResources = context.getResources();
            mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 1f, mResources.getDisplayMetrics());
            mLPadding = 0;
            mRPadding = 0;
            mTPadding = 0;
            mBPadding = 0;
            mColor = Color.BLACK;
        }


        /**
         * Set the Divider height in pixels
         * @param pixels  height in pixels
         * @return the current instance of the Builder
         */
        public Builder setHeight(float pixels){
            mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.getDisplayMetrics());
            return this;
        }

        /**
         * Set the divider in dp
         * @param heightRes height resource id
         * @return
         */
        public Builder setHeight(@DimenRes int heightRes){
            mHeight = mResources.getDimensionPixelSize(heightRes);
            return this;
        }

        /**
         * Set the divider width in pixels
         * @param pixels width in pixels
         * @return
         */
        public Builder setWidth(float pixels){
            mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.getDisplayMetrics());
            return this;
        }

        /**
         * Set the divider in dp
         * @param widthRes width resource id
         * @return
         */
        public Builder setWidth(@DimenRes int widthRes){
            mWidth = mResources.getDimensionPixelSize(widthRes);
            return this;
        }

        /**
         * Set the divider padding in pixels
         * @param pixels padding in pixels
         * @return
         */
        public Builder setPadding(float pixels){
            setLeftPadding(pixels);
            setRightPadding(pixels);
            setTopPadding(pixels);
            setBottomPadding(pixels);
            return this;
        }

        /**
         * Set the divider padding in dp
         * @param paddingRes padding resource id
         * @return
         */
        public Builder setPadding(@DimenRes int paddingRes){
            setLeftPadding(paddingRes);
            setRightPadding(paddingRes);
            setTopPadding(paddingRes);
            setBottomPadding(paddingRes);
            return this;
        }

        public Builder setLeftPadding(float pixels){
            mLPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.getDisplayMetrics());
            return this;
        }

        public Builder setLeftPadding(@DimenRes int leftPadding){
            mLPadding = mResources.getDimensionPixelSize(leftPadding);
            return this;
        }

        public Builder setRightPadding(float pixels){
            mRPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.getDisplayMetrics());
            return this;
        }

        public Builder setRightPadding(@DimenRes int rightPadding){
            mRPadding = mResources.getDimensionPixelSize(rightPadding);
            return this;
        }

        public Builder setTopPadding(float pixels){
            mTPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.getDisplayMetrics());
            return this;
        }

        public Builder setTopPadding(@DimenRes int topPadding){
            mTPadding = mResources.getDimensionPixelSize(topPadding);
            return this;
        }

        public Builder setBottomPadding(float pixels){
            mBPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.getDisplayMetrics());
            return this;
        }

        public Builder setBottomPadding(@DimenRes int bottomPadding){
            mBPadding = mResources.getDimensionPixelSize(bottomPadding);
            return this;
        }

        /**
         * Set the divider color
         * @param color the color
         * @return
         */
        public Builder setColor(@ColorInt int color){
            mColor = color;
            return this;
        }

        public Builder setColorResource(@ColorRes int resource){
            setColor(ContextCompat.getColor(mContext, resource));
            return this;
        }

        public DividerDecoration build(){
            return new DividerDecoration(mWidth, mHeight, mLPadding, mRPadding, mTPadding, mBPadding, mColor);
        }
    }
}

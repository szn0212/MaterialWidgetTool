package tools.study.com.materialwidgettool.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

//import com.facebook.common.references.CloseableReference;
//import com.facebook.datasource.BaseDataSubscriber;
//import com.facebook.datasource.DataSource;
//import com.facebook.drawee.backends.pipeline.Fresco;
//import com.facebook.imagepipeline.image.CloseableBitmap;
//import com.facebook.imagepipeline.image.CloseableImage;

/**
 * @author suzhuning
 * @version 2.7
 * @date 16/4/21.
 * @2016 AngelCrunch天使汇 http://www.angelcrunch.com/
 */
public class BitmapDataSubscriber /*extends BaseDataSubscriber<CloseableReference<CloseableImage>>*/ {

//    private Context mContext;
//    private OnResourceReadyCallback onResourceReadyCallback;
//    private CloseableReference<CloseableImage> imageReferenceClone;
//
//    public BitmapDataSubscriber(Context mContext, OnResourceReadyCallback onResourceReadyCallback) {
//        this.mContext = mContext;
//        this.onResourceReadyCallback = onResourceReadyCallback;
//    }
//
//    @Override
//    public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
//        if (!dataSource.isFinished()) {
//            return;
//        }
//        CloseableReference<CloseableImage> imageReference = dataSource.getResult();
//        if (imageReference != null) {
//            imageReferenceClone = imageReference.clone();
//            try {
//                CloseableImage closeableImage = imageReferenceClone.get();
//                if (closeableImage instanceof CloseableBitmap) {
//                    handleBitmap((CloseableBitmap) closeableImage);
//                } /*else if (closeableImage instanceof CloseableAnimatedImage) {
//                    handleAnimateBitmap((CloseableAnimatedImage) closeableImage);
//                }*/
//            } finally {
//                imageReference.close();
////                CloseableReference.closeSafely(imageReferenceClone);
//            }
//        }
//    }
//
//    @Override
//    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
//        onResourceReadyCallback.onFail(new NullPointerException("Get Bitmap is failed."));
//    }
//
////    private void handleAnimateBitmap(CloseableAnimatedImage animatedImage) {
////        Log.i("BitmapUtil", "animatableImage loaded");
////        AnimatedDrawableFactory animatedDrawableFactory =
////                Fresco.getImagePipelineFactory().getAnimatedDrawableFactory();
////        AnimatedDrawable drawable =
////                animatedDrawableFactory.create(animatedImage.getImageResult());
//////        if (onResourceReadyCallback != null) {
//////            onResourceReadyCallback.onReady(drawable);
//////        } else {
//////            onResourceReadyCallback.onFail(new NullPointerException("Bitmap is empty."));
//////        }
////    }
//
//    private void handleBitmap(CloseableBitmap closeableBitmap) {
//        Bitmap underlyingBitmap = closeableBitmap.getUnderlyingBitmap();
//        if (onResourceReadyCallback != null && isValidateBitmap(underlyingBitmap)) {
////            BitmapDrawable bitmapDrawable = new BitmapDrawable(mContext.getResources(), underlyingBitmap);
//            onResourceReadyCallback.onReady(underlyingBitmap);
//        } else {
//            onResourceReadyCallback.onFail(new NullPointerException("Bitmap is empty."));
//        }
//    }
//
//    private boolean isValidateBitmap(Bitmap bitmap) {
//        return bitmap != null && !bitmap.isRecycled();
//    }
//
//    public void close(){
//        CloseableReference.closeSafely(imageReferenceClone);
//    }
//
//
//    public interface OnResourceReadyCallback{
//        void onReady(Bitmap bitmap);
//        void onFail(Exception e);
//    }


//    private void test(){
//        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(source))
//                .setAutoRotateEnabled(true)
//                .build();
//        ImagePipeline imagePipeline = Fresco.getImagePipeline();
//        if(imagePipeline.isInBitmapMemoryCache(imageRequest)){
//            DataSource<CloseableReference<CloseableImage>> dataSource =
//                    imagePipeline.fetchImageFromBitmapCache(imageRequest,mContext);
//            CloseableReference<CloseableImage> imageReference = null;
//            try {
//                imageReference = dataSource.getResult();
//                if (imageReference != null) {
//                    CloseableImage closeableImage = imageReference.get();
//                    if (closeableImage instanceof CloseableBitmap) {
//                        Bitmap bitmap = ((CloseableBitmap)closeableImage).getUnderlyingBitmap();
//                        if (bitmap != null && !bitmap.isRecycled()) {
//                            BitmapDrawable bitmapDrawable = new BitmapDrawable(mContext.getResources(), bitmap);
//                            return bitmapDrawable;
//                        }
//                    }
//                }
//            } finally {
//                dataSource.close();
//                CloseableReference.closeSafely(imageReference);
//            }
//        }else {
//            DataSource<CloseableReference<CloseableImage>> dataSource =
//                    imagePipeline.fetchDecodedImage(imageRequest, mContext);
//            dataSource.subscribe(
//                    new BitmapDataSubscriber(mContext, onResourceReadyCallback), CallerThreadExecutor.getInstance());
//
//        }
//    }
}
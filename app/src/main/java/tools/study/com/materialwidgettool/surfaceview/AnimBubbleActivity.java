package tools.study.com.materialwidgettool.surfaceview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;

/**
 * @author suzhuning
 * @date 2016/11/23.
 * Description:
 */
public class AnimBubbleActivity extends AppCompatActivity {

    @BindView(R.id.surfaceview_floatbubble)
    FloatBubbleView surfaceview_floatbubble;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_bubble_layout);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initView() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    private void initData(){
        //设置气泡绘制者
        BubbleDrawer bubbleDrawer = new BubbleDrawer(this);
        //设置渐变背景 如果不需要渐变 设置相同颜色即可
        bubbleDrawer.setBackgroundGradient(new int[]{0xffffffff, 0xffffffff});
        //给SurfaceView设置一个绘制者
        surfaceview_floatbubble.setDrawer(bubbleDrawer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        surfaceview_floatbubble.onDrawResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        surfaceview_floatbubble.onDrawPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        surfaceview_floatbubble.onDrawDestroy();
    }
}

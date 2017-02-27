package tools.study.com.materialwidgettool.coordinatorlayout;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;

/**
 * @author suzhuning
 * @date 2016/12/1.
 * Description:
 */
public class CoordinatorActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout_layout);
        ButterKnife.bind(this);

        transparentStatusBar();

        webview.loadUrl("http://m.angelcrunch.com/agreement/online");
    }

    @TargetApi(21)
    private void transparentStatusBar(){
        View decoreView = getWindow().getDecorView();
        decoreView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }
}
